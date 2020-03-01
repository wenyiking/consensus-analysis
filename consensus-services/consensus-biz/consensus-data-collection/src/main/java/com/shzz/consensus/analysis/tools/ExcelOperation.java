package com.shzz.consensus.analysis.tools;


import com.shzz.consensus.analysis.anno.FieldAtExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.NotThreadSafe;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname ExcelOperation
 * @Description TODO
 * @Date 2019/12/29 23:28
 * @Created by wangwen7
 */
@NotThreadSafe
public class ExcelOperation {
    private final static Logger logger = LoggerFactory.getLogger(ExcelOperation.class);


    public static <T> List<T> getExcelValue(String filePath, Class<T> clazz) throws IOException {

        /**
         * @Author wangwen
         * @Description //TODO
         * @Date 23:10 2020/1/9
         * @param filePath 文件路径 全路径
         * @param isLocal 是否是本地文件路径
         * @return
         **/

        InputStream is = null;
        ExcelElement excelElement = new ExcelElement();
        List<T> tList = new ArrayList<>();
        //读取Excel文件
        if (filePath.trim().startsWith("http") || filePath.trim().startsWith("ftp")) {
            URL url = new URL(filePath);
            URLConnection urlConnection = url.openConnection();
            is = urlConnection.getInputStream();
        } else {
            File excelFile = new File(filePath.trim());

            if (!excelFile.canRead()) {
                logger.warn("文件不可读");
                excelFile.setReadable(true);
            }
            excelFile.setExecutable(true);
            is = new FileInputStream(excelFile);

        }
        //获取Excel工作薄
        if (filePath.endsWith("xlsx")) {
            excelElement.setWorkbook(new XSSFWorkbook(is));
        } else {
            excelElement.setWorkbook(new HSSFWorkbook(is));
        }
        if (excelElement.getWorkbook() == null) {
            logger.error("文件有异常，无法操作，请检查");
            return null;
        }

        Workbook workbook = excelElement.getWorkbook();
        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; ++i) {
            Sheet sheet = workbook.getSheetAt(i);
            int rows = sheet.getLastRowNum();

            Row firstRow = sheet.getRow(sheet.getFirstRowNum());

//         Map<String,Integer>  map=new HashMap<>();
            Map<Integer, String> columeAndNameMap = null;
            if (firstRow != null) {
                columeAndNameMap = getFieldNameColumeMap(firstRow);
            } else {
                logger.error("无表头信息行");
            }
            //  int firstRowNumRow = sheet.getFirstRowNum();
            int firstDataRowNum = sheet.getFirstRowNum() + 1;
            int lastRowNum = sheet.getLastRowNum();
            Constructor constructor = null;
            Map<String, String> annotation2FieldMap = getAnnotation2Field(clazz);

            try {
                constructor = clazz.getDeclaredConstructor();//clazz.getConstructor();
                for (int j = firstDataRowNum; j <= lastRowNum; ++j) {
                    @SuppressWarnings("unchecked") T rowEntity = (T) constructor.newInstance();
                    Row currentRow = sheet.getRow(j);
                    if (currentRow != null) {
                        int lastColume = currentRow.getLastCellNum();// 最后一列下标其实是lastColume-1，
                        int firstColume = currentRow.getFirstCellNum();
                        Map<String, String> field2Value = new HashMap<>();
                        for (int k = firstColume; k < lastColume; ++k) {
                            // 前提得保证 表头列与实际数据列一一对应
                            String excleFieldName = columeAndNameMap.get(k);
                            String entityFieldName = annotation2FieldMap.get(excleFieldName);

                            field2Value.put(entityFieldName, getCellValue(currentRow.getCell(k)));// 将excle 表格中每行每个cell 的值与实体类字段对应，cell 统一按照字符串提取，后面需要根据字段类型通过反射赋值

                        }
                        setEntityFieldValue(clazz, rowEntity, field2Value);//调用反射赋值方法
                        tList.add(rowEntity);

                    } else {
                        logger.error("第 {} 行数据为null", j);
                    }


                }
            } catch (Exception e) {
                logger.error(e.toString());
            }


        }

        //int numberOfSheets =workbook.getNumberOfSheets();
//       workbook.getSheetAt(0).get
        if (null != is) {
            is.close();
        }

