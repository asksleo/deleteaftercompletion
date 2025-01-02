package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import pageObjects.LandingPage;

public class TestBase {

	public WebDriver driver;
	
	public WebDriver WebDriverManager() throws IOException
	{
//		FileInputStream fis = new FileInputStream("src/test/resources/global.properties");
//		Properties prop = new Properties();
//		prop.load(fis);
//		// String url = prop.getProperty("QAUrl");
		String url ="https://rahulshettyacademy.com/seleniumPractise/#/";

//		String browser_properties = prop.getProperty("browser");
//		String browser_maven=System.getProperty("browser");
		String browser_properties = "Chrome";
		String browser_maven="Chrome";
		// result = testCondition ? value1 : value2
		
		String browser = browser_maven!=null ? browser_maven : browser_properties;
		
		
		
		if(driver == null)
		{
			if(browser.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();// driver gets the life
			}
			if(browser.equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver","//Users//rahulshetty//Downloads//geckodriver 5");
				driver = new FirefoxDriver();
			}
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(url);
		}
		
		return driver;
		
	}
	
	
	
}

