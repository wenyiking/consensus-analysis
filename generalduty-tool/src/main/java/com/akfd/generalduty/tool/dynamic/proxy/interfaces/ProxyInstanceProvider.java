package com.akfd.generalduty.tool.dynamic.proxy.interfaces;

import java.lang.reflect.Proxy;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname ProxyInstanceProvider
 * @Description TODO
 * @Date 2020/2/28 18:18
 * @Created by wangwen7
 */
public interface ProxyInstanceProvider {
    Object newDynamicProxy(Object target);

}
