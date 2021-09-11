package com.nopCommerce.testCases;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nopCommerce.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig=new ReadConfig();
	public String baseURL=readConfig.getApplicationURL();
	public String username=readConfig.getUseremail();
	public String password=readConfig.getPassword();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@BeforeClass
	@Parameters("browser")
	public void setup(String br){
		
		logger = logger.getLogger("nopEcommerce");
		PropertyConfigurator.configure("Log4j.properties");
		
	//	System.setProperty("Webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe" );
		
		//Open Chrome browser
		if(br.equals("chrome")){
		System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
		driver = new ChromeDriver();
		}
		
		//Open Firefox browser
		else if(br.equals("firefox")){
		System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
		driver = new FirefoxDriver();
		}
		 
		//Open IE browser
		else {
			if(br.equals("ie")){
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
			}
		}

		
	}
		@AfterClass
		public void tearDown(){
		driver.close();
		}
		
		public void captureScreen(WebDriver driver, String tname) throws IOException { 
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
			FileUtils.copyFile(source, target);
			System.out.println("Screenshot taken");
		}
	
		public static String randomestring() {
			String generatedString1 = RandomStringUtils.randomAlphabetic(5);
			return (generatedString1);
		}

		public static String randomeNum() {
			String generatedString2 = RandomStringUtils.randomNumeric(4);
			return (generatedString2);
		}

}
	
	


