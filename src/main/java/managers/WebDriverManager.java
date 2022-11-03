package managers;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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

	public WebDriver getDriver(){
		if (driver == null)
			driver = createDriver();
		
//		rename extent html report file
//		String str = driver.toString().replaceAll("\\s","");
//		String driverRunInfo = str.substring(str.indexOf(":")+1, str.indexOf("("));
//		GregorianCalendar calendar = new GregorianCalendar();		
//		try {
//		FileWriterManager.getInstance().getExtentWriter().setProperty("extent.reporter.spark.out", "target/cucumber-reports/report-"+String.valueOf(calendar.getTimeInMillis())+"-"+driverRunInfo+".html");		
//		}catch(IOException e){
//			System.out.println("Error while writing in extent properties file "+e);
////		}
		
		return driver;
	}

	private WebDriver createDriver() {
		switch (environmentType) {
		case LOCAL:
			driver = createLocalDriver();
			break;
		case REMOTE:
			driver = createRemoteDriver();
			break;
		}
		return driver;
	}

	private WebDriver createRemoteDriver() {
		try {
			switch (driverType) {
			case FIREFOX:
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				driver = new RemoteWebDriver(new URL(FileReaderManager.getInstance().getConfigReader().getSeleniumHub()), firefoxOptions);
				break;
			case CHROME:
				ChromeOptions chromeOptions = new ChromeOptions();
				// chromeOptions.setPlatformName("WIN10");
				driver = new RemoteWebDriver(new URL(FileReaderManager.getInstance().getConfigReader().getSeleniumHub()), chromeOptions);
				break;
			}
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return driver;
	}

	private WebDriver createLocalDriver() {
		switch (driverType) {
		case FIREFOX:
			driver = new FirefoxDriver();
			break;
		case CHROME:
			System.setProperty(CHROME_DRIVER_PROPERTY,
					FileReaderManager.getInstance().getConfigReader().getDriverPath());
			driver = new ChromeDriver();
			break;
		// case INTERNETEXPLORER : driver = new InternetExplorerDriver();
		// break;
		}

		if (FileReaderManager.getInstance().getConfigReader().getBrowserWindowSize())
			driver.manage().window().maximize();
		new WebDriverWait(driver,
				Duration.ofSeconds(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait()));
		return driver;
	}

	public void quitDriver() {
		driver.quit();
		// driver.close();
	}
}
