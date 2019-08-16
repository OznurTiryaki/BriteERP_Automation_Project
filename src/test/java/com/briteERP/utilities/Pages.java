package com.briteERP.utilities;


import com.briteERP.pages.crmPage.CRMPage;
import com.briteERP.pages.homePage.HomePage;
import com.briteERP.pages.loginPage.LoginPage;


public class Pages {

    private LoginPage loginPage;
    private CRMPage crmPage;
    private HomePage homePage;


    public LoginPage loginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public CRMPage crmPage() {
        if (crmPage == null) {
            crmPage = new CRMPage();
        }
        return crmPage;
    }


    public HomePage homePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }
}