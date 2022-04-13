package com.crm.testScripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriver_Utility;
import com.crm.objectRepository.ClickOnJump;
import com.crm.objectRepository.ClickOnPrevious;
import com.crm.objectRepository.LoginPage;

public class TC_113_testScriptJump {
	static WebDriver driver;
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		String propertyFile = ConstantPath.PROPERTYFILEPATH;
		PropertyUtility.initialize(propertyFile);
		String url = PropertyUtility.fetchString("url");
		String username = PropertyUtility.fetchString("username");
		String password = PropertyUtility.fetchString("password");
		String timeout = PropertyUtility.fetchString("timeout");
		long timeouts = JavaUtility.convertStringToLong(timeout);
		driver= WebDriver_Utility.initializeDriver(driver, timeouts, url);
		WebDriver_Utility.waitImlpicit(driver, timeouts);
		LoginPage login= new LoginPage(driver);
		login.loginAction();
		ClickOnPrevious cop= new ClickOnPrevious(driver);
		cop.selectSavedContact();
		ClickOnJump coj= new ClickOnJump(driver);
		coj.jumpAction();
		WebElement jumpedContact = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
		if(jumpedContact.getText().contains("Jagan433"))
		{
			System.out.println("pass:: jumped to Jagan433");
		}
		else
		{
			System.out.println("fail:: not jumped to Jagan433");
		}



	}

}
