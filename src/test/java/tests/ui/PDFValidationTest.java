package tests.ui;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.PdfUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

//
@Listeners(utilities.ListenerUtils.class)
public class PDFValidationTest extends BaseTest {

	@AfterClass
	public void closeBrowser() {
		driver.close();
	}


	@Test (priority=1, groups="smoke", description = "verify successfully able to validate the pdf data")
	public void pdf_test() throws InterruptedException, AWTException {
		openURL("https://www.africau.edu/images/default/sample.pdf");
		WebElement body = driver.findElement(By.tagName("body"));
		System.out.println(body.getLocation().x);
		System.out.println(body.getLocation().y);
		Thread.sleep(2000);
//		//download pdf from web:
//		action.moveByOffset(1800,30).click().perform();
//		Thread.sleep(3000);
//
//		keyboard.keyPress(KeyEvent.VK_ENTER);
//		keyboard.keyRelease(KeyEvent.VK_ENTER);
//		Thread.sleep(5000);

		//pdf extraction and printing on console:
		try {
			String text = PdfUtils.getText(new File(System.getProperty("user.home")+"\\DOwnloads\\sample.pdf"));
			log.debug("Text in PDF: " + text);
			Assert.assertTrue(text.contains("just for use in the Virtual Mechanics tutorials"),"failed to validate text on page 1");
			Assert.assertTrue(text.contains("Simple PDF File 2"),"failed to validate text on page 2");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


