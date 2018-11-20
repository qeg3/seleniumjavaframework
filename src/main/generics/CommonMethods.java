package main.generics;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends BaseTest {

    private static WebElement element;

    //------------------------------------------------1---------------------------------------------------------------//

    /**
     * This method is to intalize page
     */

    public void GenericPage() {
        PageFactory.initElements(driver, this);
    }

    /**
     * This method is used to intalize the java script execution metnod
     * @return jse;
     */

    //------------------------------------------------2---------------------------------------------------------------//

    /**
     *
     * @return
     */

    public static JavascriptExecutor jsExecutor(){
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        return jse;
    }

    //------------------------------------------------3---------------------------------------------------------------//

    /**
     * This Method is used to det the current date and time
     * @getFormatedDateTime is the format in which date has to return
     * @return the current date and time in "dd_MM_yyyy_hh_mm_ss" format
     */

    public static String getFormatedDateTime(){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        return simpleDate.format(new Date());
    }

    //------------------------------------------------4---------------------------------------------------------------//

    /**
     * This Method is used to inilaze composit actions
     * @return action
     */

    public static Actions getAction() {
        Actions action = new Actions(driver);
        return action;
    }

    //------------------------------------------------5---------------------------------------------------------------//

    /**
     *
     * @return
     * @throws AWTException
     */
    public static Robot getRobot() throws AWTException{
        Robot robot=new Robot();
        return robot;
    }

    //-------------------------------------------------6--------------------------------------------------------------//

    /**
     *
     * @param time
     * @return
     */

    public static Wait webDriverWait(long time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        return wait;
    }

    //-------------------------------------------------7--------------------------------------------------------------//

    /**
     * This Method is used to find the Element by generic search taking the locator type and locator value
     * And give the WebElement to perform Action on that element at required time
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @return element
     */

    public WebElement findElement(String by,String value){
        switch (by) {
            case "id":
                element = driver.findElement(By.id(value));
                break;
            case "name":
                element = driver.findElement(By.name(value));
                break;
            case "xpath":
                element = driver.findElement(By.xpath(value));
                break;
            case "css":
                element = driver.findElement(By.cssSelector(value));
                break;
            case "linkText":
                element = driver.findElement(By.linkText(value));
                break;
            case "partialLinkText":
                element = driver.findElement(By.partialLinkText(value));
                break;
            case "tagName":
                element = driver.findElement(By.tagName(value));
                break;
            case "className":
                element = driver.findElement(By.className(value));
                break;
        }
        return element;
    }

    //------------------------------------------------8---------------------------------------------------------------//

    /**
     *This Method is used to find the Element's by generic search taking the locator type and locator value
     * And give the list of WebElement to perform Action on the required element at required time
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @return element
     */

    public static List<WebElement> findElements(String by, String value) {

        List<WebElement> element=null;
        switch (by) {
            case "id":
                element=driver.findElements(By.id(value));
                break;
            case "name":
                element=driver.findElements(By.name(value));
                break;
            case "xpath":
                element=driver.findElements(By.xpath(value));
                break;
            case "css":
                element=driver.findElements(By.cssSelector(value));
                break;
            case "linkText":
                element=driver.findElements(By.linkText(value));
                break;
            case "partialLinkText":
                element=driver.findElements(By.partialLinkText(value));
                break;
            case "tagName":
                element=driver.findElements(By.tagName(value));
                break;
            case "className":
                element=driver.findElements(By.className(value));
                break;
        }
        return element;
    }

    //------------------------------------------------9---------------------------------------------------------------//

    /**
     * This method is used for Synchranizatio of waiting condition
     * @param i is the condition to wait for no of seconds before it go for next next step
     */

    public  int sleep(int i){
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
        }
        return i;
    }

    //-------------------------------------------------10-------------------------------------------------------------//

    /**
     * This Method will compare the expected title with the actual title and
     * @paramaTitle is the title which we r getting from(driver.getTitle())
     * @param eTitle is expected title which we r going to pass and it is compared with the actual title
     */

    public void verifyTitle(String eTitle){
            String atitle=driver.getTitle();
            Assert.assertEquals(atitle, eTitle);
    }

    //-----------------------------------------------11---------------------------------------------------------------//

    /**
     * This Method will wait till  the timeout (Number of seconds to wait to find the Title to appere)
     * and compare the expected title is matching with the actual title
     * @param time is used to wait number of seconds before it returns TimeOut.
     */

    public static void verifyTitle(long time, String eTitle)throws InterruptedException,IOException {
        try{
                webDriverWait(time).until(ExpectedConditions.titleContains(eTitle));
                String aTitle=driver.getTitle();
                //reporter.log(Status.PASS,"Page: "+aTitle+" is Verified");
            }
            catch(Exception e){
                String aTitle=driver.getTitle();
                //reporter.log(Status.FAIL,"Actual Title is NOT Matching with the Expected Title  Actual Title is: "+aTitle+" and Expected Title is: "+eTitle);
                Assert.fail();
            }
        }

    //------------------------------------------------12--------------------------------------------------------------//

    /**
     * This Method is used to get the URL of the current page
     * @return the Url of the page on which we are working on
     */

    public String getCurrentUrl() {
            return (driver.getCurrentUrl());
        }
    //------------------------------------------------13--------------------------------------------------------------//

    /**
     * This method is used to enter the url
     * @param URL url of the Application we are Accessing
     */

    public void enter_URL(String URL) {
        driver.get(URL);
    }

    //------------------------------------------------14--------------------------------------------------------------//

    /**
     * This Method is used to get multiple screenshots and store the screenshots in the specefied location or
     * folder in the ".png" format
     * @param imageFolderPath we need to specify the folder path in which we need to store all our screenshots
     * @return the Screenshot in the imageFolderPath
     */

    public String getScreenShot(String imageFolderPath) throws InterruptedException{
        String imagePath=imageFolderPath+"/"+getFormatedDateTime()+".png";
        TakesScreenshot page = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(page.getScreenshotAs(OutputType.FILE), new File(imagePath));
            //reporter.log(Status.INFO,"The ScreenShot is: "+getFormatedDateTime());
        }
        catch (Exception e) {
            //reporter.log(Status.INFO,"An Error occurred while taking ScreenShot");
            Assert.fail();
        }
        return imagePath;
    }

    //-----------------------------------------------15---------------------------------------------------------------//

    /**
     *This method is used Synchranization of FindElement and FindElements
     * @param time is the Waiting time duration for Synchranization
     */

    public void implicitwait(long time){

        driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }

    //------------------------------------------------16--------------------------------------------------------------//

    /**
     * This Method is used to perform click Action
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     */

    public void click(String by, String value, String eleName){
       try{
           findElement(by,value).click();
           //reporter.log(Status.PASS,"Clicked on: "+eleName);
       }catch (Exception e){
           //reporter.log(Status.FAIL,"FAIL: "+eleName+" is not appeared even after the time out");
           Assert.fail();
       }
    }

    //------------------------------------------------17--------------------------------------------------------------//

    /**
     * This Method is used to perform Composit Click Action on the Element
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param eleName is the name of the element on which Click Action to be performed
     */

    public void clickByActions(String by, String value, String eleName){

       try{
           WebElement ele=findElement(by,value);
           getAction().moveToElement(ele).click().perform();
           //reporter.log(Status.PASS,"Clicked on: "+eleName);
       }catch (Exception e) {
           //reporter.log(Status.FAIL, eleName + " is not appeared to click on the element");
           Assert.fail();
       }
    }

    //------------------------------------------------18--------------------------------------------------------------//

    /**
     * This method is used to click on the Element by waiting untill the element to be appear
     * @param time is the Synchranization time
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param eleName is the name of the element on which Click Action to be performed
     */

    public void clickElement(long time, String by, String value, String eleName){
        WebDriverWait wait=new WebDriverWait(driver,time);
        try{
            WebElement ele=wait.until(ExpectedConditions.visibilityOf(findElement(by,value)));
            ele.click();
            //reporter.log(Status.PASS,"Clicked on: "+eleName);
        }
        catch (Exception e){
            //reporter.log(Status.FAIL,eleName+" is not appeared even after the time out");
            Assert.fail();
        }
    }

    //------------------------------------------------19--------------------------------------------------------------//

    /**
     * This Method is used to enter the test data in to the required text field
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param data is the test data
     */

    public void sendkeys(String by, String value, String data){
        WebElement ele=findElement(by,value);
        ele.clear();
        ele.sendKeys(data);
    }

    //------------------------------------------------20--------------------------------------------------------------//

    /**
     * This Method is used to get the text present in the locator path
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @return text present in the locator
     */

    public String getText(String by, String value){
        String returnText = findElement(by, value).getText();
        //reporter.log(Status.INFO,returnText);
        return returnText;
    }

    //------------------------------------------------21--------------------------------------------------------------//

    /**
     * This Method will wait till  the timeout (Number of seconds to wait to find the Element to appere),
     * and compare the expected element is matching with the actual element
     * @param time is used to wait number of seconds before it returns TimeOut.
     *   element is  to be checking in a certain element to be identify.
     * @return faile for failure, pass for success.
     */

    public void verifyElementPresent(long time, String by, String value, String eleName){
        try{
            webDriverWait(time).until(ExpectedConditions.visibilityOf(findElement(by,value)));
          //  reporter.log(Status.PASS,eleName+" Element is Present and Verified");
        }
        catch(Exception e){
            //reporter.log(Status.FAIL,eleName+" Element is NOT Present even after Time Out");
            Assert.fail();
        }
    }

    //------------------------------------------------22--------------------------------------------------------------//

    /**
     * This Method is used to verify the URL of the Application
     * @param expectedUrl is the URL that we are going to compare with the obtained URL
     * @param time is the Waiting time out Untill the URL Appear
     */

    public void verifyURLhas(String expectedUrl, long time){
        webDriverWait(time).until(ExpectedConditions.urlContains(expectedUrl));
    }

    //------------------------------------------------23--------------------------------------------------------------//

    /**
     * This Method is used to print the names of all the matching elements
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param Text is the Message to be printed
     */

    public void count_Links(String by, String value, String Text){
            List<WebElement> ele = findElements(by, value);
            int alllinks = ele.size();
        //reporter.log(Status.INFO,Text + alllinks);
    }

    //------------------------------------------------24--------------------------------------------------------------//

    /**
     *
     * @param by
     * @param value
     * @param index
     */

    public void selectByIndex(String by,String value,int index){
        WebElement ele = findElement(by, value);
        Select select=new Select(ele);
        select.selectByIndex(index);
    }

    //------------------------------------------------25--------------------------------------------------------------//

    /**
     *
     * @param by
     * @param value
     * @param ExpValue
     */

    public void selectByValue(String by,String value,String ExpValue){
        WebElement ele = findElement(by, value);
        Select select=new Select(ele);
        select.selectByValue(ExpValue);
    }

    //------------------------------------------------26--------------------------------------------------------------//

    /**
     *
     * @param by
     * @param value
     * @param Text
     */

    public void selectByText(String by,String value,String Text){
        WebElement ele = findElement(by, value);
        Select select=new Select(ele);
        select.selectByVisibleText(Text);
    }

    //------------------------------------------------27--------------------------------------------------------------//

    /**
     *
     * @param by
     * @param value
     */

    public void printListBoxItems(String by,String value){
        List<WebElement> ele = findElements(by, value);
    }

    //------------------------------------------------28--------------------------------------------------------------//

    /**
     *
     * @param by
     * @param value
     * @throws AWTException
     *//*

    public void fileUPLoad(String by,String value) throws AWTException{
        findElement(by,value).click();
        // Press Enter
        getRobot().keyPress(KeyEvent.VK_ENTER);

        // Release Enter
        getRobot().keyRelease(KeyEvent.VK_ENTER);

        // Press CTRL+V
        getRobot().keyPress(KeyEvent.VK_CONTROL);
        getRobot().keyPress(KeyEvent.VK_V);

        // Release CTRL+V
        getRobot().keyRelease(KeyEvent.VK_CONTROL);
        getRobot().keyRelease(KeyEvent.VK_V);

        //Press Enter
        getRobot().keyPress(KeyEvent.VK_ENTER);
        getRobot().keyRelease(KeyEvent.VK_ENTER);
    }*/

    //------------------------------------------------29--------------------------------------------------------------//

    /**
     *
     * @param by1
     * @param val1
     * @param by2
     * @param val2
     */

    public void dragAndDrop(String by1,String val1,String by2,String val2){
        WebElement ele1 = findElement(by1, val1);
        WebElement ele2 = findElement(by2, val2);
        getAction().dragAndDrop(ele1,ele2).build().perform();
    }

    //------------------------------------------------30--------------------------------------------------------------//

    /**
     *
     * @param by
     * @param value
     * @param subBy
     * @param subValue
     * @throws InterruptedException
     */

    public void clickMenuSubItem(String by,String value,String subBy,String subValue) throws InterruptedException {
        WebElement ele =findElement(by,value);
        getAction().moveToElement(ele).build().perform();
        sleep(5);
        WebElement subEle = findElement(subBy,subValue);
        subEle.click();
    }

    //------------------------------------------------31--------------------------------------------------------------//

    /**
     *
     * @param time
     * @param by
     * @param value
     */

    public void waitforvisibilityofelement(long time,String by,String value) {
        WebElement ele = findElement(by, value);
        try {
            webDriverWait(time).until(ExpectedConditions.visibilityOf(ele));
        } catch (WebDriverException e) {
            throw e;
        }
    }

    //------------------------------------------------32--------------------------------------------------------------//
    public String print_Contant(WebDriver driver,String by, String value,String Text){
        String text="";
        try {
            List<WebElement> ele = findElements(by, value);
            int alllinks = ele.size();
            reporter.log(Status.INFO,"Total no of "+Text+" : " + alllinks);
            logger.log(Status.INFO,Text+" names are as follows: ");
            for (int i = 0; i < alllinks; i++) {
                WebElement link = ele.get(i);
                text = link.getText();
                logger.log(Status.INFO,i + 1 + ": " + text);
            }
        }catch (Exception e){
            logger.log(Status.FAIL,"No "+Text+"'s are present in side the Teaser");
        }
        return text;
    }
    }//------------------------------------------------33--------------------------------------------------------------//


}