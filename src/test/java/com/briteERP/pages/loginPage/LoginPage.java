package com.briteERP.pages.loginPage;

import com.briteERP.utilities.BasePage;
import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  extends BasePage {

    public LoginPage(){

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
        BriteUtils.sendKeys(userNameLocator,10,username);
        BriteUtils.sendKeys( passwordLocator,10,password);
        BriteUtils.waitForClickablility(logInButtonLocator,10).click();
    }


}

