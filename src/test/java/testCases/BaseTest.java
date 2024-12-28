package testCases;

import java.lang.constant.Constable;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;


import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Config;
import utils.Constants;
import utils.ResourceLoader;
import utils.TestListeners;

@Listeners({TestListeners.class})
public abstract class BaseTest {
	
	protected WebDriver driver;
	
	private static final Logger log = LoggerFactory.getLogger(ResourceLoader.class);

	@BeforeSuite
	public void setupConfig() {
			Config.initialize();
	}
	
    @BeforeTest
    public void setDriver(ITestContext ctx) throws MalformedURLException{
    	
    	this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED)) ? getRemoteDriver() : getLocalDriver();
    	ctx.setAttribute(Constants.DRIVER, this.driver);
    	
//    	if(Boolean.getBoolean(Config.get(Constants.GRID_ENABLED))) {
//    		this.driver = getRemoteDriver();
//    	}else
//    	{
//    		this.driver = getLocalDriver();
//    	}
    }
    
    @SuppressWarnings("deprecation")
	private WebDriver getRemoteDriver() throws MalformedURLException {
    	
    	Capabilities capabilities =new ChromeOptions();
//    	((ChromiumOptions<ChromeOptions>) capabilities).addArguments("--headless");
//    	capabilities.addArguments("--no-sandbox");
//    	capabilities.addArguments("--disable-dev-shm-usage");
    	
    	if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
    		capabilities = new FirefoxOptions();
    	}
    	
    	String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
    	String hubHost = Config.get(Constants.GRID_HUB_HOST);
    	String url = String.format(urlFormat, hubHost);
    	log.info("grid URL: "+url);
    	return new RemoteWebDriver(new URL(url),capabilities);
    }
    
    private WebDriver getLocalDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
    
    
}
