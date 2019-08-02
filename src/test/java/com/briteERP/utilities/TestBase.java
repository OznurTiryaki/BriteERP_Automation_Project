package com.briteERP.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.util.concurrent.TimeUnit;

public class TestBase {
    //should be public/protected !!!!
    public WebDriver driver;
    public Actions action;  //we dont need to initiliaze again action class in our test class

    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver();
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Long.valueOf(ConfigurationReader.getProperty("implicitwait")), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ConfigurationReader.getProperty("url"));
    }

    @AfterMethod
    public void teardown(){

        Driver.closeDriver();
    }


}
