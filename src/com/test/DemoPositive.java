package com.test;

import main.generics.BaseTest;
import main.generics.CommonMethods;
import org.testng.annotations.Test;

public class DemoPositive extends BaseTest {
    @Test
    public void test()throws NullPointerException,InterruptedException{

        CommonMethods method=new CommonMethods();

        method.verifyTitle("actiTIME - Login");
        String url = method.getCurrentUrl();
        method.verifyURL(url);
        method.visibilityOfElement("id","username","UserName Text Box");
        method.sendKeys("id","username","amarreddypull","UserName");
        method.sendKeys("name","pwd","Amar1990","Password");
        method.click("id","keepLoggedInCheckBox","ChBox");
        method.getText("xpath","//div[.='Login ']","loginBTN");
        method.click("xpath","//div[.='Login ']","loginBTN");
        method.verifyTitleContain("actiTIME - Enter Time-Track");
        String url1 = method.getCurrentUrl();
        method.verifyURL(url1);
        method.highLightElement("className","addTasksButton","addTasksButton");
        method.isEnabled("className","taskSearchField","SearchBox");
        method.clickByActions("className","dashedLink","NewTask");
        method.sleep(5);



        }
}
