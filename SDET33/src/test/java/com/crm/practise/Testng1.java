package com.crm.practise;

import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Testng1 {
	@Test
	public void  testNg1()
	{
		Reporter.log("sidda is doing testNg ",true);
	}
	@BeforeMethod
	public void testNg2()
	{
		Reporter.log("debasis from odissa", true);
	}
	@AfterMethod
	public void testNg3()
	{
		Reporter.log("program is executed", true);
	}
}
