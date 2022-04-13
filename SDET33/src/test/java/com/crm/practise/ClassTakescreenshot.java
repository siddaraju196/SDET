package com.crm.practise;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.genericUtility.BaseClass;
import com.crm.genericUtility.ExcelUtility;
import com.crm.genericUtility.JavaUtility;
import com.crm.genericUtility.PropertyUtility;
import com.crm.objectRepository.ContactInforrmation;
import com.crm.objectRepository.ContactPage;
import com.crm.objectRepository.CreateContact;

public class ClassTakescreenshot extends BaseClass {
	@Test
	public void practiceAssert() { 
		hp.newContact();
		ContactPage cp= new ContactPage(driver);
		cp.getPlusButton();
		CreateContact cc= new CreateContact(driver);
		String lastname = ExcelUtility.fetchData(PropertyUtility.fetchString("sheetName"), 2, 0);
		int random = JavaUtility.generateRandomNumber(1000);
		String lastname1 = lastname+random;
		cc.getLastNameTextField(lastname1);
		Assert.fail();
		// fetch the lastname from contact information page
		ContactInforrmation ci= new ContactInforrmation(driver);
	    String expLast = ci.savedLastName();
	   
	   // verifying data
	    Assert.assertTrue(lastname1.equals(expLast));
	    Reporter.log("Test case passed", true);
		
		
	}

}
