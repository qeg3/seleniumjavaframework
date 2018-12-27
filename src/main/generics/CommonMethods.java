package main.generics;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends BaseTest {

    //------------------------------------------------1---------------------------------------------------------------//
    private static final long ETO =10;
    private static Actions action = new Actions(driver);
    private static JavascriptExecutor jse=(JavascriptExecutor)driver;
    //------------------------------------------------2---------------------------------------------------------------//

    /**
     * This method is used to initialize generic page.
     */
    public void genericPage() {
        PageFactory.initElements(driver, this);
    }
    //------------------------------------------------3---------------------------------------------------------------//

    /**
     * This Method is used to get the root cause for the Exception caused during Execution.
     * @param e is the object of the Exception,
     * @return error message text.
     */
    private static String getErrorMessage(Exception e){
        String error=null;
        String[] message = e.getMessage().split(":");
        String screenshotPath = getScreenShot();
        error= message[0].trim()+" : "+ message[1].trim()+" - Element info : "+ message[message.length - 1].trim()+reporter.addScreenCapture(screenshotPath);
        return error;
    }
    //-------------------------------------------------4--------------------------------------------------------------//

    /**
     * This Method is used to get the current date and time.
     * is the format in which date has to return,
     * @return the current date and time in "dd_MM_yyyy_hh_mm_ss" format.
     */
    public static String getFormattedDateTime(){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        return simpleDate.format(new Date());
    }
    //-------------------------------------------------5--------------------------------------------------------------//

    /**
     * This Method is used to find the Element by generic search taking the locator type and locator value
     * And give the WebElement to perform Action on that element at required time.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @return element.
     */
    public static WebElement findElement(String by,String value){
        WebElement element=null;
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
    //------------------------------------------------6---------------------------------------------------------------//

    /**
     *This Method is used to find the Element's by generic search taking the locator type and locator value
     * And give the list of WebElement to perform Action on the required element at required time.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @return element.
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
    //------------------------------------------------7---------------------------------------------------------------//

    /**
     * This method is used to specify the Waiting Condition.
     * @param time is the condition to wait for no of seconds before it go for next step
     */
    public void sleep(int time){
        try {
            Thread.sleep(time * 1000);
        }catch (final InterruptedException e){
            reporter.log(LogStatus.ERROR,"The Entered time format is incorrect"+getErrorMessage(e));
            Assert.fail();
        }
    }
    //-------------------------------------------------8-------------------------------------------------------------//

    /**
     * This Method will compare the expected title with the actual title and.
     * Title is the title which we r getting from(driver.getTitle()),
     * @param eTitle is expected title which we r going to pass and it is compared with the actual title.
     */
    public void verifyTitle(String eTitle){
        String aTitle = driver.getTitle();
        try {
            Assert.assertEquals(aTitle, eTitle);
            reporter.log(LogStatus.PASS," ActualTitle : "+aTitle+" is matching with the ExpectedTitle: "+eTitle);
        }catch (Exception e){
            reporter.log(LogStatus.ERROR," ActualTitle : "+aTitle+" is not matching with the ExpectedTitle : "+eTitle+" and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //-------------------------------------------------9--------------------------------------------------------------//

    /**
     * This Method will wait till  the timeout (Number of seconds to wait to find the Title to appear.
     * and compare the expected title is matching with the actual title,
     * @param eTitle is expected title which we r going to pass and it is compared with the actual title.
     */
    public void verifyTitleContain(String eTitle) {
        new WebDriverWait(driver,ETO).until(ExpectedConditions.titleContains(eTitle));
        String aTitle=driver.getTitle();
        try{
                Assert.assertEquals(aTitle, eTitle);
                reporter.log(LogStatus.PASS," ActualTitle : "+aTitle+" is matching with the ExpectedTitle: "+eTitle);
            } catch(Exception e){
                reporter.log(LogStatus.ERROR," ActualTitle : "+aTitle+" is not matching with the ExpectedTitle : "+eTitle+" and the ERROR is : "+getErrorMessage(e));
                Assert.fail();
            }
        }
    //------------------------------------------------10--------------------------------------------------------------//

    /**
     * This Method is used to get the URL of the current page
     * @return the Url of the page on which we are working on
     */
    public String getCurrentUrl() {
        String url = driver.getCurrentUrl();
        reporter.log(LogStatus.INFO,"the URL of the web page is : "+url);
            return (url);
        }
    //------------------------------------------------11--------------------------------------------------------------//

    /**
     * This method is used to enter the url
     * @param url url of the Application we are Accessing
     */
    public static void enter_URL(String url) {
        driver.get(url);
        reporter.log(LogStatus.INFO,"The Entered URL is : "+url);
    }
    //------------------------------------------------12--------------------------------------------------------------//

    /**
     * This Method is used to get screenshots and store the screenshot in the project directory
     * @return finalImgPath - Returns the Screenshot folder location.
     */

    public static String platform(){
        String platform = System.getProperty("os.name");
        String value=null;
        platform = platform.toUpperCase();
        if (platform.contains("WINDOWS")) {
            value= "Windows";
        }else if (platform.contains("MAC")) {
            value= "Mac";
        }
        return value;
    }
    public static String getScreenShot(){
        String platform = platform();
        String finalImgPath = null;
        try {
            String imageName = getFormattedDateTime() + ".png";
            TakesScreenshot page = (TakesScreenshot) driver;
            switch (platform) {
                case "Windows":
                    String imagePath=PHOTO_PATH+"/"+imageName;
                    FileUtils.copyFile(page.getScreenshotAs(OutputType.FILE), new File(imagePath));
                    File dirName = new File(System.getProperty("user.dir"));
                    finalImgPath = dirName + "\\" + PHOTO_PATH+"\\"+imageName;
                    break;
                case "Mac":
                    File source = page.getScreenshotAs(OutputType.FILE);
                    finalImgPath = System.getProperty("user.dir") +"/"+ PHOTO_PATH  +"/"+imageName;
                    File finalDestination = new File(finalImgPath);
                    FileUtils.copyFile(source, finalDestination);
                    dirName = new File(System.getProperty("user.dir"));
                    String folderName = dirName.getName();
                    folderName = "/"+folderName;
                    int index = finalImgPath.indexOf(folderName);
                    String screenshotPath = finalImgPath.substring(index);
                    Path absolutePath = Paths.get(finalImgPath);
                    Path relativePath = Paths.get(screenshotPath);
                    Path finalPath = absolutePath.relativize(relativePath);
                    finalImgPath = finalPath.toString();
                    break;
            }
            reporter.log(LogStatus.INFO,"The ScreenShot is: "+imageName+" is Stored in "+PHOTO_PATH);
        }catch (Exception e){
            reporter.log(LogStatus.FAIL,"An Error occurred while taking ScreenShot Because of : "+e.getMessage().split("\n")[0].trim());
            Assert.fail();
        }

        return finalImgPath;
    }
    //------------------------------------------------13--------------------------------------------------------------//

    /**
     *This method is used synchronization of FindElement and FindElements
     * @param time is the Implicit Waiting time to find the Element
     */
    public static void implicitWait(long time){
        driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
        reporter.log(LogStatus.INFO,"Implicit time is set to : "+time+" Seconds");
    }
    //------------------------------------------------14--------------------------------------------------------------//

    /**
     * This Method is used to perform click Action
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the Text message that will print in the report.
     */
    public void click(String by, String value, String eleName){
       try{
           findElement(by,value).click();
           reporter.log(LogStatus.PASS,"Clicked on: "+eleName);
       }catch (Exception e){
           reporter.log(LogStatus.ERROR,"Failed to perform Click operation on "+eleName+" and the Error is : " + getErrorMessage(e));
       }
    }
    //------------------------------------------------15--------------------------------------------------------------//

    /**
     * This Method is used to perform Composite Click Action on the Element
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the name of the element on which Click Action to be performed
     */
    public void clickByActions(String by, String value, String eleName){
       try{
           WebElement ele=findElement(by,value);
           action.moveToElement(ele).click().perform();
           reporter.log(LogStatus.PASS,"Clicked on: "+eleName);
       }catch (Exception e) {
           reporter.log(LogStatus.ERROR,"Failed to perform Click operation on "+eleName+" and the ERROR is : " +getErrorMessage(e));
       }
    }
    //------------------------------------------------16--------------------------------------------------------------//

    /**
     * This method is used to click on the Element by waiting un till the element to be appear
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public void visibilityOfElement(String by, String value,String eleName){
        WebDriverWait wait=new WebDriverWait(driver,ETO);
        try{
            wait.until(ExpectedConditions.visibilityOf(findElement(by, value)));
            reporter.log(LogStatus.PASS,eleName+" is Visible");
        } catch (Exception e){
            reporter.log(LogStatus.ERROR,eleName+" Element is not Visible even after the time out and the Error is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------17--------------------------------------------------------------//

    /**
     * This Method is used to enter the test data in to the required text field
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param data is the test data,
     * @param eleName is the Text message that will print in the report.
     */
    public void sendKeys(String by, String value, String data,String eleName){
        try {
            WebElement ele = findElement(by, value);
            ele.clear();
            ele.sendKeys(data);
            if(eleName.equalsIgnoreCase("Password")){
                data="**********";
            }
            reporter.log(LogStatus.PASS,data+" : Entered in the "+eleName+" Text Field");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to enter "+data+" in the "+eleName+" Text Field and the Error is : " +getErrorMessage(e));
        }
    }
    //------------------------------------------------18--------------------------------------------------------------//

    /**
     * This Method is used to get the text present in the locator path
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the Text message that will print in the report,
     */
    public void getText(String by, String value,String eleName){
        String text ="";
        try {
            text = findElement(by, value).getText();
            reporter.log(LogStatus.INFO,"The Text present in the "+eleName+" is "+text);
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to get Text present in the "+eleName+" and the Error is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------19--------------------------------------------------------------//

    /**
     * This Method is used to verify the URL of the WebPage
     * @param expectedURL is the URL that we are going to compare with the obtained URL
     */
    public void verifyURL(String expectedURL){
        new WebDriverWait(driver, ETO).until(ExpectedConditions.urlContains(expectedURL));
        String currentUrl = driver.getCurrentUrl();
        try {
            Assert.assertTrue(currentUrl.contains(expectedURL));
            reporter.log(LogStatus.PASS,"ActualURL : "+currentUrl+" is matching with the ExpectedURL : "+expectedURL);
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"ActualURL : "+currentUrl+" is not matching with the ExpectedURL : "+expectedURL+" and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------20--------------------------------------------------------------//

    /**
     * This Method is used to print the names of all the matching elements
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public int countLinks(String by, String value,String webPage){
        int allLinks=0;
        try{
            List<WebElement> ele = findElements(by, value);
            allLinks = ele.size();
            reporter.log(LogStatus.INFO,"Total no of links present in the "+webPage+" are : " + allLinks);
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Unable to count the total no of links present in the "+webPage+" and the Error is : "+e.getMessage().split("\n")[0].trim());
        }
        return allLinks;
    }
    //------------------------------------------------21--------------------------------------------------------------//

    /**
     * This Method is used to Select any Element in the list Box by sending Index as input.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the Text message that will print in the report,
     * @param index Select Element based on Index.
     */
    public void selectByIndex(String by,String value,int index,String eleName){
        try {
            Select select=new Select(findElement(by, value));
            select.selectByIndex(index);
            reporter.log(LogStatus.PASS,eleName+" is selected from the Dropdown with the given Index");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to select the "+eleName+" from the Dropdown with the given Index and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------22--------------------------------------------------------------//

    /**
     * This Method is used to Select any Element in the list Box by sending expValue as input.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the Text message that will print in the report,
     * @param expValue Select Element based on Value.
     */
    public void selectByValue(String by,String value,String expValue,String eleName){
        try{
            Select select=new Select(findElement(by, value));
            select.selectByValue(expValue);
            reporter.log(LogStatus.PASS,eleName+" is selected from the Dropdown with the given Value");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to select the "+eleName+" from the Dropdown with the given Value and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------23--------------------------------------------------------------//

    /**
     * This Method is used to Select any Element in the list Box by sending Text as input.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the Text message that will print in the report,
     * @param text Select Element based on Text.
     */
    public void selectByText(String by,String value,String text,String eleName){
        try{
            Select select=new Select(findElement(by, value));
            select.selectByVisibleText(text);
            reporter.log(LogStatus.PASS,eleName+" is selected from the Dropdown with the given Text");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to select the "+eleName+" from the Dropdown with the given Text and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------24--------------------------------------------------------------//

    /**
     * This Method is used to Upload a file in to any WebElement or the Popup.
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public void fileUPLoad(String by,String value) {
        try{
            findElement(by,value).click();
            Robot rb=new Robot();
            // Press Enter
            rb.keyPress(KeyEvent.VK_ENTER);

            // Release Enter
            rb.keyRelease(KeyEvent.VK_ENTER);

            // Press CTRL+V
            rb.keyPress(KeyEvent.VK_CONTROL);
            rb.keyPress(KeyEvent.VK_V);

            // Release CTRL+V
            rb.keyRelease(KeyEvent.VK_CONTROL);
            rb.keyRelease(KeyEvent.VK_V);

            //Press Enter
            rb.keyPress(KeyEvent.VK_ENTER);
            rb.keyRelease(KeyEvent.VK_ENTER);
            reporter.log(LogStatus.PASS,"The fail got uploaded into the Up load path Successfully");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to upLoad file in to the path and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------25--------------------------------------------------------------//

    /**
     * This Method is used to swap the Elements from one place to another.
     * @param by1 Element1 locator Type,
     * @param val1 Element1 locator Value,
     * @param by2 Element2 locator Type,
     * @param val2 Element2 locator Value.
     */
    public void dragAndDrop(String by1,String val1,String by2,String val2){
        try{
            WebElement ele1 = findElement(by1, val1);
            WebElement ele2 = findElement(by2, val2);
            action.dragAndDrop(ele1,ele2).build().perform();
            reporter.log(LogStatus.PASS,"The two Elements got Swapped Successfully");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to swap the Two Elements and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------26--------------------------------------------------------------//

    /**
     *This Method is used to check no links present in the WebPage and print all the links.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the Text message that will print in the report.
     */
    public void printContent(String by, String value,String eleName){
        String text="";
        try {
            List<WebElement> ele = findElements(by, value);
            int allLinks = ele.size();
            reporter.log(LogStatus.INFO,"Total no of elements in the "+eleName+" is : " + allLinks);
            reporter.log(LogStatus.INFO,eleName+" names are as follows: ");
            for (int i = 0; i < allLinks; i++) {
                WebElement link = ele.get(i);
                text = link.getText();
                reporter.log(LogStatus.INFO,i + 1 + ": " + text);
            }
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"No "+eleName+"'s are present in side the Element and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------27--------------------------------------------------------------//

    /**
     * This Method is used to Highlight any WebElement On Demand.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the Text message that will print in the report.
     */
    public void highLightElement(String by,String value,String eleName){
        try{
            WebElement ele = findElement(by, value);
            jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
            String screenshotPath = getScreenShot();
            reporter.log(LogStatus.PASS,eleName+" : is Highlighted"+reporter.addScreenCapture(screenshotPath));
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to Highlight "+eleName+" and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------28--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page.
     * @param by Element locator Type,
     * @param value Element locator Value.
     */
    public void countFrames(String by, String value,String webPage){
        try{
            List<WebElement> frames = findElements(by, value);
            int frameSize = frames.size();
            if (frameSize>0) {
                reporter.log(LogStatus.PASS, "Total no of frames in the "+webPage+" are : " + frameSize);
            }else {
                reporter.log(LogStatus.PASS, "No frames present in the "+ webPage);
            }
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to get the Frame Count in the "+webPage+" and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------29--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the Index of the frame.
     * @param frameName Element name that is print in the Report and to select the Frame.
     */
    public void frameByIndex(int index,String frameName){
        try{
            driver.switchTo().frame(index);
            reporter.log(LogStatus.PASS,frameName+" Frame is Selected");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to select the "+frameName+" Frame with the given Index and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------30--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the Value present inside the frame.
     * @param name Element name that is print in the Report and to select the Frame.
     */
    public void frameByName(String name){
        try{
            driver.switchTo().frame(name);
            reporter.log(LogStatus.PASS,name+" Frame is Selected");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to select the "+name+" Frame with the given Name and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------31--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the text present inside the frame.
     * @param frameName Element name that is print in the Report.
     */
    public void frameByID(String id,String frameName){
        try {
            driver.switchTo().frame(id);
            reporter.log(LogStatus.PASS,frameName+" Frame is Selected");
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to select the "+frameName+" Frame with the given ID and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------32--------------------------------------------------------------//

    /**
     * This Method will check weather the given Element is Enabled or Disabled and
     * Compares the Actual Result with the Expected Result.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName Element name that is print in the Report.
     */
    public  void isEnabled(String by, String value,String eleName){
        try {
            WebElement ele = findElement(by, value);
            if (ele.isEnabled()) {
                reporter.log(LogStatus.PASS, eleName+": Element is Enabled");
            } else {
                reporter.log(LogStatus.FAIL,eleName+": Element is Disabled");
            }
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Unable to find the "+eleName+" is enabled or disabled and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------33--------------------------------------------------------------//

    /**
     *  This Method is used to establish Connection with the DataBase and Execute the Query
     *  and gives out thr result of the Query in the results.
     * @param dbUrl  url of the DataBase in which the Query has to be executed,
     * @param username DateBase login UserName,
     * @param password DataBase Login Password,
     * @param sqlQuery The Executable Query to Execute.
     * @throws Exception handling Exceptions
     */
    public void dbSQLQuery(String dbUrl,String username,String password,String sqlQuery) throws Exception {
        try {
            Class.forName(SQL_DRIVER);
            Connection con = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            reporter.log(LogStatus.INFO,"Sql query " + sqlQuery + " output is : " + rs);
        }catch (Exception e){
             reporter.log(LogStatus.ERROR,"Unable to execute the Query and the ERROR is :"+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------34--------------------------------------------------------------//

    /**
     *  This Method is used to establish Connection with the DataBase and Execute the Query
     *  and gives out thr result of the Query in the results.
     * @param dbUrl  url of the DataBase in which the Query has to be executed,
     * @param username DateBase login UserName,
     * @param password DataBase Login Password,
     * @param query The Executable Query to Execute.
     */
    public void dbOracleQuery(String dbUrl,String username,String password,String query) {
        try {
            Class.forName(ORACLE_DRIVER);
            Connection con = DriverManager.getConnection(dbUrl, username, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            reporter.log(LogStatus.INFO,"Oracle query "+query+" output is : "+rs);
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Unable to execute the Query and the ERROR is :"+getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------35--------------------------------------------------------------//

    /**
     * This method is used to scroll the web page to desired location and click on the Element.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName Element name that is print in the Report.
     */
    public void clickElementByScrollPage(String by, String value, String eleName) {
        try {
            WebElement ele = findElement(by, value);
            try {
                jse.executeScript("arguments[0].scrollIntoView(true);", ele);
                reporter.log(LogStatus.INFO,eleName.trim() + " is available and is scrolled into view.");
            } catch (Exception e) {
                reporter.log(LogStatus.ERROR,eleName.trim() + " is not available and cannot be scrolled into view. Error: " + e);
                throw e;
            }
            ele.click();
        } catch (Exception e) {
            reporter.log(LogStatus.ERROR,"Unable to perform Click operation on " + eleName.trim() + "ERROR :" +getErrorMessage(e));
            Assert.fail();
        }
    }
    //------------------------------------------------36--------------------------------------------------------------//

    /**
     * This Method is used to click on the Child Element passing from the parent Element.
     * @param parBy parent Element locator Type,
     * @param parValue parent Element locator Value,
     * @param childBy Child Element locator Type,
     * @param childValue Child Element locator Value,
     */
    public Object getChild(String parBy, String parValue,String childBy, String childValue){
        WebElement getEle=null;
        try {
            List<WebElement> parEle = findElements(parBy, parValue);
            int eleSize = parEle.size();
            for(int i=0;i<=eleSize;i++) {
                 getEle = parEle.get(i);
                String text = getEle.getText();
                WebElement chiEle = findElement(childBy, childValue);
                if(getEle.equals(chiEle)){
                    break;
                }
                reporter.log(LogStatus.PASS,"Element "+text.trim()+" : is Selected");
            }
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Unable to Match the actualElement with the expectedElement and the ERROR :"+getErrorMessage(e));
            Assert.fail();
        }
        return getEle;
    }
    //------------------------------------------------37--------------------------------------------------------------//

    /**
     * This Method is used to handle multiple action on the Alert.
     * @param action can perform actions like "Switch to Alert","Accept the Alert","Dismiss the Alert",
     * "SendData to Alert" and perform the action based on the action given
     * @param data the Data that has to be entered in to the text field.
     */
    public void handleAlert(String action,String data){
        try {
            switch (action) {
                case "switch":
                    driver.switchTo().alert();
                    reporter.log(LogStatus.PASS,"Switched to Alert");
                    break;
                case "accept":
                    driver.switchTo().alert().accept();
                    reporter.log(LogStatus.PASS,"Alert is Accepted");
                    break;
                case "dismiss":
                    driver.switchTo().alert().dismiss();
                    reporter.log(LogStatus.PASS,"Alert is Dismissed");
                    break;
                case "sendKeys":
                    driver.switchTo().alert().sendKeys(data);
                    reporter.log(LogStatus.PASS,data+" is Entered in the Alert");
                    break;
            }
        }catch (Exception e){
            reporter.log(LogStatus.ERROR,"Failed to perform "+action+" on the Alert and the ERROR is : "+getErrorMessage(e));
            Assert.fail();
        }
    }

    //------------------------------------------------37--------------------------------------------------------------//

    /**
     * This Method is used to perform composite Action.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName Element name that is print in the Report.
     */
    public void moveToElement(String by,String value,String eleName){
        List<String> byList = new ArrayList<String>(Arrays.asList(by.split("\\|")));
        List<String> valueList = new ArrayList<String>(Arrays.asList(value.split("\\|")));
        if(byList.size()==valueList.size()) {
            for (int i=0;i<=byList.size()-1;i++) {
                String byData = byList.get(i);
                String valueData = valueList.get(i);
                try {
                    WebElement ele = findElement(byData, valueData);
                    action.moveToElement(ele).build().perform();
                    if(i==byList.size()-1){
                        action.moveToElement(ele).click().perform();
                        reporter.log(LogStatus.PASS,"moved to : "+eleName+" and clicked");
                        break;
                    }
                    sleep(2);
                } catch (Exception e) {
                    reporter.log(LogStatus.ERROR, "Failed to move to : " + eleName + " and the Error is " + getErrorMessage(e));
                }
            }
        }else {
            reporter.log(LogStatus.ERROR,"The given locator type and locator values length are not matching please check the given parameters");
        }
    }
    //------------------------------------------------38--------------------------------------------------------------//
}