package com.akfd.algorithms.base.math.matrix;

/**
 * @Classname DynamicPlanning
 * @Description TODO
 * @Date 2019/11/17 14:23
 * @Created by wangwen
 */
public class DynamicPlanning {

    public static void MatrixMuitipy(int[] dimensionChain, int matrixNum) {


    }

    public static boolean inputCheck(int[] dimensionChain, int matrixNum) {

        return true;
    }

    static class MultiplyContinuouslyRecord {

        private int from;
        private int end;
        private long minNum;
        private int markPosition;

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public long getMinNum() {
            return minNum;
        }

        public void setMinNum(long minNum) {
            this.minNum = minNum;
        }

        public int getMarkPosition() {
            return markPosition;
        }

        public void setMarkPosition(int markPosition) {
            this.markPosition = markPosition;
        }
    }

}

