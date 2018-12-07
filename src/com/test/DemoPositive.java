package com.test;

import main.generics.BaseTest;
import main.generics.CommonMethods;
import main.generics.Utilities;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DemoPositive extends BaseTest {
    @Test
    public void test()throws NullPointerException{
        String unBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "UnTb", "LocatorType");
        String unVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "UnTb", "LocatorValue");
        String pwBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "PwTb", "LocatorType");
        String pwval = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "PwTb", "LocatorValue");
        String chBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "ChBox", "LocatorType");
        String chVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "ChBox", "LocatorValue");
        String loginBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "LoginBTN", "LocatorType");
        String loginval = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "LoginBTN", "LocatorValue");
        String addTaskBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "AddTasksButton", "LocatorType");
        String addTaskVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "AddTasksButton", "LocatorValue");
        String searchBoxBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "SearchBox", "LocatorType");
        String searchBoxVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "SearchBox", "LocatorValue");
        String newTaskBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "NewTaskLink", "LocatorType");
        String newTaskVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "NewTaskLink", "LocatorValue");
        String menuEleBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "ToolsQA", "menuLink", "LocatorType");
        String menuEleVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "ToolsQA", "menuLink", "LocatorValue");
        String loginPageTitle = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "LoginPage");
        String homePageTitle = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "HomePage");
        String userName = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "Username");
        String pasword = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "Password");
        String qaURL = Utilities.getcellData_Test(TEST_DATA, "ToolsQA", "URL");
        String pageTitle = Utilities.getcellData_Test(TEST_DATA, "ToolsQA", "PageTitle");


        CommonMethods method=new CommonMethods();

        method.verifyTitle(loginPageTitle);
        String url = method.getCurrentUrl();
        method.verifyURL(url);
        method.visibilityOfElement(unBy,unVal,"UserName Text Box");
        method.sendKeys(unBy,unVal,userName,"UserName");
        method.sendKeys(pwBy,pwval,pasword,"Password");
        method.click(chBy,chVal,"ChBox");
        method.getText(loginBy,loginval,"loginBTN");
        method.click(loginBy,loginval,"loginBTN");
        method.verifyTitleContain(homePageTitle);
        String url1 = method.getCurrentUrl();
        method.verifyURL(url1);
        method.highLightElement(addTaskBy,addTaskVal,"addTasksButton");
        method.isEnabled(searchBoxBy,searchBoxVal,"SearchBox");
        method.clickByActions(newTaskBy,newTaskVal,"NewTask");
        method.sleep(5);

        driver.navigate().to(qaURL);
        driver.getTitle();
        method.verifyTitleContain(pageTitle);
        String QAurl = method.getCurrentUrl();
        method.verifyURL(QAurl);
        method.moveToElement(menuEleBy, menuEleVal, "Selenium in Java");

        method.sleep(5);

        }
}
