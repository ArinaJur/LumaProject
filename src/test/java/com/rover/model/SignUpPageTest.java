package com.rover.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SignUpPageTest extends BaseTest {

    @BeforeMethod
    public void beforeClass() {
        mainPage = new MainPage(getDriver());
    }

    private MainPage mainPage;
    private static final Logger logger = LogManager.getLogger(SignUpPageTest.class);

    private void logOut() {
        String expectedMessage = mainPage.clickLogoutAccount().signOutMessage();
        Assert.assertEquals(expectedMessage, "You are signed out");
    }

    @Test
    void testSignInUser() throws InterruptedException {

        final String firstName = "Jack";
        final String lastName = "Sparrow";
        final String password = "Password123!";
        final String email = getEmail(5);
        final String expectedText = "Thank you for registering with Main Website Store.";
        mainPage.openPage();

        Thread.sleep(5000);
        logger.error(mainPage.getDriver().getTitle());
        mainPage.clickCreateAnAccountButton()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword(password)
                .enterConfirmPassword(password)
                .clickCreateAccountButton();
        logger.error(mainPage.getDriver().getTitle());
        Thread.sleep(5000);
        logger.error(mainPage.getDriver().getTitle());
        try {
            String actualUser = mainPage.getContactInformation();
            Assert.assertTrue(actualUser.contains(firstName), "Text does not contain first name: " + firstName);
            Assert.assertTrue(actualUser.contains(lastName), "Text does not contain last name: " + lastName);
            Assert.assertTrue(actualUser.contains(email), "Text does not contain email: " + email);
            String confirmation = mainPage.confirmMessage();
            Assert.assertEquals(confirmation, expectedText, "Text does not match expected");

            if (mainPage.clickShevron().isLoggedIn()) {
                logOut();
            }
        } catch (Exception e){
            logger.error(mainPage.getDriver().getCurrentUrl());
            logger.error(mainPage.getDriver().getTitle());
            logger.error(e.getMessage());
        }
    }

    private String getEmail(int length) {
        return String.format("jack.sparrow%d@%s%d.com",
                generateRandomInt(),
                generateRandomString(length),
                generateRandomInt());
    }

    private int generateRandomInt() {
        return (int) (Math.random() * 1000);
    }

    private String generateRandomString(final int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        return IntStream.range(0, length)
                .map(i -> new Random().nextInt(CHARACTERS.length()))
                .mapToObj(index -> String.valueOf(CHARACTERS.charAt(index)))
                .collect(Collectors.joining());
    }
}
