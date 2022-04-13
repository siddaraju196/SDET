package com.crm.testng;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.objectRepository.CreateOrganization;
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
		
		String orgName = (ExcelUtility.fetchData("status", 5, 1));
			
		int random = JavaUtility.generateRandomNumber(1000);
		String orgName1 = orgName+random;
		// create organization and save
		org1.getLastName(orgName1);
		// verify the contact 
		String actOrgName= orgInfo.lastNameValue();
		
		if(orgName.equalsIgnoreCase(actOrgName))
		{
			Reporter.log("organization is created successfully", true);
		}
		else
		{
			Reporter.log("organization is not created successfully", true);
		}	
		Assert.assertEquals(orgName, actOrgName);
	Reporter.log(" test case is pass ", true);

	}

}
