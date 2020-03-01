package com.shzz.consensus.analysis.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname PrototypeTest
 * @Description TODO
 * @Date 2019/12/18 15:48
 * @Created by wangwen7
 */
@Component
@Scope("prototype")
public class PrototypeTest {
    public boolean flag;

    public void setFlag(boolean flageValue) {
        this.flag = flageValue;
    }
}
