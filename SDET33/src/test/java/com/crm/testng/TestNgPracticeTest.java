package com.crm.testng;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestNgPracticeTest {
	@BeforeSuite
public void suite()
{
	System.out.println("before suit");
}
	
	@Test
	public void basicTest()
	{
		System.out.println("hi");
	}
	@Test
	public void basicTest1()
	{
		System.out.println("hi1");
	}
	@Test
	public void basicTest2()
	{
		System.out.println("hi3");
	}
	@AfterSuite
	public void aftersuite()
	{
		System.out.println("after suite");
	}
}
