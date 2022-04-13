package com.crm.practise;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNg2 {
	@Test
public void test()
{
	Reporter.log("first", true);
}
	@BeforeClass
	public void test1()
	{
		Reporter.log("second", true);
	}
	@AfterClass
	public void test2()
	{
		Reporter.log("Third", true);
	}
	@BeforeMethod
	public void test3()
	{
		Reporter.log("Four", true);
	}
}
