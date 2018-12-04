package com.test;

import main.generics.BaseTest;
import main.generics.CommonMethods;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo extends BaseTest {
    @Test
    public void test()throws NullPointerException,InterruptedException{
        CommonMethods cm=new CommonMethods();
        cm.enter_URL("https://online.actitime.com/areddy");
        cm.verifyTitleContain("actiTIME - Login");
        cm.sendKeys("id","username","amarreddypull","UserName");
        cm.sendKeys("name","pwd","Amar1990","Password");
        cm.click("id","keepLoggedInCheckBox","ChBox");
        cm.click("xpath","//div[.='Login ']","loginBTN");
        cm.verifyTitleContain("actiTIME - Enter Time-Track");
        //cm.click("xpath","//span[.='New']","newLink");
        cm.sleep(5);
        cm.click("className","cursorImageLink","ele");
        cm.sendKeys("name","//*[@id='editDescriptionPopupText']","bhlicuglifgIL","Comment");
        cm.click("xpath","//*[@id='scbutton']","ok");
        cm.sleep(5);
        cm.click("className","cursorImageLink","SignIn Link");

        //cm.selectByText("id","ext-gen155","- New Customer -");
      //reporter.log(Status.PASS,"nmvcsmhgc");





    }

}
