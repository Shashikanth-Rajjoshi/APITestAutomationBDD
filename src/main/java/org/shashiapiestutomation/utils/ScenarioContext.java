package org.shashiapiestutomation.utils;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private Response previousResponse;
    private Map<String, Object> requestTransactionMap = new HashMap<>();
    private Map<String, Object> responseTransactionMap = new HashMap<>();
    private Map<String, String> dataStore = new HashMap<>();

    public Response getPreviousResponse() {
        return previousResponse;
    }

    public void setPreviousResponse(Response previousResponse) {
        this.previousResponse = previousResponse;
    }

    public Map<String, Object> getRequestTransactionMap() {
        return requestTransactionMap;
    }

    public void setRequestTransactionMap(String txnName, Object requestObj) {
        this.requestTransactionMap.put(txnName,requestObj);
    }

    public Map<String, Object> getResponseTransactionMap() {
        return responseTransactionMap;
    }

    public void setResponseTransactionMap(String txnName, Object responseObj) {
        this.responseTransactionMap.put(txnName,responseObj);
    }

    public Map<String, String> getDataStore() {
        return dataStore;
    }

    public void setDataStore(String key, String value) {
        this.dataStore.put(key,value);
    }
}
