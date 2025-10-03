package StepDefs;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.shashiapiestutomation.service.CustomService;
import org.shashiapiestutomation.utils.FileUtils;
import org.shashiapiestutomation.utils.ScenarioContext;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.shashiapiestutomation.utils.JSONTestDataReader.updateJsonRequest;


public class CommonAPIStepDef {

    public static ScenarioContext context;
    public CommonAPIStepDef(ScenarioContext context){
        this.context = context;
    }

    CustomService cs = new CustomService();
    private static final Logger logger = LogManager.getLogger(CommonAPIStepDef.class);

    public static String jsonrequest="";

    @When("I hit the url of get {string}")
    public void i_hit_the_get_url(String value) throws IOException {
        Response response = null;
        logger.info("hitting the Get URI of "+value);
        String baseURl = FileUtils.getConfigValue("baseURI");
        switch (value.toUpperCase()){
            case "PRODUCTS":
                String endpoint=baseURl+FileUtils.getConfigValue("getProductURI");
                response = cs.getAPI(endpoint);
                logger.info("Response of Get Products: "+response.asPrettyString());
                context.setResponseTransactionMap(value,response);
                context.setDataStore(value,response.getBody().asString().replace("\n",""));
                context.setPreviousResponse(response);
                break;
            default:
                logger.info("Enter Proper Value for the get URL");
                break;
        }

    }

    @When("I Add a New Product with Following Details")
    public void iAddANewProductWithFollowingDetails(DataTable table) throws IOException {
        List<Map<String, String>> list = table.asMaps(String.class,String.class);
        String jsonRequestFileName = list.get(0).get("RequestFileName");
        String apiName = list.get(0).get("APIName");
        Response response =null;
        jsonrequest="";
        Configuration config = Configuration.builder().options(Option.SUPPRESS_EXCEPTIONS).build();
        String jsonFilePath = "testData/requestJsonFiles/"+jsonRequestFileName+".json";
        File jsonFile = new File(jsonFilePath);
        DocumentContext parsedJson = JsonPath.using(config).parse(jsonFile);
        for(int i=0;i<list.size();i++){
            for(String key : list.get(i).keySet()){
                if(!key.equalsIgnoreCase("APIName") && !key.equalsIgnoreCase("RequestFileName")){
                    jsonrequest =  updateJsonRequest(parsedJson,jsonRequestFileName,key,list.get(i).get(key));
                }
            }
            Object jsonRequestObject = jsonrequest;
            String endpoint="";
            Map<String,String> headerMap =new HashMap<>();
            String baseURl = FileUtils.getConfigValue("baseURI");
            switch (apiName.toUpperCase()){
                case "ADD_PRODUCT":
                   endpoint = baseURl+FileUtils.getConfigValue("addProductURI");
                   logger.info("EndPoint: "+endpoint);
                   logger.info("Request Body of "+apiName+" is: "+jsonrequest);
                   response = cs.postAPI(headerMap,jsonRequestObject,endpoint);
                   logger.info("Response of Add Product : "+response.asPrettyString());
                   context.setResponseTransactionMap(apiName,response);
                   context.setDataStore(apiName,response.getBody().asString().replace("\n",""));
                   context.setPreviousResponse(response);
                   break;
                default:
                    logger.info("Enter Proper Value for the API Name");
                    break;
            }
        }

    }
}
