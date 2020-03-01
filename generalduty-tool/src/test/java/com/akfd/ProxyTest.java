package com.akfd;

import com.akfd.generalduty.tool.dynamic.proxy.DynamicProxyFactory;
import com.akfd.generalduty.tool.dynamic.proxy.ProxyInstanceProviderImpl;
import com.akfd.interfaces.TestProxyInterface;
import com.akfd.interfaces.impl.TestProxyInterfaceImpl;
import org.junit.Test;

/**
 * @Classname ProxyTest
 * @Description TODO
 * @Date 2020/3/1 22:13
 * @Created by wangwen
 */
public class ProxyTest {

    @Test
    public void testJDKDynamicProxy() {
        TestProxyInterfaceImpl testProxyInterfaceImpl = new TestProxyInterfaceImpl();
        ProxyInstanceProviderImpl proxyInstanceProvider = new ProxyInstanceProviderImpl();
        proxyInstanceProvider.newDynamicProxy(testProxyInterfaceImpl);
        DynamicProxyFactory.registerProvider("ProxyInstanceProviderImpl", proxyInstanceProvider);
        try {
            // TestProxyInterfaceImpl proxyInterfaceImpl= (TestProxyInterfaceImpl)proxyInstanceProvider.newDynamicProxy(testProxyInterfaceImpl);
            TestProxyInterface proxyInterfaceImpl = (TestProxyInterface) DynamicProxyFactory.newProxyInstance("ProxyInstanceProviderImpl", testProxyInterfaceImpl);
            proxyInterfaceImpl.test1();
        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }

}
