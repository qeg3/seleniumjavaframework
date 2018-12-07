package com.test;

import main.generics.BaseTest;
import main.generics.CommonMethods;
import main.generics.Utilities;
import org.testng.annotations.Test;

public class DemoPositive extends BaseTest {
    @Test
    public void test()throws NullPointerException{
        String loginUserNameBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "UsernameTextBox", "LocatorType");
        String loginUserNameVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "UsernameTextBox", "LocatorValue");
        String loginPasswordBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "PasswordTextBox", "LocatorType");
        String loginPasswordVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "PasswordTextBox", "LocatorValue");
        String loginCheckBoxBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "LoginCheckBox", "LocatorType");
        String loginCheckBoxVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "LoginCheckBox", "LocatorValue");
        String loginBTNBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "LoginBTN", "LocatorType");
        String loginBTNval = Utilities.getcellData_OBR_PC(OBJECT_REPO, "LoginPage", "LoginBTN", "LocatorValue");
        String addTaskLinkBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "AddTasksLink", "LocatorType");
        String addTaskLinkVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "AddTasksLink", "LocatorValue");
        String searchBoxBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "SearchBox", "LocatorType");
        String searchBoxVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "SearchBox", "LocatorValue");
        String addNewTaskBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "NewTaskLink", "LocatorType");
        String addNewTaskVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "HomePage", "NewTaskLink", "LocatorValue");
        String menuElementBy = Utilities.getcellData_OBR_PC(OBJECT_REPO, "ToolsQA", "menuLink", "LocatorType");
        String menuElementVal = Utilities.getcellData_OBR_PC(OBJECT_REPO, "ToolsQA", "menuLink", "LocatorValue");
        String loginPageTitle = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "LoginPage");
        String homePageTitle = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "HomePage");
        String userName = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "Username");
        String pasword = Utilities.getcellData_Test(TEST_DATA, "ActiTIME", "Password");
        String qaURL = Utilities.getcellData_Test(TEST_DATA, "ToolsQA", "ToolsQAURL");
        String pageTitle = Utilities.getcellData_Test(TEST_DATA, "ToolsQA", "PageTitle");


        CommonMethods method=new CommonMethods();

        method.verifyTitle(loginPageTitle);
        String url = method.getCurrentUrl();
        method.verifyURL(url);
        method.visibilityOfElement(loginUserNameBy,loginUserNameVal,"UserName Text Box");
        method.sendKeys(loginUserNameBy,loginUserNameVal,userName,"UserName");
        method.sendKeys(loginPasswordBy,loginPasswordVal,pasword,"Password");
        method.click(loginCheckBoxBy,loginCheckBoxVal,"ChBox");
        method.getText(loginBTNBy,loginBTNval,"loginBTN");
        method.click(loginBTNBy,loginBTNval,"loginBTN");
        method.verifyTitleContain(homePageTitle);
        String url1 = method.getCurrentUrl();
        method.verifyURL(url1);
        method.highLightElement(addTaskLinkBy,addTaskLinkVal,"addTasksButton");
        method.isEnabled(searchBoxBy,searchBoxVal,"SearchBox");
        method.clickByActions(addNewTaskBy,addNewTaskVal,"NewTask");
        method.sleep(5);

        driver.navigate().to(qaURL);
        driver.getTitle();
        method.verifyTitleContain(pageTitle);
        String QAurl = method.getCurrentUrl();
        method.verifyURL(QAurl);
        method.moveToElement(menuElementBy, menuElementVal, "Selenium in Java");

        method.sleep(5);

        }
}
