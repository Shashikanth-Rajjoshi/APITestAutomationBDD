package StepDefs;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.shashiapiestutomation.utils.JSONTestDataReader;
import org.shashiapiestutomation.utils.ScenarioContext;
import org.slf4j.ILoggerFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.shashiapiestutomation.utils.JSONTestDataReader.getJsonPathValueFromResponseFile;

public class CustomResponseStepDef {

    public static ScenarioContext context;
    private static final Logger logger = LogManager.getLogger(CustomResponseStepDef.class);

    public CustomResponseStepDef(ScenarioContext context){
        this.context = context;
    }
    @Then("I should receive http response code as {string}")
    public void iShouldReceiveHttpResponseCodeAs(String expStatusCode) {
        String httpStatusCode = String.valueOf(context.getPreviousResponse().statusCode());
        Assert.assertEquals(httpStatusCode,expStatusCode);
    }

    @Then("I should see the following values in response")
    public void iShouldSeeTheFollowingValuesInResponse(DataTable table) throws IOException {
        List<Map<String, String>> list = table.asMaps(String.class,String.class);
        String responseValue=null;String expectedValue="";
        for(int i=0;i<list.size();i++){
            String jsonResponseFile = list.get(0).get("ResponseFileName");
            String apiName = list.get(0).get("APIName");
            String jsonResponseAsString = String.valueOf(context.getDataStore().get(apiName));
            for(String key : list.get(i).keySet()){
                if(!key.equalsIgnoreCase("APIName") && !key.equalsIgnoreCase("ResponseFileName")){
                try {
                    String pathValue = getJsonPathValueFromResponseFile(jsonResponseFile,key);
                    responseValue = JsonPath.read(jsonResponseAsString,pathValue).toString();
                    logger.info("Response value for Key "+key+" is "+responseValue);
                }catch (NullPointerException | PathNotFoundException np){
                    responseValue="null";
                }
                expectedValue = list.get(i).get(key);
                    assertThat(responseValue)
                            .as("Failed to verify Response Parameter value is: %s and Expected value is %s: ",responseValue,expectedValue)
                            .isEqualTo(expectedValue);
                }
            }
        }
    }
}
