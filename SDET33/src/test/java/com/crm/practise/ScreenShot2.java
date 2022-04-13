package com.crm.practise;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.genericUtility.WebDriver_Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ScreenShot2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		WebDriver_Utility.launchApplication(driver, "https://www.fb.com");
		//WebDriver_Utility.takeScreenShotOfFailedScript(driver, "./fbHomepage");

	}

}
