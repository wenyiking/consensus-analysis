//package com.akfd.test;
//
//
//import com.shzz.consensus.analysis.entity.VolumeForStatic;
//import com.shzz.consensus.analysis.tools.ExcelOperation;
//import org.junit.Test;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
///**
// * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
// * Description：http://www.hikvision.com
// *
// * @Classname ExcelTest
// * @Description TODO
// * @Date 2020/1/12 18:58
// * @Created by wangwen7
// */
//
//public class ExcelTest {
//    private final static Logger logger = LoggerFactory.getLogger(ExcelTest.class);
//
//    // C:\Users\wangwen7\Desktop\重要信息记录\静态流量导入协议\信号机时段流量表2019年12月17日10时53分09秒.xls
//    @Test
//    public void test1() {
//        try {
//            List<VolumeForStatic> volumeForStaticList = ExcelOperation.getExcelValue("C:\\Users\\wangwen7\\Desktop\\重要信息记录\\静态流量导入协议\\信号机时段流量表2019年12月17日10时53分09秒.xls"
//                    , true, VolumeForStatic.class);
//            for (VolumeForStatic volumeForStatic : volumeForStaticList) {
//                logger.info(volumeForStatic.toString());
//            }
//        } catch (Exception e) {
//            logger.error(e.toString());
//        }
//
//    }
//
//}
