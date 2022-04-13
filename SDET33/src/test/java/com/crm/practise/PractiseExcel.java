package com.crm.practise;

import com.crm.genericUtility.ExcelUtility;

public class PractiseExcel {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		ExcelUtility.openExcel("./src/test/resources/ExcelPage.xlsx");
		ExcelUtility.writeDataInNewRow("./src/test/resources/ExcelPage.xlsx", "status", 12, 0, "debesh");
		String orgName=ExcelUtility.fetchData("status", 10, 0);
		System.out.println(orgName);
		ExcelUtility.writeDataInNewRow("./src/test/resources/ExcelPage.xlsx", "status", 13, 0, "siddu");
		String lastName=ExcelUtility.fetchData("status", 10, 1);
		System.out.println(lastName);
		ExcelUtility.closeExcel();
		// excel 2
		ExcelUtility.openExcel("./src/test/resources/ExcelPage.xlsx");
		ExcelUtility.writeDataInNewRow("./src/test/resources/ExcelPage.xlsx", "status", 12, 0, "debesh");
		String orgName1=ExcelUtility.fetchData("status", 10, 0);
		System.out.println(orgName);
		ExcelUtility.writeDataInNewRow("./src/test/resources/ExcelPage.xlsx", "status", 15, 0, "siddu");
		String lastName1=ExcelUtility.fetchData("status", 10, 1);
		System.out.println(lastName);
		ExcelUtility.closeExcel();
		
		

	}

}
