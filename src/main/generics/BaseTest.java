package main.generics;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest implements AutomationConstants{
    public static WebDriver driver;
    private static ExtentHtmlReporter report;
    public static ExtentTest reporter;
    private static ExtentReports extent;
    private static final long ITO =50;

    @Parameters({"Platform","TargetDriver"})
    @BeforeSuite
    public WebDriver open_Browser(String Platform, String TargetDriver){

        if(Platform.equalsIgnoreCase("Windows")){

            if(TargetDriver.equalsIgnoreCase("Chrome")){
                System.setProperty(CHROME_KEY,CHROME_WIN_VALUE);
                driver = new ChromeDriver();
            }
            else if(TargetDriver.equalsIgnoreCase("FireFox")) {
                System.setProperty(GECKO_KEY, GECKO_VALUE);
                driver = new FirefoxDriver();
            }
        }
        else if (Platform.equalsIgnoreCase("Mac")){

            if(TargetDriver.equalsIgnoreCase("Chrome")){
                System.setProperty(CHROME_KEY,CHROME_MAC_VALUE);
                driver = new ChromeDriver();
            }
            else if (TargetDriver.equalsIgnoreCase("Safari")){
                System.setProperty(SAFARI_KEY,SAFARI_VALUE);
                driver = new SafariDriver();
            }
            else if(TargetDriver.equalsIgnoreCase("FireFox")) {
                System.setProperty(GECKO_KEY, GECKO_MAC_VALUE);
                driver = new FirefoxDriver();
            }
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);

        return driver;
    }

    @BeforeClass
    public void initalizeGlobalvariables(){

    }

    @BeforeTest
    public void configReports(){
        report = new ExtentHtmlReporter(System.getProperty("user.dir")+REPORTS_PATH);
        extent=new ExtentReports();
        extent.setSystemInfo("Environment","Environment");
        extent.setSystemInfo("OS","OS");
        extent.setSystemInfo("Browser","browsername");
        extent.setSystemInfo("URL","URL of project");
        extent.setSystemInfo("Host Name"," ");

        report.config().setDocumentTitle("OfsSeleniumJava_Report");
        report.config().setReportName("OfsSeleniumJava_Report ");

        extent.attachReporter(report);

    }

    @BeforeMethod
    public void startTest(Method method){
        //String testName=method.getName();


    }

    @AfterMethod
    public void endTest(Method method){
        //String testName=method.getName();

    }

    @AfterSuite
    public void tearDown() {
        driver.close();
        driver.quit();

        report.flush();

    }
}
