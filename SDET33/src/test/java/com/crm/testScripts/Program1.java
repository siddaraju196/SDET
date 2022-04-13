package com.crm.testScripts;

import org.openqa.selenium.WebDriver;

import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriver_Utility;
import com.crm.objectRepository.LoginPage;

public class Program1 {
static WebDriver driver;
	public static void main(String[] args) throws Throwable {
		WebDriver_Utility.initializeDriver(driver);
 driver=WebDriver_Utility.openBrowser(driver, PropertyUtility.initialize("url"), 0);
 LoginPage login=new LoginPage(driver);
 login.loginAction(PropertyUtility.initialize("username"), PropertyUtility.initialize("password"));
 
	}

}
