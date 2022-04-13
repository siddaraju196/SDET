package com.crm.practise;

import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckingNoOfTest {
	@Test
 public void test1()
 {
	 Reporter.log("First test", false);
 }
	@Test
	public void test2()
	{
		Reporter.log("second test",true);
	}
	@BeforeClass
	public void test3()
	{
		Reporter.log("Third test", true);
	}
	
}
