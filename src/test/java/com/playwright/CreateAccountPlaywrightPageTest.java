package com.playwright;

import api.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import playwright.pages.CreateAccountPlaywrightPage;
import runner.BaseTest;

public class CreateAccountPlaywrightPageTest extends BaseTest {
    @BeforeMethod
    public void before() {
        getDriver().quit();
    }

    @Test
    void testSignInUser() {

        User user = new User();

        CreateAccountPlaywrightPage signIn = new CreateAccountPlaywrightPage(getPage());
        signIn.openPage();
        signIn.gotoSign();

        signIn.enterFirstName(user.getFirstname())
                .enterLastName(user.getLastname())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .enterConfirmPassword(user.getPassword())
                .clickCreateAccountButton();

        String actualUser = signIn.getContactInformation();
        Assert.assertTrue(actualUser.contains(user.getFirstname()), "Text does not contain first name: " + user.getFirstname());
        Assert.assertTrue(actualUser.contains(user.getLastname()), "Text does not contain last name: " + user.getLastname());
        Assert.assertTrue(actualUser.contains(user.getEmail()), "Text does not contain email: " + user.getEmail());

        if (signIn.clickShevron().isLoggedIn()) {
            signIn.clickLogoutAccount().verifyLogOut();
            System.out.println(signIn.getSignOutText());
        }
    }
}
