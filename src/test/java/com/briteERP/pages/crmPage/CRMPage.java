package com.briteERP.pages.crmPage;

import com.briteERP.pages.loginPage.LoginPage;
import com.briteERP.utilities.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CRMPage extends BasePage{

    public CRMPage() {

        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "button[class='btn btn-icon fa fa-lg fa-table o_cp_switch_pivot active']  ")
    public WebElement pivotButtonLocator;

    @FindBy(css = ".o_pivot_header_cell_closed:nth-of-type(1)")
    public WebElement totalIconLocator;

    @FindBy(css = "table tbody tr:nth-of-type(4) td:nth-of-type(2)")
    public WebElement table2Row2Clolumn;

    @FindBy(css = "table tbody tr:nth-of-type(2) td:nth-of-type(1)")
    public WebElement newButton;

    @FindBy(css = "li[data-field='name']>a")
    public WebElement DropDownOpportunityLocator;

    @FindBy(css = "button[data-view-type='list']")
    public WebElement listButtonLocator;

    @FindBy(css = "table tbody tr:nth-of-type(1) td:nth-of-type(9)")
    public WebElement listMatchingTableLocator;

    @FindBy(css = "table tbody tr:nth-of-type(1) td:nth-of-type(2)")
    public WebElement totalRevenue;

    public void getToOpportunity() {




// 2.click CRM button

        navigateToModule(Driver.getDriver(), "CRM");
        //step 1 , pages'e ulasmak icin bu sinifi Test Base ile extend ettik,
        //test base sinifi ile pagese ve onun icindekiilere ulabiliriz
        //oradan homePage() ile homePage'e ulasmak icin bir obje olusturduk
        //ama navigate to module onun icinde degil
        //homePage'den de Base Page i extend ettigimiz icin navigate to module'a ulastik

// 3.click pivot icon

        BriteUtils.clickOn(pivotButtonLocator, 20);

//4. click new and choose opportunity

        BriteUtils.clickOn( newButton, 10);

// 5.choose second row and second column /on list board it is first one

        BriteUtils.waitFor(3);

        BriteUtils.clickOn(DropDownOpportunityLocator, 10);

    }


    public int sumRevenue() {

        int sum = 0;

        for (int i = 3; i <= 5; i++) {

            WebElement element = Driver.getDriver().findElement(By.cssSelector("table tbody tr:nth-of-type(" + i + ") td:nth-of-type(2)"));

            int revenue = Integer.parseInt(element.getText().replaceAll("[^\\d]", ""));

            sum += revenue;


        }

        return sum;


    }}

