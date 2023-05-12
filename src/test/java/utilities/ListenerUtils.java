package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.ui.BaseTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ListenerUtils implements ITestListener {
    public static ExtentTest test;
    static ExtentReports report;
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
        try {
            CommonUtils.cleanHtmlReportFolder();
            report = new ExtentReports(System.getProperty("user.dir")+"\\html-report\\Automation_Execution_Report"+timestamp+".html");
            report.loadConfig(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\configs\\config.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        report.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getTestClass().getName().split("\\.")[1].equalsIgnoreCase("ui")){
            BaseTest.log.info("saving screenshot for extent report");
//                CommonUtils.takescreenshot(result.getName());

            test.log(
                    LogStatus.FAIL,
                    "<b> TEST CLASS :" + result.getInstanceName() +
                            "<br /> TEST CASE : "+result.getMethod().getDescription() +
                            "<br /> STATUS : <font color=red> FAILED" +
                            "<br /> ERROR : </b>" + result.getThrowable().toString() +
                            "<br /> FULL STACK TRACE : </b>" + ExceptionUtils.getStackTrace(result.getThrowable()) /*+ test.addScreenCapture(result.getName()+"fail.png")*/
            );
            report.endTest(test);

        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(
                LogStatus.INFO,
                "<b> TEST CLASS :" + result.getInstanceName() +
                        "<br /> TEST CASE : "+result.getMethod().getDescription()+
                        "<br /> STATUS : <font color=green> PASSED"
        );
        report.endTest(test);

    }

    public void onTestStart(ITestResult result) {
        String[] category = (result.getInstanceName()).split("\\.");
 test = report.startTest(result.getMethod().getDescription()).assignCategory(category[2]);
//        test = report.startTest(result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        test.log(LogStatus.SKIP,result.getInstanceName()+" : "+result.getName().replace("_"," ")+" -- SKIPPED");
        report.endTest(test);
    }

}