        return tList;
    }

    public static <T> T setEntityFieldValue(Class<T> clazz, T instance, Map field2Value) {

        /**
         * @Author wangwen
         * @Description /通过字段名称反射方式赋值，注意，字段名称要按照小驼峰规则命名
         * @Date 17:54 2020/1/12
         * @param fieldName
         * @param clazz
         * @param instance
         * @return
         **/
        Set<Map.Entry> field2ValueEntrySet = field2Value.entrySet();

        try {
            for (Map.Entry entry : field2ValueEntrySet) {
                String fieldName = (String) entry.getKey();
                String fieldStringValue = (String) entry.getValue();
                Class<?> type = clazz.getDeclaredField(fieldName).getType();

                // 首字母大写
                String replace = fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);  //将字段小驼峰规则首字母变大写，因为规范的set get 方法是get/set+首字母大写的fieldName
//获得setter方法
                Method setMethod = clazz.getMethod("set" + replace, type);//// TODO: 2020/1/12  这种方式有一定约束性，如果用户编码不规范，各种字段或者方法命名杂乱无章，后续可以改进通配模式
                if (fieldStringValue != null && !"".equals(fieldStringValue)) {
                    // ---判断读取数据的类型
                    logger.info("插入值 {} --{}", fieldName, fieldStringValue);
                    if (type.isAssignableFrom(String.class)) {
                        setMethod.invoke(instance, fieldStringValue);
                    } else if (type.isAssignableFrom(int.class)
                            || type.isAssignableFrom(Integer.class)) {
                        setMethod.invoke(instance, Integer.parseInt(fieldStringValue));
                    } else if (type.isAssignableFrom(Double.class)
                            || type.isAssignableFrom(double.class)) {
                        setMethod.invoke(instance, Double.parseDouble(fieldStringValue));
                    } else if (type.isAssignableFrom(Boolean.class)
                            || type.isAssignableFrom(boolean.class)) {
                        setMethod.invoke(instance, Boolean.parseBoolean(fieldStringValue));
                    } else if (type.isAssignableFrom(Date.class)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        setMethod.invoke(instance, dateFormat.parse(fieldStringValue));
                    } else {
                        setMethod.invoke(instance, null);
                        logger.info("暂不支持自定义字段类型,该字段赋null ");
                    }
//                    else if (type.isAssignableFrom(Timestamp.class)) {
//                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                        setMethod.invoke(instance, new Timestamp(dateFormat.parse(str).getTime()));
//                    }
                } else {

                    setMethod.invoke(instance, null);
                }


            }

            //  setMethod.invoke(instance,)

        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }
        return instance;
    }

    public static <T> Map<String, String> getAnnotation2Field(Class<T> entityClass) {

        /**
         * @Author wangwen
         * @Description //带有 FieldAtExcel 注解的字段，获取excleFieldName 和字段，名称的映射关系
         * @Date 16:39 2020/1/12
         * @param entityClass
         * @return
         **/
        Map<String, String> map = new HashMap<>(); //FieldAtExcel 注解名称和字段名称map
        Field[] entityClassFields = entityClass.getDeclaredFields();
        for (int i = 0; i < entityClassFields.length; ++i) {
            entityClassFields[i].setAccessible(true);
            boolean isFieldAtExcelAnnotationPresent = entityClassFields[i].isAnnotationPresent(FieldAtExcel.class);
            if (isFieldAtExcelAnnotationPresent) {
                FieldAtExcel fieldAtExcel = entityClassFields[i].getAnnotation(FieldAtExcel.class);
                map.put(fieldAtExcel.excleFieldName(), entityClassFields[i].getName());
                logger.info("映射关系 fieldAtExcel.excleFieldName()={} ,field.getName() ={} ", fieldAtExcel.excleFieldName(), entityClassFields[i].getName());
            }

        }
        return map;
    }

    public static Map<Integer, String> getFieldNameColumeMap(Row row) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        int lastColume = row.getLastCellNum();
        int firstColume = row.getFirstCellNum();
        for (int col = firstColume; col < lastColume; ++col) {
            Cell cell = row.getCell(col);
            String name = getCellValue(cell);
            map.put(col, name);
        }
        logger.info(map.toString());
        return map;
    }

    public static String getCellValue(Cell cell) {

        /**
         * @Author wangwen
         * @Description //获取表格元素值，统一转成字符串
         * @Date 15:15 2020/1/12
         * @param cell
         * @return
         **/
        String cellValue = "";
        switch (cell.getCellType()) {
            case STRING: {
                cellValue = cell.getStringCellValue();
                if (cellValue.trim().equals("") || cellValue.trim().length() <= 0) {
                    cellValue = " ";
                }
                break;
            }
            case NUMERIC: {
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            }
            case BOOLEAN: {
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            }
            case BLANK: {
                cellValue = " ";// 空格
                break;
            }
            case FORMULA: {
                cellValue = cell.getCellFormula();
                break;
            }
            case ERROR: {
                break;
            }
            case _NONE: {

            }
            default: {
                return "";
            }
        }

        return cellValue;
    }

    public static class ExcelElement {
        private Workbook workbook = null;
        private Sheet sheet = null;
        private Row row = null;

        public Workbook getWorkbook() {
            return workbook;
        }

        public void setWorkbook(Workbook workbook) {
            this.workbook = workbook;
        }

        public Sheet getSheet() {
            return sheet;
        }

        public void setSheet(Sheet sheet) {
            this.sheet = sheet;
        }

        public Row getRow() {
            return row;
        }

        public void setRow(Row row) {
            this.row = row;
        }
    }
}
