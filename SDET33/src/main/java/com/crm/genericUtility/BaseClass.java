package com.crm.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
public HomePage hp;
	public WebDriver driver;
	public static WebDriver ddriver;
	@BeforeSuite
	// open database
	public void openDatabase() throws Throwable
	{
		PropertyUtility.initialize(ConstantPath.PROPERTYFILEPATH);
		DataBaseUtility.getMysqlDatabaseConnection1(ConstantPath.dbUrl,PropertyUtility.initialize("dbUserName"),PropertyUtility.initialize("dbPassword"));
	
	}
	@Parameters("browser")
	@BeforeClass
	// launch browser
	public void launchBrowser(String browser) throws Throwable
	{
		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}
		else
		{
			System.out.println("browser is not specified");
		}
		long timeout = JavaUtility.convertStringToLong(PropertyUtility.initialize("timeout"));
		// WebDriver_Utility.initializeDriver(driver); 
    driver=WebDriver_Utility.openBrowser(driver, PropertyUtility.initialize("url"), timeout);
		 ddriver=driver;// takescreenshot
	}
	@BeforeMethod
	// logim to application
	public void loginToApplication() throws Throwable
	{
     LoginPage log= new LoginPage(driver);
     log.loginAction(PropertyUtility.initialize("username"), PropertyUtility.initialize("password"));
     hp = new HomePage(driver);
	 ExcelUtility.openExcel(ConstantPath.EXCELPATH);
	}
	@AfterSuite
	public void closeDataBase() throws Throwable
	{
		DataBaseUtility.closeDatabase();
	}
	@AfterClass
	public void closeBrowser()
	{
		WebDriver_Utility.closeBrowser(driver);
	}
	@AfterMethod
	public void logout() throws Throwable
	{
		 ExcelUtility.closeExcel();
		hp.getSignoutTab();
	}
}
