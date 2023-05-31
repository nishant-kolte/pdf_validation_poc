package tests.ui;


import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(utilities.ListenerUtils.class)
public class SalesforceLoginTest extends BaseTest {
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}

	@Test (priority=1, groups="smoke", description = "verify user is able to successfully login to salesforce app")
	public void login() throws InterruptedException {
		openURL("https://nts-bb-dev-ed.develop.my.salesforce.com/?ec=302&startURL=%2Fsetup%2FforcecomHomepage.apexp%3Fsetupid%3DForceCom");
		clickOnLoginButton();
	}
}


