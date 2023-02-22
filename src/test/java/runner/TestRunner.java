package runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(glue = "stepDefinitions", features = "Features", publish = true, plugin = { "pretty",
		"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:" })
public class TestRunner extends AbstractTestNGCucumberTests {
	/*
	 * @Override
	 * 
	 * @DataProvider(parallel = false) public Object[][] scenarios() { return
	 * super.scenarios(); }
	 */
}
