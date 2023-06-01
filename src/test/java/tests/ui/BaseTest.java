package tests.ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utilities.CommonUtils;
import utilities.InitTestData;


import java.awt.*;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static String browser;
    public static String env;
    public static WebDriver driver;
    public static Properties testdata;
    public static Properties config;
    public static Logger log;
    public static Actions action;

    private  By email=By.xpath("//input[@name=\"username\"]");
    private By password=By.xpath("//input[@type=\"password\"]");
    private By loginButton=By.xpath("//input[@id=\"Login\"]");

    @BeforeSuite
    @Parameters({"browser","environment"})
    public void testInit(@Optional String browser,@Optional String env) {
        PropertyConfigurator.configure("log4j.properties");
        log = Logger.getLogger(Test.class);

        if(browser==null) {
            this.browser = "chrome";
        }
        if(env==null){
            if(getEnvConfig("environment")==null){
                this.env="qa";
            }
            else {
                this.env=getEnvConfig("environment");
            }
        }
//        initBrowser(browser);
//        initAllPages();
//        testdata = InitTestData.getTestData(this.env);
//        config = InitTestData.readConfig(this.env);
    }

    public static void initBrowser(String browser) {
        if (browser == null || browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            String downloadFilepath = System.getProperty("user.dir")+"/";
            Map<String, Object> preferences = new Hashtable<String, Object>();
            preferences.put("profile.default_content_settings.popups", 0);
            preferences.put("download.prompt_for_download", false);
            preferences.put("download.default_directory", downloadFilepath);
            preferences.put("plugins.always_open_pdf_externally", true);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
            options.setExperimentalOption("prefs", preferences);
            options.addArguments("--disable-notifications");
            options.addArguments("--headless");
            options.addArguments("--window-size=1920x1080");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            driver= new ChromeDriver(options);
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            log.info("chrome browser started");
        }
        else if (browser.equalsIgnoreCase("ie")){
            WebDriverManager.iedriver().setup();
            driver= new InternetExplorerDriver();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver= new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            driver.manage().window().maximize();

        }
        else{
            log.info("invalid browser selected");
        }
    }

    public static void openURL(String url) {
        initBrowser(browser);
        driver.get(url);
        initObjects();
    }
    public static void initObjects() {
//        keyboard = new Robot();
        action = new Actions(driver);
    }

    public static String getTestData(String key){
        try{
            return testdata.getProperty(key);
        }
        catch (Exception e){
            return null;
        }
    }
    public static String getConfig(String key){
        try{
            return config.getProperty(key);
        }
        catch (Exception e){
            return null;
        }
    }
    public static String getEnvConfig(String key){
        try{
            return System.getenv(key);
        }
        catch (Exception e){
            return null;
        }

    }
    public static String getProp(String key){
        String ev=getEnvConfig(key);
        return ev!=null?ev:getConfig(key);

    }

    public  void clickOnLoginButton() throws InterruptedException {
        driver.findElement(email).sendKeys("rajvaibhavi.yadav@neutrinotechlabs.com ");
        driver.findElement(password).sendKeys("connect@123");
        driver.findElement(loginButton).click();
        Thread.sleep(5000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"Jobs\"]")).getText(),"Jobs");

    }
}
