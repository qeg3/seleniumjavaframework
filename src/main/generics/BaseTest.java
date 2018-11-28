package main.generics;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest implements AutomationConstants{
    public static WebDriver driver;
    private static ExtentHtmlReporter report;
    public static ExtentTest reporter;
    private static ExtentReports file;
    public static final long ITO =50;

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
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        //driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);

        return driver;
    }

    @BeforeClass
    public void initializeGlobalVariables(){

    }

    @BeforeTest
    public void configReports(){
        report = new ExtentHtmlReporter(System.getProperty("user.dir")+REPORTS_PATH);
        file=new ExtentReports();
        file.setSystemInfo("Environment","Prod");
        file.setSystemInfo("OS","Windows");
        file.setSystemInfo("Browser","Chrome");
        file.setSystemInfo("URL","https://ToolsQA.com");
        file.setSystemInfo("Host Name"," ");

        report.config().setDocumentTitle("OFS Selenium");
        report.config().setReportName("OFS Selenium ");

        file.attachReporter(report);
    }

    @BeforeMethod
    public void startTest(Method method){
        String testName=method.getName();
        reporter=file.createTest(testName);
        driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
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
