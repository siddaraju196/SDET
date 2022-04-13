package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContact {
@FindBy(name="lastname")
private WebElement lastNameTextField;
 @FindBy(xpath="//input[@title=\"Save [Alt+S]\"]")
 private WebElement  saveButton;
 
 public CreateContact(WebDriver driver)
 {
	 PageFactory.initElements(driver, this);
 }

public WebElement getLastNameTextField(String name)
{
	lastNameTextField.sendKeys(name);
	saveButton.click();
	return lastNameTextField;
}


}
