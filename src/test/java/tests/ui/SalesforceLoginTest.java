package tests.ui;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.PdfUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;


@Listeners(utilities.ListenerUtils.class)
public class SalesforceLoginTest extends BaseTest {

	@AfterTest
	public void closeBrowser() {
		driver.close();
	}

	@Test (priority=1, groups="smoke", description = "verify user is able to successfully login to salesforce app")
	public void pdf_test() throws InterruptedException, AWTException {
		openURL("https://www.africau.edu/images/default/sample.pdf");
		
	}
}


