package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/functionalTests",
		stepNotifications = true,
		glue= {"stepDefinitions"},
		plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:","html:target/cucumber-reports/cucumber.html", "json:target/cucumber-reports/Cucumber.json", "junit:target/cucumber-reports/Cucumber.xml" },
		monochrome = true
		)
public class TestRunner {
	

}
