package com.shzz.consensus.analysis.config;

import com.shzz.consensus.analysis.ConsensusDataCollectionApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Classname PropertiesProcess
 * @Description TODO
 * @Date 2020/3/21 13:31
 * @Created by wangwen7
 */
public class PropertiesProcess {
    private final static Logger LOG = LoggerFactory.getLogger(ConsensusDataCollectionApplication.class);

    public static Properties getProperties() {


        InputStream in = PropertiesProcess.class.getClassLoader().getResourceAsStream("config.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            in.close();
        } catch (IOException ioe) {
            LOG.error(ioe.getLocalizedMessage());

        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage());
        } finally {
            in = null;

        }
        return properties;
    }
}
