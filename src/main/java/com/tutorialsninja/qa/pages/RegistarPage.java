package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistarPage {

	WebDriver driver;
	
	public RegistarPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(id = "input-firstname")
	private WebElement firstName;

	@FindBy(id = "input-lastname")
	private WebElement lastName;

	@FindBy(id = "input-email")
	private WebElement email;

	@FindBy(id = "input-telephone")
	private WebElement telephone;

	@FindBy(id = "input-password")
	private WebElement password;

	@FindBy(id = "input-confirm")
	private WebElement conformPassword;

	@FindBy(xpath = "//input[@type='checkbox']")
	private WebElement checkBox;

	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginButton;

	// Actions

	public void enterFirstName(String fn) {
		firstName.sendKeys(fn);
	}

	public void enterLastName(String ln) {
		lastName.sendKeys(ln);
	}

	public void enterEmail(String email1) {
		email.sendKeys(email1);
	}

	public void entertelephone(String tel) {
		telephone.sendKeys(tel);
	}

	public void enterpassword(String ps) {
		password.sendKeys(ps);
	}

	public void enterconformPassword(String cps) {
		conformPassword.sendKeys(cps);
	}

	public void selectCheckBox() {
		checkBox.click();
		
	}
	
	public AccountSuccessPage clickLogin() {
		loginButton.click();
	return	new AccountSuccessPage(driver);
		
	}

}
