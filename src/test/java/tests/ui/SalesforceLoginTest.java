package tests.ui;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.PdfUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;


@Listeners(utilities.ListenerUtils.class)
public class SalesforceLoginTest extends BaseTest {
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

	@Test (priority=1, groups="smoke", description = "verify user is able to successfully login to salesforce app")
	public void login() throws InterruptedException, AWTException {
		openURL("https://nts-bb-dev-ed.develop.my.salesforce.com/?ec=302&startURL=%2Fsetup%2FforcecomHomepage.apexp%3Fsetupid%3DForceCom");
		clickOnLoginButton();
	}
}


