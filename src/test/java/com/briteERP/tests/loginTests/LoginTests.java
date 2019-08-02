package com.briteERP.tests.loginTests;

import com.briteERP.pages.loginPages.LoginPages;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import com.briteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests  extends TestBase {


    @Test
    public void Test1(){
        LoginPages loginpage = new LoginPages();
        String username = ConfigurationReader.getProperty("username");
        String password = ConfigurationReader.getProperty("password");
        loginpage.login(username,password);
    }

    @Test
    public void negativeLoginTest1(){
        LoginPages loginPage = new LoginPages();
        loginPage.login("wrongusername", "wrongpassword");
        String message = loginPage.errorMessageElement.getText().trim();
        Assert.assertEquals(message, "Wrong login/password");
    }
}