package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {
	HomePage homePage;
	SearchPage sp;

	public SearchTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public WebDriver driver;

	@BeforeMethod
	public void setup() {
		driver = initializedBrowserAndOpenApplicationURL("chrome");

	}

	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void searchValidProduct() {
		 homePage = new HomePage(driver);
		homePage.searchValidProduct(propTestdata.getProperty("validProduct"));
		sp = homePage.clickSearchButton();

		String actualTest = sp.actualSearchResult();
		Assert.assertEquals(actualTest, "HP LP3065");
	}

	 @Test(priority = 2)
	public void verifySearchWithInavalidProduct() {
		 homePage = new HomePage(driver);
			sp = homePage.clickSearchButton();
			
		
		String actualTest = driver.findElement(By.xpath("//*[@id=\"content\"]/p[2]")).getText();
		Assert.assertEquals(actualTest, "HP LP3065");

	}

}
