package com.crm.practise;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.objectRepository.CreateOrganization;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.OrganizationInformationPage;
import com.crm.objectRepository.OrganizationPage;

public class CreateOrganizationPage extends BaseClass
{  
	@Test
	public void createOrganizationPage() {
	 
	
		hp.getOrganizationsTab();
		OrganizationPage org= new OrganizationPage(driver);
		org.getPlusButton();
		CreateOrganization org1= new CreateOrganization(driver);
		OrganizationInformationPage orgInfo=new OrganizationInformationPage(driver);
		
		String orgName = (ExcelUtility.fetchData("status", 6, 1)+JavaUtility.generateRandomNumber(1000));
		// create organization and save
		org1.createOrg(orgName);
		// verify the contact 
		String actOrgName= orgInfo.lastNameValue();
		
		if(orgName.equalsIgnoreCase(actOrgName))
		{
			Reporter.log("organization is created successfully", true);
		}
		else
		{
			Reporter.log("organization is created successfully", true);
		}	

	}

}
