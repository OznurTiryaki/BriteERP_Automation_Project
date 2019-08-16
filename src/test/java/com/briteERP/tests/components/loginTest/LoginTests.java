package com.briteERP.tests.components.loginTest;

import com.briteERP.pages.loginPage.LoginPage;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests  extends TestBase {


    @Test
    public void Test1(){

        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");
        pages.loginPage().login(username,password);
    }

    @Test
    public void negativeLoginTest1(){

        pages.loginPage().login("wrongusername", "wrongpassword");
        String message =     pages.loginPage().errorMessageElement.getText().trim();
        Assert.assertEquals(message, "Wrong login/password");
    }
}