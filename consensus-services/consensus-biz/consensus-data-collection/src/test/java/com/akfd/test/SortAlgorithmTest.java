package com.akfd.test;/*
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * FileName：DecisionControlController
 * Description：http://www.hikvision.com
 *
 * History：
 * @author wangwen7
 * @date 2019/10/8
 * @update 新建文件
 */


import com.akfd.algorithms.base.define.ExceptionDefine;
import com.akfd.algorithms.base.sort.SortAlgorithms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author wangwen7
 * @version v1.0
 * @date 2019/10/8 11:15
 */
public class SortAlgorithmTest {
    private final static Logger LOG = LoggerFactory.getLogger(SortAlgorithms.class);

    public static void main(String[] args) {
        //SortAlgorithms.quickSort()
        System.out.println((double) 1/3);

        for (int testTime = 0; testTime < 100; ++testTime) {

            TestClass[] testClassArray = new TestClass[200];
           // System.out.println("==============排序前===========：");
            for (int i = 0; i < testClassArray.length; ++i) {
                testClassArray[i] = new TestClass();
                long age = Math.round(Math.random() * 10000);
                testClassArray[i].setAge(age);
                testClassArray[i].setName("testname" + age);
              //  System.out.println(testClassArray[i].toString());

            }

            TestClass[] sortArray = SortAlgorithms.quickSort(testClassArray, 0, testClassArray.length - 1);
            // quickSort(testClass);

            boolean isSort = true;
            for (int i = 0; i < sortArray.length - 2; ++i) {
                if (sortArray[i].compareTo(sortArray[i + 1]) > 0) {
                    isSort = false;
                }

            }
            if (isSort == false) {
                System.out.println("本次排序结果不正确");
                System.out.println("==============排序后===========：");
                for (TestClass testClassTemp : testClassArray) {

                    System.out.println(testClassTemp.toString());
                }
            } else {
//                System.out.println("本次排序结果正确");
//                for (TestClass testClassTemp : testClassArray) {
//
//                    System.out.println(testClassTemp.toString());
//                }
            }
        }
    }


    public static <T extends Comparable<? super T>> T[] quickSort(T[] originalArray, int start, int end) {
        /**
         * @Description   快速排序算法，传入参数需要继承 Comparable 接口
         *  该方法线程安全，快速排序算法逻辑请上网查阅。
         * @Param    T[] originalArray, 待排序数组，int start 排序起始位置, int end 排序终止位置
         * @return  排序后的数组
         **/


        T[] sortArray = originalArray;// Arrays.copyOf(originalArray, originalArray.length);
        try {
            if (start >= end) {
                //LOG.info("本次pivot="+start);
                return sortArray;
            }
            int pivot = getPivot(sortArray, start, end);
            if (pivot == start) {
                // LOG.info("本次pivot="+pivot);

                //  return  sortArray;
            }
            // LOG.info("本次pivot="+pivot);
            quickSort(sortArray, start, pivot - 1);
            quickSort(sortArray, pivot + 1, end);

        } catch (ExceptionDefine e) {
            LOG.error(e.toString());
        }


        return sortArray;

    }

    public static <T extends Comparable<? super T>> int getPivot(T[] tempArray, int start, int end) throws ExceptionDefine {
        /**
         * @Description //快速排序寻找分界点
         * @Param T[] tempArray 待排序数组；
         * int start,int end  本次寻找分界点从参数指定的数组起始点下标和终止下标区间段开始
         * @return 返回分界点下标值
         **/

        if (start < 0) {

            throw new ExceptionDefine("getPivot方法 parameter start=" + start + " 小于数组下界");

        } else if (end > (tempArray.length - 1)) {

            throw new ExceptionDefine("getPivot方法 parameter end=" + end + "  大于数组上界");
        } else if (end < start) {
            throw new ExceptionDefine("getPivot方法 parameter end=" + end + "  小于 parameter start=" + start);
        } else if (start == end) {
            return start;
        }


        T markValue = tempArray[start];

        while (start < end) {

            if ((tempArray[end].compareTo(markValue) <= 0)) {
                tempArray[start] = tempArray[end];
                ++start;
                for (; start < end; ++start) {
                    if (tempArray[start].compareTo(markValue) >= 0) {
                        tempArray[end] = tempArray[start];
                        --end;
                        break;

                    }
                }


                tempArray[start] = markValue;


            } else {
                --end;
            }


        }


        tempArray[start] = markValue;


        return start;
    }
}
