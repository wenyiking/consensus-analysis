package com.akfd.test;

import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayInputStream;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname UnitTest
 * @Description TODO
 * @Date 2020/1/7 10:13
 * @Created by wangwen7
 */

public class UnitTest {
    @Test
    public void testIo() {
        System.out.println("int.class.isAssignableFrom(int.class) =" + int.class.isAssignableFrom(int.class));


        byte[] bytes = new byte[40];
        for (int j = 0; j < 40; ++j) {
            bytes[j] = (byte) Math.round(Math.random() * 127);

        }
//        int k=0;
//        for(byte b:bytes){
//            b=(byte) Math.round(Math.random()*127);
//            System.out.print(bytes[k]);
//            System.out.print("--");
//            System.out.println(b);
//            ++k;
//        }
        printBytes(bytes);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        byte[] bufferBytes = new byte[35];
        int start = 0;
        int bufferSize = 5;
        System.out.println("byteArrayInputStream.available()==" + byteArrayInputStream.available());
        int ava = byteArrayInputStream.available();
        for (int i = 0; (i + bufferSize - 1) < ava - 5; ) {
            //  System.out.println("byteArrayInputStream.available()=="+byteArrayInputStream.available());
            int len = byteArrayInputStream.read(bufferBytes, i, bufferSize);

            i += len;

            System.out.println("current  pos==" + i);
            System.out.println("byteArrayInputStream.available()=" + byteArrayInputStream.available());
            if (byteArrayInputStream.markSupported() && (i == 10)) {
                System.out.println("可标记重置  byteArrayInputStream.mark(10)");
                byteArrayInputStream.mark(5);
                System.out.println("byteArrayInputStream.available()=" + byteArrayInputStream.available());

//                byteArrayInputStream.skip(10);
//                System.out.println("byteArrayInputStream.available()="+byteArrayInputStream.available());
            }
            if (i == 30) {
                byteArrayInputStream.reset();
                System.out.println(" byteArrayInputStream.reset() byteArrayInputStream.available()=" + byteArrayInputStream.available());
            }
            printBytes(
                    bufferBytes
            );
        }

    }

    public void printBytes(byte[] bytes) {
        for (byte b : bytes) {
            System.out.print(b);
            System.out.print("  ");
        }
        System.out.println();
    }


    public void testCompiler() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    }
}
