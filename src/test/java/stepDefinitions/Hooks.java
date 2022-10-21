package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	TestContext testContext;

	public Hooks(TestContext context) {
		testContext = context;
	}

	@Before
	//executed before each scenario
	public void BeforeSteps() {
		System.out.println("beforesteps executed here");
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
	}

	@After
	//executed after each scenario
	public void AfterSteps() {
		System.out.println("aftersteps executed here");
		testContext.getWebDriverManager().quitDriver();
	}
}
