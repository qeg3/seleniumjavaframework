package main.generics;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends BaseTest {
    public static WebDriver driver;
    private static WebElement element;

    //-----------------------------------------------1---------------------------------------------------------------//

    /**
     * This method is to intalize page
     * @param driver is the driver instance
     */

    public void GenericPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //-----------------------------------------------2---------------------------------------------------------------//

    /**
     * This method is used for Synchronization of waiting condition
     * @param i is the condition to wait for no of seconds before it go for next next step
     */

    public  int sleep(int i){
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
        }
        return i;
    }

    //-----------------------------------------------3---------------------------------------------------------------//

    /**
     * This Method will compare the expected title with the actual title and
     * @param driver is the driver instance
     * @paramaTitle is the title which we r getting from(driver.getTitle())
     * @param eTitle is expected title which we r going to pass and it is compared with the actual title
     */

    public void verifyTitle(WebDriver driver,String eTitle){
        String atitle=driver.getTitle();
        Assert.assertEquals(atitle, eTitle);
    }

    //-----------------------------------------------4---------------------------------------------------------------//

    /**
     * This Method will wait till  the timeout (Number of seconds to wait to find the Title to appear)
     * and compare the expected title is matching with the actual title
     * @param driver is the driver instance
     * @param time is used to wait number of seconds before it returns TimeOut.
     */

    public static void verifyTitle(WebDriver driver,long time,String eTitle)throws InterruptedException,IOException {
        WebDriverWait wait=new WebDriverWait(driver,time);
        try{
            wait.until(ExpectedConditions.titleContains(eTitle));
            String aTitle=driver.getTitle();
            reporter.log(Status.PASS,"Page: "+aTitle+" is Verified");
        }
        catch(Exception e){
            String aTitle=driver.getTitle();
            reporter.log(Status.FAIL,"Actual Title is NOT Matching with the Expected Title  Actual Title is: "+aTitle+" and Expected Title is: "+eTitle);
            Assert.fail();
        }
    }

    //------------------------------------------------5---------------------------------------------------------------//

    /**
     * This Method is used to get the URL of the current page
     * @param driver is the driver instance
     * @return the Url of the page on which we are working on
     */

    public String getCurrentUrl(WebDriver driver) {
        return (driver.getCurrentUrl());
    }

    //------------------------------------------------6---------------------------------------------------------------//

    /**
     * This method is used to enter the url
     * @param driver is the driver instance
     * @param URL url of the Application we are Accessing
     */

    public void enter_URL(WebDriver driver,String URL) {
        driver.get(URL);
    }

    //------------------------------------------------7---------------------------------------------------------------//

    /**
     * This Method is used to det the current date and time
     * @getFormatedDateTime is the format in which date has to return
     * @return the current date and time in "dd_MM_yyyy_hh_mm_ss" format
     */

    public static String getFormatedDateTime(){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        return simpleDate.format(new Date());
    }

    //-----------------------------------------------8---------------------------------------------------------------//

    /**
     * This Method is used to get multiple screenshots and store the screenshots in the specified location or
     * folder in the ".png" formate
     * @param driver is the driver instance
     * @param imageFolderPath we need to specify the folder path in which we need to store all our screenshots
     * @return the Screenshot in the imageFolderPath
     */

    public String getScreenShot(WebDriver driver,String imageFolderPath) throws InterruptedException{
        String imagePath=imageFolderPath+"/"+getFormatedDateTime()+".png";
        TakesScreenshot page = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(page.getScreenshotAs(OutputType.FILE), new File(imagePath));
            reporter.log(Status.INFO,"The ScreenShot is: "+getFormatedDateTime());
        }
        catch (Exception e) {
            reporter.log(Status.INFO,"An Error occurred while taking ScreenShot");
            Assert.fail();
        }
        return imagePath;
    }

    //-----------------------------------------------9----------------------------------------------------------------//

    /**
     * This Method is used to get screenshot and store the screenshots in the specified location or
     * folder in the ".png" format
     * @param driver is the driver instance
     * @param imageFolderPath we need to specify the folder path in which we need to store all our screenshots
     * @return the Screenshot in the imageFolderPath
     */

    public String getScreenShots(WebDriver driver,String imageFolderPath)throws InterruptedException{

        String imagePath=imageFolderPath+"/"+getFormatedDateTime()+".png";
        TakesScreenshot page = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(page.getScreenshotAs(OutputType.FILE), new File(imagePath));
            reporter.log(Status.INFO,"The ScreenShot is: "+getFormatedDateTime());
        }
        catch (Exception e) {
            reporter.log(Status.INFO,"An Error occurred while taking ScreenShot");
            Assert.fail();
        }
        return imagePath;
    }

    //------------------------------------------------10--------------------------------------------------------------//

    /**
     * This Method is used to get the stable or unchanged Data from the Property File
     * @filePath it is the path where we store all the values and we can get it using key
     * @key it is the key value by which we are going to identify the value
     * @return it will return the String Value
     */

    public static String getPropertyValue(String filePath,String key) {
        String value="";
        Properties ppt=new Properties();
        try{
            ppt.load(new FileInputStream(filePath));
            value=ppt.getProperty(key);
        }
        catch(Exception e){
        }
        return value;
    }

    //------------------------------------------------11--------------------------------------------------------------//

    /**
     * This Method is used to initialize composite actions
     * @param driver is the driver instance
     * @return action
     */

    public static Actions getAction(WebDriver driver) {
        Actions action = new Actions(driver);
        return action;
    }

    //------------------------------------------------12--------------------------------------------------------------//

    /**
     *This method is used Synchronization of FindElement and FindElements
     * @param driver is the driver instance
     * @param time is the Waiting time duration for Synchronization
     */

    public void implicitwait(WebDriver driver,long time){

        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }

    //------------------------------------------------13--------------------------------------------------------------//

    /**
     * This Method is used to find the Element by generic search taking the locator type and locator value
     * And give the WebElement to perform Action on that element at required time
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @return element
     */

    public WebElement findElement(WebDriver driver,String by,String value){
        switch (by.toUpperCase()) {
            case "ID":
                element = driver.findElement(By.id(value));
                break;
            case "NAME":
                element = driver.findElement(By.name(value));
                break;
            case "XPATH":
                element = driver.findElement(By.xpath(value));
                break;
            case "CSS":
                element = driver.findElement(By.cssSelector(value));
                break;
            case "LINKTEXT":
                element = driver.findElement(By.linkText(value));
                break;
            case "PARTIALLINKTEXT":
                element = driver.findElement(By.partialLinkText(value));
                break;
            case "TAGNAME":
                element = driver.findElement(By.tagName(value));
                break;
            case "CLASSNAME":
                element = driver.findElement(By.className(value));
                break;
        }
        return element;
    }


    //------------------------------------------------14--------------------------------------------------------------//

    /**
     * This Method is used to perform click Action
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     */

    public void click(WebDriver driver,String by,String value,String eleName){
        try{
            findElement(driver,by,value).click();
            reporter.log(Status.PASS,"Clicked on: "+eleName);
        }catch (Exception e){
            reporter.log(Status.FAIL,"FAIL: "+eleName+" is not appeared even after the time out");
            Assert.fail();
        }
    }

    //------------------------------------------------15--------------------------------------------------------------//

    /**
     * This Method is used to perform Composit Click Action on the Element
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param eleName is the name of the element on which Click Action to be performed
     */

    public void clickByActions(WebDriver driver,String by,String value,String eleName){

        try{
            WebElement ele=findElement(driver,by,value);
            getAction(driver).moveToElement(ele).click().perform();
            reporter.log(Status.PASS,"Clicked on: "+eleName);
        }catch (Exception e) {
            reporter.log(Status.FAIL, eleName + " is not appeared to click on the element");
            Assert.fail();
        }
    }

    //------------------------------------------------16--------------------------------------------------------------//

    /**
     * This method is used to click on the Element by waiting untill the element to be appear
     * @param driver is the driver instance
     * @param time is the Synchronization time
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param eleName is the name of the element on which Click Action to be performed
     */

    public void clickElement(WebDriver driver,long time,String by,String value,String eleName){
        WebDriverWait wait=new WebDriverWait(driver,time);
        try{
            WebElement ele=wait.until(ExpectedConditions.visibilityOf(findElement(driver,by,value)));
            ele.click();
            reporter.log(Status.PASS,"Clicked on: "+eleName);
        }
        catch (Exception e){
            reporter.log(Status.FAIL,eleName+" is not appeared even after the time out");
            Assert.fail();
        }
    }

    //------------------------------------------------17--------------------------------------------------------------//

    /**
     * This Method is used to enter the test data in to the required text field
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param data is the test data
     */

    public void sendkeys(WebDriver driver,String by,String value,String data){
        WebElement ele=findElement(driver,by,value);
        ele.clear();
        ele.sendKeys(data);
    }

    //------------------------------------------------18--------------------------------------------------------------//

    /**
     * This Method is used to get the text present in the locator path
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @return text present in the locator
     */

    public String getText(WebDriver driver,String by,String value){
        String returnText = findElement(driver,by, value).getText();
        reporter.log(Status.INFO,returnText);
        return returnText;
    }

    //------------------------------------------------19--------------------------------------------------------------//

    /**
     * This Method will wait till  the timeout (Number of seconds to wait to find the Element to appere),
     * and compare the expected element is matching with the actual element
     * @param driver is the driver instance
     * @param time is used to wait number of seconds before it returns TimeOut.
     *   element is  to be checking in a certain element to be identify.
     * @return faile for failure, pass for success.
     */

    public void verifyElementPresent(WebDriver driver,long time,String by,String value,String eleName){
        WebDriverWait wait=new WebDriverWait(driver,time);
        try{
            wait.until(ExpectedConditions.visibilityOf(findElement(driver,by,value)));
            reporter.log(Status.PASS,eleName+" Element is Present and Verified");
        }
        catch(Exception e){
            reporter.log(Status.FAIL,eleName+" Element is NOT Present even after Time Out");
            Assert.fail();
        }
    }

    //------------------------------------------------20--------------------------------------------------------------//

    /**
     * This Method is used to verify the URL of the Application
     * @param driver is the driver instance
     * @param expectedUrl is the URL that we are going to compare with the obtained URL
     * @param time is the Waiting time out Untill the URL Appear
     */

    public void verifyURLhas(WebDriver driver,String expectedUrl,long time){
        new WebDriverWait(driver,time).until(ExpectedConditions.urlContains(expectedUrl));
    }

    //------------------------------------------------21--------------------------------------------------------------//

    /**
     *This Method is used to find the Element's by generic search taking the locator type and locator value
     * And give the list of WebElement to perform Action on the required element at required time
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @return element
     */

    public static List<WebElement> findElements(WebDriver driver, String by, String value) {

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

    //------------------------------------------------22--------------------------------------------------------------//

    /**
     * This Method is used to print the names of all the matching elements
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param Text is the Message to be printed
     * @return text
     */

    public String print_Contant(WebDriver driver,String by, String value,String Text){
        String text="";
        try {
            List<WebElement> ele = findElements(driver, by, value);
            int alllinks = ele.size();
            reporter.log(Status.INFO,"Total no of "+Text+" : " + alllinks);
            reporter.log(Status.INFO,Text+" names are as follows: ");
            for (int i = 0; i < alllinks; i++) {
                WebElement link = ele.get(i);
                text = link.getText();
                reporter.log(Status.INFO,i + 1 + ": " + text);
            }
        }catch (Exception e){
            reporter.log(Status.FAIL,"No "+Text+"'s are present in side the Teaser");
        }
        return text;
    }

    //--------------------------------------------------23--------------------------------------------------------------//

    /**
     * This Method is used to print the names of all the matching elements
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param Text is the Message to be printed
     */

    public void count_Links(WebDriver driver,String by, String value,String Text){
        List<WebElement> ele = findElements(driver, by, value);
        int alllinks = ele.size();
        reporter.log(Status.INFO,"Total no of "+Text+" in the teaser is: " + alllinks);
    }

    //------------------------------------------------24--------------------------------------------------------------//

    /**
     * This Method is used to print the names of all the matching elements by swiping till the last element
     * @param driver is the driver instance
     * @param by is the locator type by which we are going to locate
     * @param value it the locator value of the element
     * @param sBy is the swiping element locator type by which we are going to locate
     * @param sValue it the swiping element locator value of the element
     * @param Text is the Message to be printed
     * @return text;
     */

    public String swipe_print_Contant(WebDriver driver,String by, String value,String sBy,String sValue,String Text){
        String text="";
        try {
            int j = 0;
            List<WebElement> ele = findElements(driver,by,value);
            int allLinks = ele.size();
            reporter.log(Status.INFO,Text+" names are as follows: ");
            for (int i = 0; i < allLinks; i++) {
                WebElement link = ele.get(i);
                text = link.getText();
                if(text.length()!=0) {
                    reporter.log(Status.INFO,  j + 1 + ": " + text);
                    j = j+1;
                }
                if ((i+1)% 4 == 0) {
                    sleep(1);
                    WebElement ele1 = findElement(driver,sBy,sValue);
                    ele1.click();
                }
            }
            reporter.log(Status.INFO,"Total no of "+Text+" : " + j);
        }catch (Exception e){
            reporter.log(Status.FAIL,"No "+Text+"'s are present in side the Teaser");
            Assert.fail();
        }
        return text;
    }

    //------------------------------------------------25--------------------------------------------------------------//

    /**
     * This method is used to initialize the java script execution method
     * @param driver is the driver instance
     * @return jse;
     */

    public static JavascriptExecutor jsExecutor(WebDriver driver){
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        return jse;
    }

    //------------------------------------------------26--------------------------------------------------------------//

    /**
     * This method is used to verify the vide player
     * @param driver is the driver instance
     * @param message is the message to be printed on the reports
     */

    public void verifyVideoPlay(WebDriver driver,String message){
        sleep(20);
        jsExecutor(driver).executeScript("jwplayer().pause()");
        sleep(5);
        jsExecutor(driver).executeScript("jwplayer().play();");
        sleep(20);
        reporter.log(Status.INFO,message);
    }

    //------------------------------------------------27--------------------------------------------------------------//

    /**
     *
     * @param driver
     * @param by
     * @param value
     * @param SelectText
     */

    public void searchSuggested(WebDriver driver,String by, String value,String SelectText){
        List<WebElement> ele = findElements(driver,by,value);
        String []allLinks =new String[ele.size()];

        int j=0;
        for(WebElement a: ele) {
            allLinks[j] = a.getText();
            if (allLinks[j].equalsIgnoreCase(SelectText)) {
                ele.get(j).click();
                break;
            }
            j++;
        }
    }

    //------------------------------------------------28--------------------------------------------------------------//

    /**
     *
     * @param driver
     * @param by
     * @param value
     * @param sBy
     * @param sValue
     * @param dayBy
     * @param dayVal
     * @return
     */

    public String printProgramDate(WebDriver driver,String by, String value,String sBy,String sValue,String dayBy,String dayVal) {
        String text="";
        List<WebElement> ele = findElements(driver,by,value);
        int allLinks = ele.size();
        reporter.log(Status.INFO, "Program names are as follows: ");
        Actions action = new Actions(driver);

        int j = 0;
        for (int i = 0; i < allLinks; i++) {
            sleep(3);
            WebElement link = ele.get(i);
            action.moveToElement(link).build().perform();
            text = link.getText();
            if (text.length() != 0) {
                String data = findElement(driver,sBy,sValue).getText();
                String time=findElement(driver,dayBy,dayVal).getText();
                reporter.log(Status.INFO, j + 1 + ": " + text + " :  "+time+" " + data);
                j = j + 1;
            }
        }
        reporter.log(Status.INFO, "Total no of Programs are : " + j);

        return text;
    }

    //------------------------------------------------29--------------------------------------------------------------//


}
