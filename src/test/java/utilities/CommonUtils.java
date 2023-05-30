package utilities;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static tests.ui.BaseTest.driver;


public class CommonUtils {
	static String timestamp;
	public static Actions actionObject(){
		return new Actions(driver);
	}

	public static void takescreenshot(String testname) throws IOException
	{
		File myfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(myfile, new File(System.getProperty("user.dir")+"/html-report/"+testname+"fail.png"));
	}

	public static void takeScreenshot(String name) throws IOException
	{
		File myfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(myfile, new File(System.getProperty("user.dir")+"/html-report/"+name+".png"));
	}

	public static void cleanHtmlReportFolder() throws Exception {
//		String command = "cmd /c start cmd.exe /K ";
//		Runtime rt = Runtime.getRuntime();
//		Process proc = rt.exec(command);
			File directory = new File(System.getProperty("user.dir")+"/html-report");
			FileUtils.cleanDirectory(directory);
	}


	public static byte[] saveFailureScreenShot()
	{
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	}

	public static void initPageFactory(Class page)
	{
		PageFactory.initElements(driver, page);
	}


	public static void logStep(String message){
		// intentionally kept empty
	}
	public static WebElement element(By locator){
		return driver.findElement(locator);
	}

	public static void logStepAsPassedInExtentReport(String message){
		ListenerUtils.test.log(LogStatus.PASS, "<font color=green>"+message);
	}

	public static void logStepAsPassedWithSreenshotInExtentReport(String message){
		try{
			timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
			takeScreenshot("step_"+timestamp);
			ListenerUtils.test.log(LogStatus.PASS, "<font color=green>"+message,ListenerUtils.test.addScreenCapture("step_"+timestamp+".png"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void logStepAsFailedInExtentReport(String message){
		ListenerUtils.test.log(LogStatus.FAIL, "<font color=red>"+message);
	}

	public static void logStepAsInfoInExtentReport(String step, String details){
		ListenerUtils.test.log(LogStatus.INFO, "<b><u>"+step+"</u></b><br />" + "<pre>"+details+"</pre>");
	}

	public static void assertEquals(Object actual, Object expected, String fieldName){
		try {
			Assert.assertEquals(actual,expected);
			logStepAsPassedInExtentReport("successfully validated "+fieldName+" value equal to "+expected);
		}
		catch (AssertionError e){
			logStepAsFailedInExtentReport("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
			Assert.fail("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
		}
	}

	public static void assertNotNull(Object actual, String fieldName){
		try {
			Assert.assertNotNull(actual);
			logStepAsPassedInExtentReport("successfully validated "+fieldName+" is not null");
		}
		catch (AssertionError e){
			logStepAsFailedInExtentReport("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
			Assert.fail("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());

		}
	}

	public static void assertTrue(Boolean actual, String fieldName){
		try {
			Assert.assertTrue(actual);
			logStepAsPassedInExtentReport("successfully validated "+fieldName+" is not null");
		}
		catch (AssertionError e){
			logStepAsFailedInExtentReport("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());
			Assert.fail("assertion failed for - " + fieldName + " with ERROR: "+ e.getMessage());

		}
	}

	}

