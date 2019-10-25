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

/**
 * @author wangwen7
 * @version v1.0
 * @date 2019/10/8 11:18
 */
public class TestClass implements Comparable<TestClass> {
    private long age;
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(TestClass o) {
        return (int)(this.getAge()-o.getAge());
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "age=" + age +
                ", name='" + name + '\'' ;
    }
}


