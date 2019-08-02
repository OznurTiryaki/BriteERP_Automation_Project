package com.briteERP.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class BriteUtils {


    private WebDriver driver = Driver.getDriver();
    //we don't want to access these variables outside
    private static String usernamelocator = "prependedInput";
    private static String passwordLocator = "prependedInput2";
    private static String loaderMaskLocator = "div[class='loader-mask shown']";
    private static String pageSubTitleLocator = "h1[class='oro-subtitle']";



    /**
     *
     * @param expectedResult
     * @param actualResult
     * Verifies if two strings are equals.
     */
    public static void verifyEquals(String expectedResult, String actualResult){
        if(expectedResult.equals(actualResult)){
            System.out.println("Passed");
        }else{
            System.out.println("Failed");
            System.out.println("Expected result: "+expectedResult);
            System.out.println("Actual result: "+actualResult);
        }
    }
    /**
     *  This method will put on pause execution
     * @param seconds
     */
    public static void waitPlease(int seconds){
        try {
            Thread.sleep(seconds * 1000 );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     *
     * @param page
     * @param driver
     * This method will open example page based on link name
     */
    public static void openPage(String page, WebDriver driver){
        //we will find all examples on the home page
        List<WebElement> listOfExamples = driver.findElements(By.tagName("a"));
        for(WebElement example: listOfExamples){
            if(example.getText().contains(page)){
                example.click();
                break;
            }
        }
    }
    public static void verifyIsDisplayed(WebElement element){
        if(element.isDisplayed()){
            System.out.println("PASSED");
            System.out.println(element.getText()+": is visible");
        }else{
            System.out.println("FAILED");
            System.out.println(element.getText()+": is not visible!");
        }
    }
    /**
     * This method will recover in case of exception after unsuccessful the click,
     * and will try to click on element again.
     * @param driver
     * @param by
     * @param attempts
     */
    public static void clickWithWait(WebDriver driver, By by, int attempts){
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while(counter < attempts) {
            try {
                //selenium must look for element again
                driver.findElement(by).click();
                //if click is successful - then break
                break;
            } catch (WebDriverException e) {
                //if click failed
                //print exception
                System.out.println(e);
                //print attempt
                System.out.println("Attempt :: " + ++counter);
                //wait for 1 second, and try to click again
                waitPlease(1);
            }
        }
    }

    /**
     * This method will recover in case of exception after unsuccessful the click,
     * and will try to click on element again.
     * @param driver
     * @param attempts
     */
    public static void clickWithWait(WebDriver driver, WebElement element, int attempts){
        int counter = 0;
        //click on element as many as you specified in attempts parameter
        while(counter < attempts) {
            try {
                //selenium must look for element again
                element.click();
                //if click is successful - then break
                break;
            } catch (WebDriverException e) {
                //if click failed
                //print exception
                System.out.println(e);
                //print attempt
                System.out.println("Attempt :: " + ++counter);
                //wait for 1 second, and try to click again
                waitPlease(1);
            }
        }
    }
    /**OVERLOADED WITHOUT DRIVER
     * This method is to send keys with Explicit waits
     * you will change time, or elements, this is biggest advantages
     * @param element
     * @param timeout
     * @param value
     */
    public static void sendKeys( WebElement element, int timeout, String value){
        new WebDriverWait(Driver.getDriver(),timeout).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }

    /**
     * This method is to send keys with Explicit waits
     * you will change time, or elements, this is biggest advantages
     * @param driver
     * @param element
     * @param timeout
     * @param value
     */
    public static void sendKeys(WebDriver driver, WebElement element, int timeout, String value){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(value);
    }
    /**
     * This method for click button with with Explicit waits
     * @param driver
     * @param element
     * @param timeout
     */
    public static void clickOn(WebDriver driver, WebElement element, int timeout){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    /**
     * This method for click button with with Explicit waits
     * @param element
     * @param timeout
     */
    public static void clickOn( WebElement element, int timeout){
        new WebDriverWait(Driver.getDriver(),timeout).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }


    /**
     * This method to verify a Page Title
     * @param driver
     * @param title
     * @param timeout
     */
    public static void checkTitle(WebDriver driver, String title, int timeout){
        new WebDriverWait(driver,timeout).until(ExpectedConditions.titleIs(title));
        //      Assert.assertTrue( driver.getTitle().contains(title));
        System.out.println( driver.getTitle());
    }
    /**
     * Login into vytrack application
     *
     * @param driver
     * @param username
     * @param password
     */
    public static void login(WebDriver driver, String username, String password) {
        driver.findElement(By.id(usernamelocator)).sendKeys(username);
        //Keys.ENTER means click enter after entering password
        //in this way, we don't need to click login button
        driver.findElement(By.id(passwordLocator)).sendKeys(password, Keys.ENTER);
        waitPlease(3);
    }
    /**
     * This method will navigate user to the specific module in vytrack application.
     * For example: if tab is equals to Activities, and module equals to Calls,
     * Then method will navigate user to this page: http://qa2.vytrack.com/call/
     *
     * @param driver
     * @param tab
     * @param module
     */
    public static void navigateToModule(WebDriver driver, String tab, String module) {
        String tabLocator = "//span[contains(text(),'" + tab + "') and contains(@class, 'title title-level-1')]";
        String moduleLocator = "//span[contains(text(),'" + module + "') and contains(@class, 'title title-level-2')]";
//        driver.findElement(By.xpath(tabLocator)).click();
        clickWithWait(driver, By.xpath(tabLocator), 5);
        waitPlease(1);
        driver.findElement(By.xpath(moduleLocator)).click();
//        SeleniumUtils.clickWithWait(driver, By.xpath(moduleLocator), 5);
        waitPlease(2);
    }
    public static void logout(WebDriver driver) {
        WebElement nameOnTopRight = driver.findElement(By.className("dropdown-toggle"));
        waitPlease(10);
        nameOnTopRight.click();
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        waitPlease(10);
    }
    /**
     * Waits until loader screen present. If loader screen will not pop up at all,
     * NoSuchElementException will be handled  bu try/catch block
     * Thus, we can continue in any case.
     *
     * @param driver
     */
    public static void waitUntilLoaderScreenDisappear(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Long.valueOf(ConfigurationReader.getProperty("explicitwait")));
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(loaderMaskLocator))));
        } catch (Exception e) {
            System.out.println(e + " :: Loader mask doesn't present.");
        }
    }
    /*
     * switches to new window by the exact title
     */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }
    /**
     * Moves the mouse to given element
     *
     * @param element on which to hover
     */
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }
    /**
     * return a list of string from a list of elements
     * text
     *
     * @param list of webelements
     * @return
     */
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }
    /**
     * Extracts text from list of elements matching the provided locator into new List<String>
     *
     * @param locator
     * @return list of strings
     */
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }
    /**
     * Performs a pause
     *
     * @param seconds
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Waits for the provided element to be visible on the page
     *
     * @param element
     * @param timeToWaitInSec
     * @return
     */
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeToWaitInSec);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * Waits for element matching the locator to be visible on the page
     *
     * @param locator
     * @param timeout
     * @return
     */
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    /**
     * Waits for provided element to be clickable
     *
     * @param element
     * @param timeout
     * @return
     */
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Waits for element matching the locator to be clickable
     *
     * @param locator
     * @param timeout
     * @return
     */
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeout);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    /**
     * waits for backgrounds processes on the browser to complete
     *
     * @param timeOutInSeconds
     */
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }
    /**
     * Verifies whether the element matching the provided locator is displayed on page
     *
     * @param by
     * @throws AssertionError if the element matching the provided locator is not found or not displayed
     */
    public static void verifyElementDisplayed(By by) {
        try {
            assertTrue("Element not visible: " + by, Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);
        }
    }
    /**
     * Verifies whether the element matching the provided locator is NOT displayed on page
     *
     * @param by
     * @throws AssertionError the element matching the provided locator is displayed
     */
    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse(Driver.getDriver().findElement(by).isDisplayed(), "Element should not be visible: " + by);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    /**
     * Verifies whether the element is displayed on page
     *
     * @param element
     * @throws AssertionError if the element is not found or not displayed
     */
    public static void verifyElementDisplayed(WebElement element) {
        try {
            assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);
        }
    }
    /**
     * Waits for element to be not stale
     *
     * @param element
     */
    public void waitForStaleElement(WebElement element) {
        int y = 0;
        while (y <= 15) {
            if (y == 1)
                try {
                    element.isDisplayed();
                    break;
                } catch (StaleElementReferenceException st) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (WebDriverException we) {
                    y++;
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }
    /**
     * Clicks on an element using JavaScript
     *
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }
    /**
     * Scrolls down to an element using JavaScript
     *
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }
    /**
     * Changes the HTML attribute of a Web Element to the given value using JavaScript
     *
     * @param element
     * @param attributeName
     * @param attributeValue
     */
    public static void setAttribute(WebElement element, String attributeName, String attributeValue) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attributeName, attributeValue);
    }
    /**
     * Highlighs an element by changing its background and border color
     * @param element
     */
    public static void highlight(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        waitFor(1);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
    /**
     * Checks or unchecks given checkbox
     *
     * @param element
     * @param check
     */
    public static void selectCheckBox(WebElement element, boolean check) {
        if (check) {
            if (!element.isSelected()) {
                element.click();
            }
        } else {
            if (element.isSelected()) {
                element.click();
            }
        }
    }
    /**
     * attempts to click on provided element until given time runs out
     *
     * @param element
     * @param timeout
     */
    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }
    /**
     * executes the given JavaScript command on given web element
     *
     * @param element
     */
    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command, element);
    }
    /**
     * executes the given JavaScript command on given web element
     *
     * @param command
     */
    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command);
    }




    public static String getPageSubTitle(){
        //ant time we are verifying page name, or page subtitle, loader mask appears
        waitUntilLoaderScreenDisappear(Driver.getDriver());
        return Driver.getDriver().findElement(By.cssSelector(pageSubTitleLocator)).getText();
    }


    /**
     * This method will navigate user to the specific module in BriteERP application.
     * @param driver
     * @param tab
     *
     */
    public static void navigateToModule(WebDriver driver, String tab) {
     waitForPageToLoad(20);
        String tabLocator = "//li/a/span[@class='oe_menu_text'][contains(text(), '"+tab+"')][1]";
        //        driver.findElement(By.xpath(tabLocator)).click();
        clickWithWait(driver, By.xpath(tabLocator), 5);
        waitPlease(5);

    }



}