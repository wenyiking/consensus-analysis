package com.akfd.algorithms.base.sort;/*
 * FileName：DecisionControlController
 * Description：排序算法合集
 *
 * History：
 * @author wangwen
 * @date 2019/10/7
 * @update 新建文件
 */

import com.akfd.algorithms.base.define.ExceptionDefine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author wangwen7
 * @version v1.0
 * @date 2019/10/7 19:57
 */
public class SortAlgorithms {
    private final static Logger LOG = LoggerFactory.getLogger(SortAlgorithms.class);

    public synchronized static <T extends Comparable<? super T>> T[] quickSort(T[] originalArray, int start, int end) {
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

    private static <T extends Comparable<? super T>> int getPivot(T[] tempArray, int start, int end) throws ExceptionDefine {
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

//    public synchronized static <T extends Comparable<? super T>> T[] mergeSort(T[] originalArray, int start, int end){
//
//    }
}
