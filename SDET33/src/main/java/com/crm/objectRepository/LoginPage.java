package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.genericUtility.PropertyUtility;

import net.bytebuddy.asm.Advice.Enter;

public class LoginPage {
	@FindBy(name="user_name")
	private WebElement userName;
	
	@FindBy(name="user_password")
	private WebElement passwordTextField;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getUserNameTextField() {
		return userName;
	}

	public WebElement getPasswordTextField() {
		return passwordTextField;
	}

	public WebElement getLoginButton() {
		return loginButton;
		
	}
	// By creating business library
//	public void loginAction() {
//		enterValue();
//		loginButton.click();
	
//	}
   public void loginAction(String username, String password)
   {
	   userName.sendKeys(username);
	   passwordTextField.sendKeys(password);
	   loginButton.click();
   }
}


