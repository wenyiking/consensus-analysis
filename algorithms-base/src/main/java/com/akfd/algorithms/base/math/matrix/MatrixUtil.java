package com.akfd.algorithms.base.math.matrix;/*

 * FileName：MatrixUtil
 * Description：矩阵操作类，定义内部类 matrix
 *
 * History：
 * @author wangwen7
 * @date 2019/10/28
 * @update 新建文件
 */

import com.akfd.algorithms.base.sort.SortAlgorithms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wangwen7
 * @version v1.0
 * @date 2019/10/28 0:01
 */
public class MatrixUtil<T extends Number & Comparable<? super T>> {
    private final static Logger LOG = LoggerFactory.getLogger(SortAlgorithms.class);

//    public  Matrix matrixMultipy(){
//
//    }


    class Matrix<T extends Number & Comparable<? super T>> {

        private long colNum;
        private long rowNum;
        private T[][] data;

        private Matrix(T[][] data) {
            if (null != data) {
                this.data = data;
                rowNum = data.length;
                colNum = data[0].length;
            } else {
                LOG.error("数组为空");
            }


        }


        public long getColNum() {
            return colNum;
        }

        public void setColNum(long colNum) {
            this.colNum = colNum;
        }

        public long getRowNum() {
            return rowNum;
        }

        public void setRowNum(long rowNum) {
            this.rowNum = rowNum;
        }

        public T[][] getData() {
            return data;
        }

        public void setData(T[][] data) {
            this.data = data;
        }
    }
}
