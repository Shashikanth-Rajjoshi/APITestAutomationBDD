package org.shashiapiestutomation.utils;

import com.jayway.jsonpath.DocumentContext;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.Map;

public class JSONTestDataReader {

    private static final Logger logger = LogManager.getLogger(JSONTestDataReader.class);
    public  static Map<String,String> jsonReqResponseMap =null;

    public static String getJsonPathValueFromResponseFile(String responseFile,String key) throws IOException {
       String filePath = "testData/responsePropertyFiles/"+responseFile+".properties";
        jsonReqResponseMap = FileUtils.loadPropertiesToMap(filePath);
        return getJsonKeyFromMap(key);
    }

    public static String getJsonPathValueFromRequestFile(String requestFile,String key) throws IOException {
        String filePath = "testData/requestPropertyFiles/"+requestFile+".properties";
        jsonReqResponseMap = FileUtils.loadPropertiesToMap(filePath);
        return getJsonKeyFromMap(key);
    }

    public static String getJsonKeyFromMap(String key){
        String value=null;
        if(jsonReqResponseMap.containsKey(key))
            value=jsonReqResponseMap.get(key);
        else
            logger.info("Given JSON Path for the key "+key+" is not found");

        return value;
    }

    public static boolean jsonPathExists(DocumentContext parsed, String jsonPathValue){
        String pathData="";
        try{
            pathData = parsed.read(jsonPathValue).toString();
        }catch (NullPointerException e){
            pathData =null;
        }
        if(pathData==null)
            return false;
        else
            return true;
    }

    public static String[] getNewRequestParameterKeyAndPath(String jsonPathValue){
        String[] params = jsonPathValue.split("\\.");
        String keyName = params[params.length-1];
        String keyPath ="$";
        for(int i=1;i<params.length-1;i++){
            keyPath = keyPath+"."+params[i];
        }
        String[] ar = {keyPath,keyName};
        return ar;
    }

    public static String updateJsonRequest(DocumentContext parsedJson,String jsonFileName, String key, String value) throws IOException {
        String jsonPathValueFromReq = getJsonPathValueFromRequestFile(jsonFileName,key);
       if(jsonPathExists(parsedJson,jsonPathValueFromReq)){
           if(value.equalsIgnoreCase("REMOVE")){
               parsedJson.delete(jsonPathValueFromReq);
           }else {
               parsedJson.set(jsonPathValueFromReq,value);
           }
       }else{
           String[] pathNKey=getNewRequestParameterKeyAndPath(jsonPathValueFromReq);
           parsedJson.put(pathNKey[0],pathNKey[1],value);
       }
       return parsedJson.jsonString();
    }
}
