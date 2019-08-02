package com.briteERP.pages.crmPages;

import com.briteERP.pages.loginPages.LoginPages;
import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CrmPages {

    public CrmPages(){

        PageFactory.initElements(Driver.getDriver(),this);
    }

    @FindBy(css = "button[data-original-title='Pivot']")
    public WebElement pivotButtonLocator;


    @FindBy(css = ".o_pivot_header_cell_closed:nth-of-type(1)")
    public WebElement totalIconLocator;


    @FindBy(css= "table tbody tr:nth-of-type(4) td:nth-of-type(2)")
    public WebElement table2Row2Clolumn;


    @FindBy(css = "table tbody tr:nth-of-type(2) td:nth-of-type(1)")
    public WebElement newButton;

    @FindBy(css = "li[data-field='name']>a")
    public WebElement DropDownOpportunityLocator;

    @FindBy(css = "button[data-view-type='list']")
    public WebElement listButtonLocator;

    @FindBy(css = "table tbody tr:nth-of-type(1) td:nth-of-type(9)")
    public WebElement listMatchingTableLocator;

    @FindBy(css="table tbody tr:nth-of-type(1) td:nth-of-type(2)")
    public WebElement totalRevenue;

    public void getToOpportunity(){

            LoginPages logIn=new LoginPages();
// 1.log in
            String username = ConfigurationReader.getProperty("crmUsername");
            String password = ConfigurationReader.getProperty("crmPassword");
            logIn.login(username,password);

// 2.click CRM button

            BriteUtils.navigateToModule(Driver.getDriver(), "CRM");

// 3.click pivot icon

            BriteUtils.clickOn(Driver.getDriver(),pivotButtonLocator,10);

//4. click new and choose opportunity

            BriteUtils.clickOn(Driver.getDriver(),newButton,10);

// 5.choose second row and second column /on list board it is first one

            BriteUtils.clickOn(DropDownOpportunityLocator, 10);

    }



}
