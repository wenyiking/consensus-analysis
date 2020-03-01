package com.akfd.interfaces.impl;

import com.akfd.interfaces.TestProxyInterface;

/**
 * @Classname TestProxyInterfaceImpl
 * @Description TODO
 * @Date 2020/3/1 22:16
 * @Created by wangwen
 */
public class TestProxyInterfaceImpl implements TestProxyInterface {
    @Override
    public void test1() {
        System.out.println("TestProxyInterfaceImpl test1");
    }

    @Override
    public void test2() {
        System.out.println("TestProxyInterfaceImpl test2");
    }
}
