package main.generics;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.taskdefs.condition.IsTrue;
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
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonMethods extends BaseTest {

    public static WebElement element;

    //------------------------------------------------1---------------------------------------------------------------//

    /**
     * This method is to initialize page.
     */
    public void GenericPage() {
        PageFactory.initElements(driver, this);
    }
    //------------------------------------------------2---------------------------------------------------------------//

    /**
     * This method is used to initialize the java script execution method.
     * @return jse;
     */
    public static JavascriptExecutor jsExecutor(){
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        return jse;
    }
    //------------------------------------------------3---------------------------------------------------------------//

    /**
     * This Method is used to get the current date and time.
     * @getFormatedDateTime is the format in which date has to return,
     * @return the current date and time in "dd_MM_yyyy_hh_mm_ss" format.
     */
    public static String getFormatedDateTime(){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        return simpleDate.format(new Date());
    }
    //------------------------------------------------4---------------------------------------------------------------//

    /**
     * This method is used for the initialize of Actions Class.
     * This Method is used to initialize composite actions.
     * @return action.
     */
    public static Actions getAction() {
        Actions action = new Actions(driver);
        return action;
    }
    //------------------------------------------------5---------------------------------------------------------------//

    /**
     * This method is used for the initialize of Robot Class.
     * @return robot.
     * @throws Exception handling Exception.
     */
    public static Robot getRobot() throws Exception{
        Robot robot=new Robot();
        return robot;
    }
    //-------------------------------------------------6--------------------------------------------------------------//

    /**
     * This Method is used for the initialize Explicit Waiting time.
     * @param time is the Explicit Waiting time to find the Element
     * @return wait;
     */
    public static Wait webDriverWait(long time){
        WebDriverWait wait = new WebDriverWait(driver,time);
        return wait;
    }
    //-------------------------------------------------7--------------------------------------------------------------//

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
    //------------------------------------------------8---------------------------------------------------------------//

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
     * This Method will compare the expected title with the actual title and.
     * @paramaTitle is the title which we r getting from(driver.getTitle()),
     * @param eTitle is expected title which we r going to pass and it is compared with the actual title.
     */
    public void verifyTitle(String eTitle){
            String atitle=driver.getTitle();
            Assert.assertEquals(atitle, eTitle);
    }
    //-----------------------------------------------11---------------------------------------------------------------//

    /**
     * This Method will wait till  the timeout (Number of seconds to wait to find the Title to appear.
     * and compare the expected title is matching with the actual title,
     * @param time is the Explicit Waiting time to find the Element,
     * @throws Exception Handling Exception.
     */
    public static void verifyTitle(long time, String eTitle)throws Exception {
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
     * @throws Exception Handling Exception.
     */
    public String getScreenShot(String imageFolderPath) throws Exception{
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
     * @param time is the Implicit Waiting time to find the Element
     */
    public void implicitwait(long time){

        driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    //------------------------------------------------16--------------------------------------------------------------//

    /**
     * This Method is used to perform click Action
     * @param by Element locator Type,
     * @param value Element locator Value,
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
     * @param by Element locator Type,
     * @param value Element locator Value,
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
     * This method is used to click on the Element by waiting un till the element to be appear
     * @param time is the Explicit Waiting time to find the Element
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param eleName is the name of the element on which Click Action to be performed
     */
    public void visabilityOfElement(long time, String by, String value, String eleName){
        WebDriverWait wait=new WebDriverWait(driver,time);
        try{
            WebElement ele=wait.until(ExpectedConditions.visibilityOf(findElement(by,value)));
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
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param data is the test data
     */
    public void sendKeys(String by, String value, String data){
        WebElement ele=findElement(by,value);
        ele.clear();
        ele.sendKeys(data);
    }
    //------------------------------------------------20--------------------------------------------------------------//

    /**
     * This Method is used to get the text present in the locator path
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @return text present in the locator
     */
    public String getText(String by, String value){
        String text = findElement(by, value).getText();
        //reporter.log(Status.INFO,returnText);
        return text;
    }
    //------------------------------------------------21--------------------------------------------------------------//

    /**
     * This Method is used to verify the URL of the WebPage
     * @param expectedUrl is the URL that we are going to compare with the obtained URL
     * @param time is the Explicit Waiting time to find the Element
     */
    public void verifyURLhas(String expectedUrl, long time){
        webDriverWait(time).until(ExpectedConditions.urlContains(expectedUrl));
    }
    //------------------------------------------------22--------------------------------------------------------------//

    /**
     * This Method is used to print the names of all the matching elements
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param Text is the Message to be printed
     */
    public void count_Links(String by, String value, String Text){
            List<WebElement> ele = findElements(by, value);
            int alllinks = ele.size();
        //reporter.log(Status.INFO,Text + alllinks);
    }
    //------------------------------------------------23--------------------------------------------------------------//

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
    //------------------------------------------------24--------------------------------------------------------------//

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
    //------------------------------------------------25--------------------------------------------------------------//

    /**
     * This Method is used to Select any Element in the list Box by sending Text as input.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param Text Select Element based on Text.
     */
    public void selectByText(String by,String value,String Text){
        WebElement ele = findElement(by, value);
        Select select=new Select(ele);
        select.selectByVisibleText(Text);
    }
    //------------------------------------------------26--------------------------------------------------------------//

    /**
     * This Method is used to find the list box and print all the elements present in the list Box.
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public void printListBoxItems(String by,String value){
        List<WebElement> ele = findElements(by, value);
    }
    //------------------------------------------------27--------------------------------------------------------------//

    /**
     * This Method is used to Upload a file in to any WebElement or the Popup.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @throws Exception Handling Exception
     */

    public void fileUPLoad(String by,String value) throws Exception{
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
    }
    //------------------------------------------------28--------------------------------------------------------------//

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
        getAction().dragAndDrop(ele1,ele2).build().perform();
    }
    //------------------------------------------------29--------------------------------------------------------------//

    /**
     * This Method is used to move the mouse to the Menu item and click on the sub Element.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param subBy SubElement locator Type,
     * @param subValue SubElement locator Value,
     * @throws Exception Handling Exception.
     */
    public void clickMenuSubElement(String by,String value,String subBy,String subValue) throws Exception {
        WebElement ele =findElement(by,value);
        getAction().moveToElement(ele).build().perform();
        sleep(5);
        WebElement subEle = findElement(subBy,subValue);
        subEle.click();
    }
    //------------------------------------------------30--------------------------------------------------------------//

    /**
     *This Method is used to check no links present in the WebPage and print all the links.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param Text is the Text message that will print in the report,
     * @return text.
     */
    public String printContent(String by, String value,String Text){
        String text="";
        try {
            List<WebElement> ele = findElements(by, value);
            int alllinks = ele.size();
            reporter.log(Status.INFO,"Total no of "+Text+" : " + alllinks);
            reporter.log(Status.INFO,Text+" names are as follows: ");
            for (int i = 0; i < alllinks; i++) {
                WebElement link = ele.get(i);
                text = link.getText();
                reporter.log(Status.INFO,i + 1 + ": " + text);
            }
        }catch (Exception e){
            reporter.log(Status.FAIL,"No "+Text+"'s are present in side the Element");
        }
        return text;
    }
    //------------------------------------------------31--------------------------------------------------------------//

    /**
     * This Method is used to Highlight any WebElement On Demand.
     * @param by Element locator Type,
     * @param value Element locator Value,
     */
    public void highLighterMethod(String by,String value){
        WebElement ele = findElement(by, value);
        jsExecutor().executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", ele);
    }
    //------------------------------------------------32--------------------------------------------------------------//

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
    //------------------------------------------------33--------------------------------------------------------------//

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
    //------------------------------------------------34--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the Value present inside the frame..
     * @param by Element locator Type,
     * @param value Element locator Value.
     */
    public void frameByName(String by, String value,String Name){
        List<WebElement> frames = findElements(by, value);
        int frameSize = frames.size();
        reporter.log(Status.PASS,"Total no of frames in the list are : "+frameSize);
        driver.switchTo().frame(Name);
    }
    //------------------------------------------------35--------------------------------------------------------------//

    /**
     * This Method is used is used to Count the total no of frames present inside the Web page and
     * Select the desired Frame by using the text present inside the frame.
     * @param by Element locator Type,
     * @param value Element locator Value.
     */
    public void frameByText(String by, String value,String ID){
        List<WebElement> frames = findElements(by, value);
        int frameSize = frames.size();
        reporter.log(Status.PASS,"Total no of frames in the list are : "+frameSize);
        driver.switchTo().frame(ID);
    }
    //------------------------------------------------36--------------------------------------------------------------//

    /**
     * This Method will check weather the given Element is Enabled or Disabled and
     * Compares the Actual Result with the Expected Result.
     * @param by Element locator Type,
     * @param value Element locator Value,
     * @param expValue expected Result that is compared with the Actual Result
     */
    public  void isEnabled(String by, String value, String expValue){
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
    //------------------------------------------------37--------------------------------------------------------------//

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
    //------------------------------------------------38--------------------------------------------------------------//

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
    //------------------------------------------------39--------------------------------------------------------------//

    /**
     * This Method is used to establish Connection with the DataBase and Execute the Query
     *  and gives out thr result of the Query in the results.
     * @param dbName name of the data base
     * @param dbURL url of the DataBase in which the Query has to be executed,
     * @param sqlQuery The Executable Query to Execute.
     * @throws SQLException handling Exceptions
     */
    public Connection conn = null;
    private String dbName = null;
    public void Database(String dbName, String dbURL,String sqlQuery)throws SQLException{
        this.dbName = dbName;
        try {
            Class.forName(SQL_DRIVER);
            this.conn = DriverManager.getConnection(dbURL);//here put the new simple url.
            Statement sta = conn.createStatement();
            ResultSet result = sta.executeQuery(sqlQuery);
            reporter.info("Oracle query "+sqlQuery+" output is : "+result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //------------------------------------------------40--------------------------------------------------------------//
}