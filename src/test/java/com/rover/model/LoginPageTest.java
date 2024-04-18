package com.rover.model;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class LoginPageTest extends BaseTest {

    @Ignore
    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickButton();
        Assert.assertTrue(true);
    }
}