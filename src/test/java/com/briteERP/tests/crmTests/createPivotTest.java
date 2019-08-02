package com.briteERP.tests.crmTests;

import com.briteERP.pages.crmPages.CrmPages;
import com.briteERP.pages.loginPages.LoginPages;
import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Driver;
import com.briteERP.utilities.TestBase;
import com.google.common.base.Verify;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class createPivotTest extends TestBase {

  //  LoginPages logIn = new LoginPages();



//    Verify that on the pivot table, the total expected revenue should be the sum of all opportunities’ expected revenue.
    // 1.log in
    // 2.click CRM button
    // 3.click pivot icon
    // 4. click new and choose opportunity
    // 5.choose second row and second column /on list board it is first one
    //verify they have same revenue


    @Test
    public void Test1() {

        CrmPages crm = new CrmPages();

// 1.log in
// 2.click CRM button
// 3.click pivot icon
//4. click new and choose opportunity


        crm.getToOpportunity();



// 5.choose second row and second column /on list board it is first one
        String pivow = crm.table2Row2Clolumn.getText();

        System.out.println(pivow);

//6.verify they have same revenue

        BriteUtils.waitForClickablility(crm.listButtonLocator, 10).click();

        String list = crm.listMatchingTableLocator.getText();

        System.out.println(list);

        Assert.assertTrue(pivow.equals(list));
    }

// Verify that on the pivot table, the total expected revenue should be the sum of all opportunities’ expected revenue.

    //1. PreCondition- each CRM manager user should create at least 3 opportunities on the CRM module.
    // (create 2 opportunity just in case)
    // 2.Pre-condition two: on Pivot table expand total and select opportunity from the dropdown.

    @Test
    public void Test2() {

        CrmPages crm = new CrmPages();

        crm.getToOpportunity();

        int sum=0;

        for (int i = 3; i <= 5; i++) {

            WebElement element = driver.findElement(By.cssSelector("table tbody tr:nth-of-type(" + i + ") td:nth-of-type(2)"));

            int revenue = Integer.parseInt(element.getText().replaceAll("[^\\d]", ""));

            sum+=revenue;

        }
        System.out.println(sum);

        int totalRevenue=Integer.parseInt(crm.totalRevenue.getText().replaceAll("[^\\d]", ""));

        Assert.assertTrue(sum==totalRevenue);
    }
}
