package managers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import enums.DriverType;
import enums.EnvironmentType;

public class WebDriverManager {
	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}

	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}

	private WebDriver createDriver() {
		   switch (environmentType) {	    
	        case LOCAL : driver = createLocalDriver();
	        	break;
	        case REMOTE : driver = createRemoteDriver();
	        	break;
		   }
		   return driver;
	}

	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}

	private WebDriver createLocalDriver() {
        switch (driverType) {	    
        case FIREFOX : driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
        	driver = new ChromeDriver();
    		break;
        case INTERNETEXPLORER : driver = new InternetExplorerDriver();
    		break;
        }

        if(FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize()) driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait()));
		return driver;
	}	

	public void quitDriver() {
		driver.quit();
		//driver.close();	
	}
}
