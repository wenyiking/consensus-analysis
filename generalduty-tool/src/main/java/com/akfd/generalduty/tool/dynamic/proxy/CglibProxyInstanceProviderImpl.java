package com.akfd.generalduty.tool.dynamic.proxy;

import com.akfd.generalduty.tool.dynamic.proxy.interfaces.ProxyInstanceProvider;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Classname CglibProxyInstanceProviderImpl
 * @Description TODO
 * @Date 2020/3/1 20:47
 * @Created by wangwen
 */
public class CglibProxyInstanceProviderImpl implements ProxyInstanceProvider {

    @Override
    public Object newDynamicProxy(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object original, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                return methodProxy.invokeSuper(original, objects);
            }
        });
        return enhancer.create();
    }
}
