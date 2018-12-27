package main.generics;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;

public abstract class BaseTest implements AutomationConstants{
    public static WebDriver driver;
    public static ExtentReports report;
    public static ExtentTest reporter;
    public static final long ITO =10;
    public static String url="https://online.actitime.com/areddy/login.do";

    @Parameters({"platform","targetDriver"})
    @BeforeSuite
    public WebDriver open_Browser(String platform, String targetDriver){

        if(platform.equalsIgnoreCase("Windows")){

            if(targetDriver.equalsIgnoreCase("Chrome")){
                System.setProperty(CHROME_KEY,CHROME_WIN_VALUE);
                driver = new ChromeDriver();
            }
            else if(targetDriver.equalsIgnoreCase("FireFox")) {
                System.setProperty(GECKO_KEY, GECKO_VALUE);
                driver = new FirefoxDriver();
            }
            else if (targetDriver.equalsIgnoreCase("IE")){
                System.setProperty(IE_KEY,IE_VALUE);
                driver=new InternetExplorerDriver();
            }
            driver.manage().window().maximize();
        }
        else if (platform.equalsIgnoreCase("Mac")){

            if(targetDriver.equalsIgnoreCase("Chrome")){
                System.setProperty(CHROME_KEY,CHROME_MAC_VALUE);
                driver = new ChromeDriver();
            }
            else if (targetDriver.equalsIgnoreCase("Safari")){
                System.setProperty(SAFARI_KEY,SAFARI_VALUE);
                driver = new SafariDriver();
            }
            else if(targetDriver.equalsIgnoreCase("FireFox")) {
                System.setProperty(GECKO_KEY, GECKO_MAC_VALUE);
                driver = new FirefoxDriver();
            }
            driver.manage().window().fullscreen();
        }
        driver.manage().deleteAllCookies();
        return driver;
    }

    @BeforeClass
    public void initializeGlobalVariables(){

    }

    @BeforeTest
    public void configReports(){
        report = new ExtentReports (System.getProperty("user.dir") +REPORTS_PATH, true);
        report.addSystemInfo("Host Name", "")
                .addSystemInfo("Environment", "prod")
                .addSystemInfo("URL",url)
                .addSystemInfo("User Name", "");
        report.loadConfig(new File(System.getProperty("user.dir")+REPORTS_CONFIG));
    }

    @BeforeMethod
    public void startTest(Method method){
        String testName=method.getName();
        reporter=report.startTest(testName);
        CommonMethods.implicitWait(ITO);
        CommonMethods.enter_URL(url);
    }

    @AfterMethod
    public void endTest(ITestResult result, Method method){
        String testName=method.getName();
        report.endTest(reporter);
    }

    @AfterSuite
    public void tearDown() {
        driver.close();
        driver.quit();
        report.flush();

    }
}
