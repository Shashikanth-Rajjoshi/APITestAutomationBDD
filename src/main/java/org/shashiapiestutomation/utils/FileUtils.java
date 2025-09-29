package org.shashiapiestutomation.utils;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

    public static Map<String, String> loadPropertiesToMap(String filePath) throws IOException {
        Properties properties = new Properties();
        Map<String, String> map = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        }

        for (String key : properties.stringPropertyNames()) {
            map.put(key, properties.getProperty(key));
        }
        return map;
    }
}
