package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	@FindBy(xpath="//span[@id='dtlview_Organization Name']")
	private WebElement orgNameTextField;
	
	public OrganizationInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getLastNameTextField() {
		return orgNameTextField;
	}
	// by business logic
  public String lastNameValue()
  {
	  String data = orgNameTextField.getText();
	  return data;
  }
}
