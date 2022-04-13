package com.crm.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * this class contains Excel specific Methods
 * @author  Siddu Maharaj
 * @return 
 * 
 */

public class ExcelUtility {
		static Workbook wb;
		/**
		 * This method is used to fetch the single data from excel
		 * 
		 * 
		 */
public static String fetchData( String sheetName, int rowNumber, int cellNumber)
{
	Sheet sh = wb.getSheet(sheetName);
	String data = sh.getRow(cellNumber).getCell(cellNumber).getStringCellValue(); 
	return data;
	
}
//public static void writeDataInExis
	
/**
 * This method is used to create/write the data to new row into specified excel sheet
 * @return 
 * @throws IOException 
 * 
 */
public static void writeDataInNewRow(String path, String sheetName,int rowNumber,int cellName, String data ) throws IOException
{
	 Sheet sh=wb.getSheet(sheetName);
	 sh.createRow(rowNumber).createCell(cellName).setCellValue(data);
	 FileOutputStream fosExcel= new FileOutputStream(path);
	 wb.write(fosExcel);
	 System.out.println("Excel is successfully");
}
/**
 * This method is used to open the Excel File
 * @throws IOException 
 * @throws EncryptedDocumentException 
 * @throws Throwable 
 */
public static void openExcel(String path) throws EncryptedDocumentException, IOException 
{
	FileInputStream f=new FileInputStream(path);
	wb=WorkbookFactory.create(f);
	System.out.println("Excel open successfully");
}
/**
 * This method is used for close the excel File
 * @throws Throwable 
 * 
 */
public static Object[][] fetchMultipleData(String sheetName) throws Throwable
{
	Sheet sh= wb.getSheet(sheetName);
	Object[][] arr= new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
	for (int i = 0; i <sh.getLastRowNum() ; i++) {
		for (int j = 0; j <sh.getRow(0).getLastCellNum(); j++) {
			arr[i][j]=sh.getRow(i+1).getCell(j).toString();
			
		}
	}
	return arr;
}
public static void closeExcel() throws Throwable
{
	wb.close();
	System.out.println("Excel closed Successfully");
}

public static void WriteDataInExcel( String path,String sheet,int rowNumber,int cellNumber,String data) throws IOException
{
	FileInputStream f=new FileInputStream(path);
	WorkbookFactory.create(f);
	Sheet sh = wb.getSheet(sheet);
	Row row = sh.getRow(rowNumber);
	Cell cell = row.createCell(cellNumber);
	cell.setCellValue(data);
	FileOutputStream fos= new FileOutputStream(path);
	wb.write(fos);

	
}
	
}


	





