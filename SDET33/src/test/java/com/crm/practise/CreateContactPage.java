package com.crm.practise;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.objectRepository.ContactPage;
import com.crm.objectRepository.CreateContact;
import com.crm.objectRepository.CreateOrganization;
import com.crm.objectRepository.HomePage;
import com.crm.objectRepository.OrganizationInformationPage;
import com.crm.objectRepository.OrganizationPage;

public class CreateContactPage extends BaseClass {
      @Test
	public void createContactPage()
	{
     hp.getContactsTab();
      ContactPage cp= new ContactPage(driver);
      cp.plus();
      CreateContact cc= new  CreateContact(driver);
      String lastname = ExcelUtility.fetchData(PropertyUtility.fetchString("sheetName"), 3, 0)+JavaUtility.generateRandomNumber(100);
      cc.getLastNameTextField(lastname);
	}
	
}
