package com.shzz.consensus.analysis.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname FieldAtExcel
 * @Description TODO
 * @Date 2020/1/6 14:30
 * @Created by wangwen7
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldAtExcel {
    String excleFieldName() default "";

    boolean nullPermission() default false;
}
