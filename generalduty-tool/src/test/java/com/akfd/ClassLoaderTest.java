package com.akfd;

import com.akfd.generalduty.tool.udf.UDFClassLoader;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.logging.Level;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname ClassLoaderTest
 * @Description TODO
 * @Date 2020/2/23 10:58
 * @Created by wangwen7
 */

public class ClassLoaderTest {


   @Test
    public void testClassLoader(){
       UDFClassLoader udfClassLoader=new UDFClassLoader.Build("D:\\德阳文件\\德阳版交通控制决策引擎代码分支\\交通控制决策引擎德阳版branch\\tcd-root\\tcd-common\\target")
               .classMapinitialCapacity(1024)
               .loggerLever(Level.INFO)
               .Build();
       try{
          Class<?> featureClass=  udfClassLoader.loadClass("com.hikvision.rd.gravelines.tcd.common.entity.result.cycleResult.TcdGreenwaveCycleCauseTrunk");

           Field[] fields=featureClass.getDeclaredFields();
           for(Field field:fields){
               System.out.println(field.getName());
           }

       }catch (Exception e){

       }



    }

}
