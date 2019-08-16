package com.briteERP.tests.components.crmTests;

import com.briteERP.pages.crmPage.CRMPage;
import com.briteERP.utilities.BriteUtils;
import com.briteERP.utilities.ConfigurationReader;
import com.briteERP.utilities.Pages;
import com.briteERP.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreatePivotTest extends TestBase {





//    Verify that on the pivot table, the total expected revenue should be the sum of all opportunities’ expected revenue.
    // 1.log in
    // 2.click CRM button
    // 3.click pivot icon
    // 4. click new and choose opportunity
    // 5.choose second row and second column /on list board it is first one
    //verify they have same revenue


    @Test
    public void Test1() {

        extentLogger =  report.createTest("Verify that second opportunity "+
                "Expected Revenue value on the Pivot board matches revenue column value on the list board.");

// 1.log in
// 2.click CRM button
// 3.click pivot icon
//4. click new and choose opportunity


        String username = ConfigurationReader.getProperty("crmUsername");
        String password = ConfigurationReader.getProperty("crmPassword");
        pages.loginPage().login(username, password);
        pages.crmPage().getToOpportunity();  //pages objesi ile pages class' a ulasiyorum
                                            //oradan da crmPage() methodunu cagiriyorum



// 5.choose second row and second column /on list board it is first one
        String pivow =  pages.crmPage().table2Row2Clolumn.getText();

        System.out.println(pivow);

//6.verify they have same revenue

        BriteUtils.waitForClickablility( pages.crmPage().listButtonLocator, 10).click();

        String list =  pages.crmPage().listMatchingTableLocator.getText();

        System.out.println(list);

        Assert.assertTrue(pivow.equals(list));

        extentLogger.pass("Verified they have same revenue");
    }

// Verify that on the pivot table, the total expected revenue should be the sum of all opportunities’ expected revenue.

    //1. PreCondition- each CRM manager user should create at least 3 opportunities on the CRM module.
    // (create 2 opportunity just in case)
    // 2.Pre-condition two: on Pivot table expand total and select opportunity from the dropdown.

    @Test
    public void Test2() {

        extentLogger = report.createTest("Verify that on the pivot table, " +
                "the total expected revenue should be the sum of all opportunities’ expected revenue.");

       pages.crmPage().getToOpportunity();

    int sum= pages.crmPage().sumRevenue();

    int totalRevenue=Integer.parseInt(pages.crmPage().totalRevenue.getText().replaceAll("[^\\d]", ""));

       Assert.assertTrue(sum==totalRevenue);

       extentLogger.pass("Verify that on the pivot table, the total expected revenue should be the sum of all opportunities’ expected revenue");
}
}
