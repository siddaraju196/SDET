package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickOnPrevious {
	@FindBy(xpath="//a[contains(@href,'Contacts') and text()='Contacts']")
	private WebElement contactTab;
	@FindBy(xpath="//a[text()='Jagan183']")
	private WebElement savedContact;
	
	@FindBy(xpath="//img[@title='Previous']")
	private WebElement previousButton;
	
	public WebElement getContactTab() {
		return contactTab;
	}

	public WebElement getSavedContact() {
		return savedContact;
	}

	public WebElement getPreviousButton() {
		return previousButton;
	}

	public ClickOnPrevious(WebDriver driver) {
	PageFactory.initElements(driver, this);
	}
	// by business layer
	public void clickAction()
	{
		selectSavedContact();
		previousButton.click();
		
	}
	public void selectSavedContact()
	{
		contactTab.click();
		savedContact.click();
		
	}

}
