package StepDefs;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.shashiapiestutomation.service.CustomService;
import org.shashiapiestutomation.utils.FileUtils;
import org.shashiapiestutomation.utils.ScenarioContext;
import org.testng.Assert;

import java.io.IOException;


public class CommonAPIStepDef {

    public static ScenarioContext context;
    public CommonAPIStepDef(ScenarioContext context){
        this.context = context;
    }

    CustomService cs = new CustomService();
    private static final Logger logger = LogManager.getLogger(CommonAPIStepDef.class);

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
}
