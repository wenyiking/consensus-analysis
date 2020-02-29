package com.akfd.generalduty.tool.dynamic.proxy;

import com.akfd.generalduty.tool.dynamic.proxy.interfaces.ProxyInstanceProvider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname DynamicProxyFactory
 * @Description TODO
 * @Date 2020/2/28 18:15
 * @Created by wangwen7
 */

public class DynamicProxyFactory {
    private static final Logger LOG = Logger.getLogger("DynamicProxyFactory");
    private DynamicProxyFactory(){ }
    private static final Map<String, ProxyInstanceProvider> PROVIDERS=new ConcurrentHashMap<String, ProxyInstanceProvider>(1024);
    public static final String  DEFAULT_PROVIDER_NAME="com.akfd.generalduty.tool.dynamic.proxy.interfaces.DynamicProxyProviderImpl";
    public static void registerDefaultProvider(ProxyInstanceProvider dynamicProxyProvider){
            PROVIDERS.put(DEFAULT_PROVIDER_NAME,dynamicProxyProvider);

    }

    public static void registerProvider(String name, ProxyInstanceProvider dynamicProxyProvider){
        if(!PROVIDERS.containsKey(name)){
            PROVIDERS.put(name,dynamicProxyProvider);
        }else{
            LOG.info("已存在 "+ name +" 代理提供者");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T newProxyInstance(String name,T target) throws Exception{
        ProxyInstanceProvider proxyInstanceProvider= PROVIDERS.get(name);
        T proxyInstance=null;
        if(null!=proxyInstanceProvider){
             proxyInstance=(T) proxyInstanceProvider.newDynamicProxy(target);
        }else{
            throw new Exception("no provider is register"+ name);
        }
        return  proxyInstance;
    }

    public static <T> T newProxyInstance(T target) throws  Exception{
        ProxyInstanceProvider proxyInstanceProvider= PROVIDERS.get(DEFAULT_PROVIDER_NAME);
        T proxyInstance=null;
        if(null!=proxyInstanceProvider){
            proxyInstance=(T) proxyInstanceProvider.newDynamicProxy(target);
        }else{
            throw new Exception("no provider is register"+ DEFAULT_PROVIDER_NAME);
        }
        return  proxyInstance;
    }
}
