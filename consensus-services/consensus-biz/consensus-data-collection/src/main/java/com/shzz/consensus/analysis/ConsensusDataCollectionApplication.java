package com.shzz.consensus.analysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Copyright (C) 2018 HangZhou Hikvision System Technology Co., Ltd. All Right Reserved.
 * Description：http://www.hikvision.com
 *
 * @Classname com.shzz.consensus.analysis.ConsensusDataCollectionApplication
 * @Description TODO
 * @Date 2019/12/18 15:42
 * @Created by wangwen7
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.shzz.consensus.analysis")
public class ConsensusDataCollectionApplication {
    private final static Logger logger = LoggerFactory.getLogger(ConsensusDataCollectionApplication.class);

    public static void main(String[] args) {
        String OS = System.getProperty("os.name").toLowerCase();

        if (OS.toLowerCase().indexOf("windows") >= 0) {
            System.setProperty("hadoop.home.dir", "D:\\hadoop-common-2.2.0-bin-master");
        }

        SpringApplication.run(ConsensusDataCollectionApplication.class, args);
        logger.info("ConsensusDataCollectionApplication start to run ");

    }
}
