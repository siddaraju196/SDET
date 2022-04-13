package com.crm.organization;

import org.openqa.selenium.WebDriver;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.genericUtility.WebDriver_Utility;
import com.crm.objectRepository.IndustryAndType;
import com.crm.objectRepository.LoginPage;
import com.crm.objectRepository.OrganizationPage;
import com.crm.objectRepository.OrganizationInformationPage;

public class CreateOrgWithIndustryWithPom {
	static WebDriver driver;
	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		String propertyFile = ConstantPath.PROPERTYFILEPATH;
		PropertyUtility.initialize(propertyFile);
		String url = PropertyUtility.fetchString("url");
		String username = PropertyUtility.fetchString("username");
		String password = PropertyUtility.fetchString("password");
		String timeout = PropertyUtility.fetchString("timeout");
		long timeOuts = JavaUtility.convertStringToLong(timeout);
		driver=WebDriver_Utility.initializeDriver(driver, timeOuts, url);
		int randomNumber = JavaUtility.generateRandomNumber(1000);
		String name="SDET";
		String actual = randomNumber+name;

		LoginPage login= new LoginPage(driver);
		login.loginAction();

		OrganizationPage org= new OrganizationPage(driver);
		org.enterValue(actual);
		OrganizationInformationPage oip= new OrganizationInformationPage(driver);
		if(actual.equals(oip.lastNameValue()))
		{
			System.out.println("pass: org is correct");
		}
		IndustryAndType industry= new IndustryAndType(driver);
		industry.selectIndustry("Education");
		industry.selectType("Analyst");
		org.getSaveButton();
		industry.save();


	}


}
