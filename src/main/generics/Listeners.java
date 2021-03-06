package main.generics;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Listeners extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        reporter.log(LogStatus.INFO,testName+" : has Started Exicution");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        reporter.log(LogStatus.PASS,testName+" : has Successfully Exicuted");
    }

    @Override
    public void onTestFailure(ITestResult result)  {
        String testName = result.getName();
        reporter.log(LogStatus.FAIL,testName+" : has Failed to Execute Because of : "+result.getThrowable());
        try {
            Robot r = new Robot();
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(d);
            BufferedImage img = r.createScreenCapture(screenRect);
            String now = CommonMethods.getFormattedDateTime();
            ImageIO.write(img, "png", new File(PHOTO_PATH + testName + now + ".png"));
            reporter.log(LogStatus.FAIL,"The " + testName + " has Failed and the Screenshot is : " + PHOTO_PATH + testName + now + ".png");
        }catch (Exception e){

            reporter.log(LogStatus.ERROR,"Error Occurred while taking Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        reporter.log(LogStatus.SKIP,testName+" : has got Skipped from the Execution Because of : "+result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }
}
