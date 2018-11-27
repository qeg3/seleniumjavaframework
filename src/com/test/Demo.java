package com.test;

import main.generics.BaseTest;
import main.generics.CommonMethods;
import org.testng.annotations.Test;

public class Demo extends BaseTest {
    @Test
    public void test()throws Exception{
        CommonMethods cm=new CommonMethods();
        cm.enter_URL("http://toolsqa.com/");
        cm.sleep(10);
        cm.highLighterMethod("className","menu-text");
        cm.sleep(10);
    }
}
