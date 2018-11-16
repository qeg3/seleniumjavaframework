package main.generics;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest implements AutomationConstants{
    public static WebDriver driver;
    public static ExtentHtmlReporter report;
    public static ExtentTest reporter;
    public static ExtentReports extent;

    @Parameters({"platform","browser"})
    @BeforeSuite
    public WebDriver open_Browser(String platform, String browser){

        return driver;
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
    public void startTest(){

    }
    @AfterMethod
    public void endTest(){

    }

    @AfterSuite
    public void tearDown() {

    }
}
