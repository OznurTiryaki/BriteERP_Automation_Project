package com.briteERP.utilities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


public abstract class BasePage {

    //we don't want to access these variables outside
    private static final Logger logger = LogManager.getLogger();

    /**
     * This method will navigate user to the specific tab in BRiteERP application.
     * @param driver
     * @param tab
     */

    public void navigateToModule(WebDriver driver, String tab) {
        String tabLocator = "//li/a/span[@class='oe_menu_text'][contains(text(), '" + tab + "')][1]";

        try {
            BriteUtils.waitForClickablility(By.xpath(tabLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
            WebElement tabElement = Driver.getDriver().findElement(By.xpath(tabLocator));
            new Actions(Driver.getDriver()).moveToElement(tabElement).pause(200).doubleClick(tabElement).build().perform();
        } catch (Exception e) {
            logger.error("Failed to click on :: " + tab);
            logger.error(e);
            BriteUtils.clickWithWait(By.xpath(tabLocator), Integer.valueOf(ConfigurationReader.getProperty("SHORT_WAIT")));
        }

    }
}
