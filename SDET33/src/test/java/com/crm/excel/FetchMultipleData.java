package com.crm.excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchMultipleData {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis= new FileInputStream("./src/test/resources/ExcelPage.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Page2");
		for(int i=1;i<=sh.getLastRowNum();i++)
		{
			Row row = sh.getRow(i);
		for(int j=0;j<row.getLastCellNum();j++)
		{

			String usernamePassword = sh.getRow(i).getCell(j).toString();
			System.out.println(usernamePassword+"\n");
		}
		}
	}
}

	


