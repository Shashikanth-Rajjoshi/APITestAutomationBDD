package org.shashiapiestutomation.utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class FileUtils {

    private static final Logger logger = LogManager.getLogger(FileUtils.class);
    public static String getConfigValue(String key) throws IOException {
        FileReader fr = new FileReader(System.getProperty("user.dir")+"/testData/config.properties");
        Properties pr = new Properties();
        pr.load(fr);
        logger.info("Getting Config value for key: "+key);
        return pr.getProperty(key);
    }
}
