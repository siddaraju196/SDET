package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Excel2 {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis=new FileInputStream("./src/test/resources/Name2.xlsx");
		Workbook workbook = WorkbookFactory.create(fis);

		String url = workbook.getSheet("TC002").getRow(2).getCell(1).getStringCellValue();
		String username = workbook.getSheet("TC002").getRow(2).getCell(2).getStringCellValue();
		String password = workbook.getSheet("TC002").getRow(2).getCell(3).getStringCellValue();
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		



	}

}
