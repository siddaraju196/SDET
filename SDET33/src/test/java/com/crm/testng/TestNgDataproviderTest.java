package com.crm.testng;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.genericUtility.ConstantPath;
import com.crm.genericUtility.ExcelUtility;

public class TestNgDataproviderTest {
	@Test(dataProvider ="dataProvider_Excel" )
	public void getMultipleDataFromExcel(String username,String password)
	{
		System.out.println(username+"++++++++++"+password);
	}
@DataProvider
public Object[][] dataProvider_Excel() throws Throwable
{
	ExcelUtility.openExcel("./src/test/resources/StatusList1.xlsx");
	Object[][] data = ExcelUtility.fetchMultipleData("Page3");
	return data;

}
@DataProvider
public  Object[][ ] program1()
{
Object[][] arr= new Object[5][2];
  arr[0][0]="testyantra";
  arr[0][1]="2017";
  
  arr[1][0]="testyantra";
  arr[1][1]="2018";
  
  arr[2][0]="testyantra";
  arr[2][1]="2018";
  
  arr[3][0]="testyantra";
  arr[3][1]="2019";
  
  arr[4][0]="testyantra";
  arr[4][1]="2011";
  return arr;
}
}
