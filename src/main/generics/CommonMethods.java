package main.generics;

import com.aventstack.extentreports.Status;
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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends BaseTest {

    //------------------------------------------------1---------------------------------------------------------------//

    public static WebElement element;
    private static final long ETO =50;
    public Actions action = new Actions(driver);
    public JavascriptExecutor jse=(JavascriptExecutor)driver;
    //------------------------------------------------2---------------------------------------------------------------//

    /**
     * This method is used to initialize generic page.
     */
    public void genericPage() {
        PageFactory.initElements(driver, this);
    }
    //------------------------------------------------3---------------------------------------------------------------//

    /**
     * This Method is used to get the current date and time.
     * is the format in which date has to return,
     * @return the current date and time in "dd_MM_yyyy_hh_mm_ss" format.
     */
    public static String getFormattedDateTime(){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        return simpleDate.format(new Date());
    }
    //-------------------------------------------------4--------------------------------------------------------------//

    /**
     * This Method is used to find the Element by generic search taking the locator type and locator value
     * And give the WebElement to perform Action on that element at required time.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @return element.
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
    //------------------------------------------------5---------------------------------------------------------------//

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
    //------------------------------------------------6---------------------------------------------------------------//

    /**
     * This method is used to specify the Waiting Condition.
     * @param time is the condition to wait for no of seconds before it go for next step
     */
    public  int sleep(int time)throws InterruptedException{
            Thread.sleep(time*1000);
        return time;
    }
    //-------------------------------------------------7-------------------------------------------------------------//

    /**
     * This Method will compare the expected title with the actual title and.
     * Title is the title which we r getting from(driver.getTitle()),
     * @param eTitle is expected title which we r going to pass and it is compared with the actual title.
     */
    public void verifyTitle(String eTitle){
            String aTitle=driver.getTitle();
            Assert.assertEquals(aTitle, eTitle);
    }
    //------------------------------------------------8---------------------------------------------------------------//

    /**
     * This Method will wait till  the timeout (Number of seconds to wait to find the Title to appear.
     * and compare the expected title is matching with the actual title,
     */
    public static void waitTitleContain(String eTitle) {
        try{
            new WebDriverWait(driver,ETO).until(ExpectedConditions.titleContains(eTitle));
                String aTitle=driver.getTitle();
                reporter.log(Status.PASS,"Page: "+aTitle+" is Verified");
            }
            catch(Exception e){
                String aTitle=driver.getTitle();
                reporter.log(Status.FAIL,"Actual Title is NOT Matching with the Expected Title  Actual Title is: "+aTitle+" and Expected Title is: "+eTitle);
                Assert.fail();
            }
        }
    //-------------------------------------------------9--------------------------------------------------------------//

    /**
     * This Method is used to get the URL of the current page
     * @return the Url of the page on which we are working on
     */
    public String getCurrentUrl() {
            return (driver.getCurrentUrl());
        }
    //------------------------------------------------10--------------------------------------------------------------//

    /**
     * This method is used to enter the url
     * @param url url of the Application we are Accessing
     */
    public void enter_URL(String url) {
        driver.get(url);
    }
    //------------------------------------------------11--------------------------------------------------------------//

    /**
     * This Method is used to get multiple screenshots and store the screenshots in the location or
     * folder in the ".png" format
     * @param imageFolderPath we need to specify the folder path in which we need to store all our screenshots
     * @return the Screenshot in the imageFolderPath
     */
    public String getScreenShot(String imageFolderPath) {
        String imagePath=imageFolderPath+"/"+getFormattedDateTime()+".png";
        TakesScreenshot page = (TakesScreenshot) driver;
        try {
            FileUtils.copyFile(page.getScreenshotAs(OutputType.FILE), new File(imagePath));
            reporter.log(Status.INFO,"The ScreenShot is: "+getFormattedDateTime());
        }
        catch (Exception e) {
            reporter.log(Status.INFO,"An Error occurred while taking ScreenShot");
            Assert.fail();
        }
        return imagePath;
    }
    //-----------------------------------------------12---------------------------------------------------------------//

    /**
     *This method is used synchronization of FindElement and FindElements
     * @param time is the Implicit Waiting time to find the Element
     */
    public void implicitWait(long time){

        driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    //------------------------------------------------13--------------------------------------------------------------//

    /**
     * This Method is used to perform click Action
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public void click(String by, String value, String eleName){
       try{
           findElement(by,value).click();
           reporter.log(Status.PASS,"Clicked on: "+eleName);
       }catch (Exception e){
           reporter.error("Unable to perform Click operation on the element the isERROR :" + e.getMessage().split("\n")[0].trim());
           Assert.fail();
       }
    }
    //------------------------------------------------14--------------------------------------------------------------//

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
           reporter.log(Status.PASS,"Clicked on: "+eleName);
       }catch (Exception e) {
           reporter.error("Unable to perform Click operation on the element the isERROR :" + e.getMessage().split("\n")[0].trim());
           Assert.fail();
       }
    }
    //------------------------------------------------15--------------------------------------------------------------//

    /**
     * This method is used to click on the Element by waiting un till the element to be appear
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public void visibilityOfElement(String by, String value,String eleName){
        WebDriverWait wait=new WebDriverWait(driver,ETO);
        try{
            wait.until(ExpectedConditions.visibilityOf(findElement(by,value)));
            reporter.log(Status.PASS,"Clicked on: "+eleName);
        }
        catch (Exception e){
            reporter.error(eleName+" Element is not Visible even after the time out");
            Assert.fail();
        }
    }
    //------------------------------------------------16--------------------------------------------------------------//

    /**
     * This Method is used to enter the test data in to the required text field
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param data is the test data
     */
    public void sendKeys(String by, String value, String data){
        WebElement ele=findElement(by,value);
        ele.clear();
        ele.sendKeys(data);
    }
    //------------------------------------------------17--------------------------------------------------------------//

    /**
     * This Method is used to get the text present in the locator path
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @return text present in the locator
     */
    public String getText(String by, String value){
        String text = findElement(by, value).getText();
        reporter.log(Status.INFO,text);
        return text;
    }
    //------------------------------------------------18--------------------------------------------------------------//

    /**
     * This Method is used to verify the URL of the WebPage
     * @param expectedURL is the URL that we are going to compare with the obtained URL
     */
    public void verifyURL(String expectedURL){
        new WebDriverWait(driver,ETO).until(ExpectedConditions.urlContains(expectedURL));
    }
    //------------------------------------------------19--------------------------------------------------------------//

    /**
     * This Method is used to print the names of all the matching elements
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param text is the Message to be printed
     */
    public void countLinks(String by, String value, String text){
            List<WebElement> ele = findElements(by, value);
            int allLinks = ele.size();
        reporter.log(Status.INFO,text + allLinks);
    }
    //------------------------------------------------20--------------------------------------------------------------//

    /**
     * This Method is used to Select any Element in the list Box by sending Index as input.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param index Select Element based on Index.
     */
    public void selectByIndex(String by,String value,int index){
        WebElement ele = findElement(by, value);
        Select select=new Select(ele);
        select.selectByIndex(index);
    }
    //------------------------------------------------21--------------------------------------------------------------//

    /**
     * This Method is used to Select any Element in the list Box by sending expValue as input.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param expValue Select Element based on Value.
     */
    public void selectByValue(String by,String value,String expValue){
        WebElement ele = findElement(by, value);
        Select select=new Select(ele);
        select.selectByValue(expValue);
    }
    //------------------------------------------------22--------------------------------------------------------------//

    /**
     * This Method is used to Select any Element in the list Box by sending Text as input.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param text Select Element based on Text.
     */
    public void selectByText(String by,String value,String text){
        WebElement ele = findElement(by, value);
        Select select=new Select(ele);
        select.selectByVisibleText(text);
    }
    //------------------------------------------------23--------------------------------------------------------------//

    /**
     * This Method is used to Upload a file in to any WebElement or the Popup.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @throws Exception Handling Exception
     */
    public void fileUPLoad(String by,String value) throws Exception{
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
    }
    //------------------------------------------------24--------------------------------------------------------------//

    /**
     * This Method is used to swap the Elements from one place to another.
     * @param by1 Element1 locator Type,
     * @param val1 Element1 locator Value,
     * @param by2 Element2 locator Type,
     * @param val2 Element2 locator Value.
     */
    public void dragAndDrop(String by1,String val1,String by2,String val2){
        WebElement ele1 = findElement(by1, val1);
        WebElement ele2 = findElement(by2, val2);
        action.dragAndDrop(ele1,ele2).build().perform();
    }
    //------------------------------------------------25--------------------------------------------------------------//

    /**
     * This Method is used to move the mouse to the Menu item and click on the sub Element.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param subBy SubElement locator Type,
     * @param subValue SubElement locator Value,
     */
    public void clickMenuSubElement(String by,String value,String subBy,String subValue) throws InterruptedException{
        WebElement ele =findElement(by,value);
        action.moveToElement(ele).build().perform();
        sleep(5);
        WebElement subEle = findElement(subBy,subValue);
        subEle.click();
    }
    //------------------------------------------------26--------------------------------------------------------------//

    /**
     *This Method is used to check no links present in the WebPage and print all the links.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param elementName is the Text message that will print in the report,
     * @return text.
     */
    public String printContent(String by, String value,String elementName){
        String text="";
        try {
            List<WebElement> ele = findElements(by, value);
            int allLinks = ele.size();
            reporter.log(Status.INFO,"Total no of "+elementName+" : " + allLinks);
            reporter.log(Status.INFO,elementName+" names are as follows: ");
            for (int i = 0; i < allLinks; i++) {
                WebElement link = ele.get(i);
                text = link.getText();
                reporter.log(Status.INFO,i + 1 + ": " + text);
            }
        }catch (Exception e){
            reporter.log(Status.FAIL,"No "+elementName+"'s are present in side the Element");
        }
        return text;
    }
    //------------------------------------------------27--------------------------------------------------------------//

    /**
     * This Method is used to Highlight any WebElement On Demand.
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public void highLighterMethod(String by,String value){
        WebElement ele = findElement(by, value);
        jse.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
    }
    //------------------------------------------------28--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page.
     * @param by Element locator Type,
     * @param value Element locator Value.
     */
    public void countFrames(String by, String value){
        List<WebElement> frames = findElements(by, value);
        int frameSize = frames.size();
        reporter.log(Status.PASS,"Total no of frames in the list are : "+frameSize);
    }
    //------------------------------------------------29--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the Index of the frame.
     * @param by Element locator Type,
     * @param value Element locator Value.
     */
    public void frameByIndex(String by, String value,int index){
        List<WebElement> frames = findElements(by, value);
        int frameSize = frames.size();
        reporter.log(Status.PASS,"Total no of frames in the list are : "+frameSize);
        driver.switchTo().frame(index);
    }
    //------------------------------------------------30--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the Value present inside the frame.
     * @param by Element locator Type,
     * @param value Element locator Value.
     */
    public void frameByName(String by, String value,String name){
        List<WebElement> frames = findElements(by, value);
        int frameSize = frames.size();
        reporter.log(Status.PASS,"Total no of frames in the list are : "+frameSize);
        driver.switchTo().frame(name);
    }
    //------------------------------------------------31--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the text present inside the frame.
     * @param by Element locator Type,
     * @param value Element locator Value.
     */
    public void frameByText(String by, String value,String id){
        List<WebElement> frames = findElements(by, value);
        int frameSize = frames.size();
        reporter.log(Status.PASS,"Total no of frames in the list are : "+frameSize);
        driver.switchTo().frame(id);
    }
    //------------------------------------------------32--------------------------------------------------------------//

    /**
     * This Method will check weather the given Element is Enabled or Disabled and
     * Compares the Actual Result with the Expected Result.
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public  void isEnabled(String by, String value){
        WebElement ele = findElement(by, value);
        try {
            if (ele.isEnabled()) {
                reporter.log(Status.PASS, "The Element is Enabled");
            } else {
                reporter.fail("The Element is Disabled");
            }
        }catch (Exception e){
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
        Class.forName(SQL_DRIVER);
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sqlQuery);
        reporter.info("Sql query "+sqlQuery+" output is : "+rs);
    }
    //------------------------------------------------34--------------------------------------------------------------//

    /**
     *  This Method is used to establish Connection with the DataBase and Execute the Query
     *  and gives out thr result of the Query in the results.
     * @param dbUrl  url of the DataBase in which the Query has to be executed,
     * @param username DateBase login UserName,
     * @param password DataBase Login Password,
     * @param query The Executable Query to Execute.
     * @throws Exception handling Exceptions
     */
    public void dbOracleQuery(String dbUrl,String username,String password,String query) throws Exception{
        Class.forName(ORACLE_DRIVER);
        Connection con = DriverManager.getConnection(dbUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        reporter.info("Oracle query "+query+" output is : "+rs);
    }
    //------------------------------------------------45--------------------------------------------------------------//

    /**
     * This method is used to scroll the web page to desired location and click on the Element.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param name Element name;
     */
    public void clickElementByScrollPage(String by, String value, String name) {

        try {
            WebElement ele = findElement(by, value);
            try {
                jse.executeScript("arguments[0].scrollIntoView(true);", ele);
                reporter.info(name + " is available and is scrolled into view.");
            } catch (Exception e) {
                reporter.error(name + " is not available and cannot be scrolled into view. Error: " + e);
                throw e;
            }
            ele.click();
        } catch (Exception e) {
            reporter.error("Unable to perform Click operation on the element " + name.trim() + "ERROR :" + e.getMessage().split("\n")[0].trim());
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
                WebElement chiEle = findElement(childBy, childValue);
                if(getEle.equals(chiEle)){
                    break;
                }
            }
        }catch (Exception e){
            reporter.error("Unable to Match the element with the Expected Element and the ERROR :" + e.getMessage().split("\n")[0].trim());
        }
        return getEle;
    }
    //------------------------------------------------37--------------------------------------------------------------//
}