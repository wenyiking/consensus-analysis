package com.shzz.consensus.analysis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname UsePrototype
 * @Description TODO
 * @Date 2019/12/18 15:50
 * @Created by wangwen7
 */
@Component
public class UsePrototype implements ApplicationRunner {
    private final static Logger logger = LoggerFactory.getLogger(UsePrototype.class);

    @Autowired
    PrototypeTest prototypeTest;

    @Autowired
    OtherPrototypeUtil otherPrototypeUtil;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 13, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        logger.info("线程池创建线程: " + r.toString());
                        return new Thread(r, "threadPool" + r.toString());
                    }
                },
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        logger.warn("拒绝执行策略  " + r.toString());
                    }
                });

        for (int i = 0; i < 20; ++i) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    logger.info("thread exec in threadPool  = " + threadPoolExecutor.toString());

                    try {
                        Thread.sleep(1);
                    } catch (Exception e) {
                        logger.info(e.toString());
                    }
                }
            });

            // threadPoolExecutor.execute(thread);
        }
        otherPrototypeUtil.setListField();
        System.out.println("获取单例字段值：" + otherPrototypeUtil.fieldList.toString());
        System.out.println("\"   \".trim().isEmpty()" + "   ".trim().isEmpty());

        System.out.println("UsePrototype prototypeTest.hashCode== " + prototypeTest.hashCode());

        System.out.println("OtherPrototypeUtil prototypeTest.hashCode== " + otherPrototypeUtil.getPrototypeTest());
    }
}
