package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	@FindBy(xpath="//input[@id='input-email']")
	private WebElement email;
	
	@FindBy(xpath="//input[@id='input-password']")
	private	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	private	WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmail(String emailId) {
		email.sendKeys(emailId);
	}
	
	public void enterPassword(String Password) {
		password.sendKeys(Password);
	}
	
	public AccountPage clickLoginButton() {
		loginButton.click();
	return	new AccountPage(driver);
	}

}
