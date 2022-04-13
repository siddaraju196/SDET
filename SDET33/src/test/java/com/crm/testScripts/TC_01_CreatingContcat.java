package com.crm.testScripts;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriver_Utility;
import com.crm.objectRepository.ContactPage;
import com.crm.objectRepository.CreateContact;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_01_CreatingContcat {
static WebDriver driver;
	public static void main(String[] args) throws Throwable {
        String property = ConstantPath.PROPERTYFILEPATH;
        PropertyUtility.initialize(property);
        String url = PropertyUtility.fetchString("url");
        String username = PropertyUtility.fetchString("username");
        String password = PropertyUtility.fetchString("password");
        String timeout = PropertyUtility.fetchString("timeout");
        String browser= PropertyUtility.fetchString("browser");
        String excel = ConstantPath.EXCELPATH;
        ExcelUtility.openExcel(excel);
        String lastName = ExcelUtility.fetchData("status", 1, 1);
        if(browser.equalsIgnoreCase("chrome"))
        {
        	WebDriverManager.chromedriver().setup();
        	driver= new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
        	WebDriverManager.firefoxdriver().setup();
        	driver= new  FirefoxDriver();
        }
        driver=WebDriver_Utility.initializeDriver(driver, 0, url);
        LoginPage login= new LoginPage(driver);
        login.loginAction();
        HomePage hp=new HomePage(driver);
        hp.newContact();
        ContactPage cp= new ContactPage(driver);
        cp.plus();
        CreateContact ccp= new CreateContact(driver);
        ccp.getLastNameTextField(lastName);
        ccp.getSaveButton();
	}

}
