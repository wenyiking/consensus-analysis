package com.akfd.generalduty.tool.dynamic.proxy;

import com.akfd.generalduty.tool.dynamic.proxy.interfaces.ProxyInstanceProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname ProxyInstanceProviderImpl
 * @Description TODO
 * @Date 2020/2/28 18:30
 * @Created by wangwen7
 */

public class ProxyInstanceProviderImpl<T> implements ProxyInstanceProvider<T> {

    @SuppressWarnings("unchecked")
    @Override
    public Object newDynamicProxy(Object target) {
        T proxyIstance = (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = method.invoke(proxy, args);
                        return invoke;
                    }
                });

        return proxyIstance;

    }
}



