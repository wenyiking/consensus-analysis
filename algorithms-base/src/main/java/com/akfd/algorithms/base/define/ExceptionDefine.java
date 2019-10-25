package com.akfd.algorithms.base.define;/*
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * FileName：DecisionControlController
 * Description：http://www.hikvision.com
 *
 * History：
 * @author wangwen7
 * @date 2019/10/9
 * @update 新建文件
 */

/**
 * @author wangwen7
 * @version v1.0
 * @date 2019/10/9 16:49
 */
public class ExceptionDefine extends Exception{
    private String description;

    public  ExceptionDefine(String description){
        this.description=description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExceptionDefine{" +
                "description='" + description + '\'' +
                '}';
    }
}
