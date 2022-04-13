package com.crm.practise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContact {

	public static void main(String[] args) throws IOException {
		FileInputStream fis= new FileInputStream("./src/test/resources/commondata.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");

		// fetch data from excel
		FileInputStream fisExcel= new FileInputStream("./src/test/resources/StatusList.xlsx");
		Workbook wb = WorkbookFactory.create(fisExcel);
		Row row = wb.getSheet("Page1").getRow(1);
		String lastName = row.getCell(0).toString();

		// create Random 
		Random ran= new Random();
		int randomNumber = ran.nextInt(1000);
		String expectedLastName = lastName+randomNumber;

		// launch the browser
		WebDriverManager.chromedriver().setup();
		WebDriver  driver= new ChromeDriver();
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(expectedLastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		WebElement expLast = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']"));
		if(expLast.getText().equals(expectedLastName))
		{
			FileOutputStream fos= new FileOutputStream("./src/test/resources/StatusList.xlsx");
			row.createCell(2).setCellValue("Pass");
			wb.write(fos);
			System.out.println("pass: contact is created");

		}
		else
		{
			System.out.println("fail: contact is not created");
		}
		driver.quit();
	}

}
