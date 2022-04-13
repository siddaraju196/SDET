package com.crm.organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit; 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
public class OrganizationWithIndustryAndTypeTest {
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		FileInputStream fis= new FileInputStream("./src/test/resources/commondata.properties");
		Properties prop= new Properties();
		prop.load(fis);
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		String browser=prop.getProperty("browser");
		System.out.println(url);
		System.out.println(username);
		System.out.println(password);
		System.out.println(browser);
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("Sharp");

		Select select=new Select(driver.findElement(By.xpath("//select[@name='industry']")));
		Thread.sleep(2000);

		select.selectByVisibleText("Education");
		Thread.sleep(2000);
		Select select1=new Select(driver.findElement(By.xpath("//select[@name='accounttype']")));
		select1.selectByIndex(1);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		WebElement actual = driver.findElement(By.xpath("//font[text()='Education']"));
		WebElement educationText = driver.findElement(By.id("dtlview_Industry"));
		if (educationText.getText().contains("Education")) {
			System.out.println("pass : education is present");

		}
		else
		{
			System.out.println("fail: educvation is not present");
		}
		WebElement analyst = driver.findElement(By.xpath("//font[text()='Analyst']"));
		WebElement analystText = driver.findElement(By.id("dtlview_Type"));
		if(analystText.getText().contains("Analyst"))
		{
			System.out.println("pass: analyst present");
		}
		else
		{
			System.out.println("fail: analyst is not present");
		}
		WebElement signout = driver.findElement(By.xpath("//td[contains(@onmouseover,'siddara')]"));
		Actions action=new Actions(driver);
		action.moveToElement(signout).perform();
		driver.findElement(By.linkText("Sign Out")).click();


	}

}
