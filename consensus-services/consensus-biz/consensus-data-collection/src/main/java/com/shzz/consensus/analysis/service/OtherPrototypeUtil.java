package com.shzz.consensus.analysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname OtherPrototypeUtil
 * @Description TODO
 * @Date 2019/12/18 15:52
 * @Created by wangwen7
 */
@Component
public class OtherPrototypeUtil {
    public List<String> fieldList;
    @Autowired
    PrototypeTest prototypeTest;

    public int getPrototypeTest() {
        return prototypeTest.hashCode();
    }

    public void setListField() {
        List<String> strings = new ArrayList<>();
        strings.add("first");
        strings.add("second");
        fieldList = strings;
    }


}
