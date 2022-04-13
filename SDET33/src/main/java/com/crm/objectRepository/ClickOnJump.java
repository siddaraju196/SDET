package com.crm.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClickOnJump {
@FindBy(xpath="//img[@id='jumpBtnIdTop']")
private WebElement jumpButton;
 

@FindBy(xpath="//a[text()='  Jagan433']")
private WebElement selectContactButton;

public ClickOnJump(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

public WebElement getJumpButton() {
	return jumpButton;
}

public WebElement getSelectContactButton() {
	return selectContactButton;
}
 // by business logic logic
public void jumpAction()
{
	jumpButton.click();
	selectContactButton.click();
}

}
