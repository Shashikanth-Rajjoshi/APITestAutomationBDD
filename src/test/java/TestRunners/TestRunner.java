package TestRunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


    @CucumberOptions(
            features = "src/test/resources/features",    // path to your feature files
            glue = {"StepDefs"},                  // path to your step definitions
            plugin = {"pretty", "html:target/cucumber-reports"}, // reports
            monochrome = true,                           // output formatting
            tags = "@TestId_GP_001"                          // (optional) run tagged scenarios only
    )
    public class TestRunner extends AbstractTestNGCucumberTests {
    }

