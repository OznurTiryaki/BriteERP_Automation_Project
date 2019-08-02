package com.briteERP.pages.loginPages;

import com.briteERP.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPages {

    public LoginPages(){

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id="login")
    public WebElement userNameLocator;

    @FindBy(id="password")
    public WebElement passwordLocator;

    @FindBy(css = "button[class='btn btn-primary']")
    public WebElement logInButtonLocator;

    @FindBy(className = "custom-checkbox__icon")
    public WebElement rememberMeElement;

    @FindBy(partialLinkText = "Forgot your password?")
    public WebElement forgotPasswordElement;

    @FindBy(tagName = "h2")
    public WebElement titleElement;

    @FindBy(css = ".alert.alert-danger")
    public WebElement errorMessageElement;

    public void login(String username, String password){
        userNameLocator.sendKeys(username);
        passwordLocator.sendKeys(password);
        logInButtonLocator.click();
    }


}

