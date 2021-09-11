package com.nopCommerce.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopCommerce.pageObjects.LoginPage;


public class TC_LoginTest_001 extends BaseClass{
	
	@Test
	public void loginTest() throws InterruptedException, IOException{
		
		driver.get(baseURL);
		
		logger.info("URL provided"); //logger msg
		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("User provided");
		
		lp.setPassword(password);
		logger.info("password provided");
		
		lp.clickLogin();
		logger.info("Login is clicked");
		
		Thread.sleep(5000);
		
		driver.manage().window().maximize();
		
		if(driver.getTitle().equals("Dashboard / nopCommerce administration")){
			
			Assert.assertTrue(true);
			lp.clickLogout();
			logger.info("Login passed");
		}
		
		else {
			
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
		
	}
}
	

	

