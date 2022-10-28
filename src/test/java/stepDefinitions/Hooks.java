package stepDefinitions;

import cucumber.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;

public class Hooks {
	TestContext testContext;

	public Hooks(TestContext context) {
		testContext = context;
	}

	@BeforeAll
	public static void beforeAll() {
		System.out.println("before all scenarios executed here");
	}
	
	@Before
	//executed before each scenario
	public void beforeSteps() {
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
	public void afterSteps() {
		System.out.println("aftersteps executed here");
		testContext.getWebDriverManager().quitDriver();
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("after all scenarios executed here");
	}
}
