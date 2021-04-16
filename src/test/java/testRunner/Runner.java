package testRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
        (
              features = ".//features/TC001_ManageUserList.feature",
               glue="stepDefinitions",
                dryRun=false,
                monochrome = true,
                plugin= {"pretty", "html:target/cucumber-html-reports.html","json:target/cucumber.json",
                }
        )
public class Runner {
}
