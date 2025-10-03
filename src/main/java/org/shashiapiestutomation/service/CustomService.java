package org.shashiapiestutomation.service;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Map;

public class CustomService {

    private static final Logger logger = LogManager.getLogger(CustomService.class);


    public Response postAPI(Map<String,String> headerMap, Object request, String url){
            Response response = RestAssured.given().contentType(String.valueOf(ContentType.APPLICATION_JSON)).headers(headerMap).and().body(request).when().post(url);

            return response;
    }

    public Response getAPI(String url){
        logger.info("Get Url is: "+url);
        Response response = RestAssured.get(url);

        return response;
    }

}
