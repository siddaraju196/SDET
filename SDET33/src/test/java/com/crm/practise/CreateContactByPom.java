package com.crm.practise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriver_Utility;
import com.crm.objectRepository.ContactPage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactByPom {

	static WebDriver driver=null;
	public static void main(String[] args) throws Throwable {

		String propertyfilepath = ConstantPath.PROPERTYFILEPATH;
		String excelpath = ConstantPath.EXCELPATH;
		PropertyUtility.initialize(propertyfilepath);
		String url = PropertyUtility.fetchString("url");
		String username = PropertyUtility.fetchString("username");
		String password = PropertyUtility.fetchString("password");
		String timeout = PropertyUtility.fetchString("timeout");
		long timeouts = JavaUtility.convertStringToLong(timeout);

		driver=WebDriver_Utility.initializeDriver(driver, timeouts, url);
		LoginPage loginpage=new LoginPage(driver);
		loginpage.loginAction();
		ContactPage hp= new ContactPage(driver);
		hp.saveAction();
		WebElement expName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
		if(expName.getText().contains("BattleField"))
		{
			System.out.println("last name is correct");
		}
		else
		{
			System.out.println("last name is not correct");
		}
		
		
	}
}