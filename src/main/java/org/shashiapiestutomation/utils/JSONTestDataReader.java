package org.shashiapiestutomation.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Map;

public class JSONTestDataReader {

    private static final Logger logger = LogManager.getLogger(JSONTestDataReader.class);
    public  static Map<String,String> jsonResponseMap =null;

    public static String getJsonPathValueFromResponseFile(String responseFile,String key) throws IOException {
       String filePath = "testData/testPropertyFiles/"+responseFile+".properties";
        jsonResponseMap = FileUtils.loadPropertiesToMap(filePath);
        return getJsonKeyFromMap(key);
    }

    public  static  String getJsonKeyFromMap(String key){
        String value=null;
        if(jsonResponseMap.containsKey(key))
            value=jsonResponseMap.get(key);
        else
            logger.info("Given JSON Path for the key "+key+" is not found");

        return value;
    }
}
