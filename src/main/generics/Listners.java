package main.generics;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Listners extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getName();
        reporter.info(testName+" : has Started Exicution");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        reporter.pass(testName+" : has Successfully Exicuted");
    }

    @Override
    public void onTestFailure(ITestResult result)  {
        String testName = result.getName();
        reporter.fail(testName+" : has Failed to Execute Because of : "+result.getThrowable());
        try {
            Robot r = new Robot();
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle screenRect = new Rectangle(d);
            BufferedImage img = r.createScreenCapture(screenRect);
            String now = CommonMethods.getFormattedDateTime();
            ImageIO.write(img, "png", new File(PHOTO_PATH + testName + now + ".png"));
            reporter.info("The " + testName + " has Failed and the Screenshot is : " + PHOTO_PATH + testName + now + ".png");
        }catch (Exception e){

            reporter.error("Error Occurred while taking Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getName();
        reporter.skip(testName+" : has got Skipped from the Execution Because of : "+result.getThrowable());
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
