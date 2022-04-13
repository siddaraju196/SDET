package com.crm.testng;

import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.objectRepository.ContactInforrmation;
import com.crm.objectRepository.ContactPage;
import com.crm.objectRepository.CreateContact;

public class ContactClassTest extends BaseClass{

	@Test
	public void createContact() throws Throwable
	{
		hp.newContact();
		ContactPage cp = new ContactPage(driver);
		cp.plus();
		CreateContact cc= new CreateContact(driver);
		
		int random = JavaUtility.generateRandomNumber(100);
	String contactName=(ExcelUtility.fetchData(PropertyUtility.initialize("sheetName"),1, 1));
	        String contName = random+contactName;
	        cc.getLastNameTextField(contName);
		ContactInforrmation ci= new ContactInforrmation(driver);
		String contactName1 = ci.savedLastName();
		if(contName.equals(contactName1))
		{
			System.out.println("contact is created");
		} 
		else
		{
			System.out.println("contact is not created");
		}
	}


}
