package com.rover.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SignUpPageTest extends BaseTest {

    private static final Logger logger = LogManager.getLogger(SignUpPageTest.class);
    private static final String url = "https://magento.softwaretestingboard.com/customer/account/create/";

    @BeforeMethod
    public void beforeClass() {
        this.getDriver().manage().window().maximize();
        this.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        try {
            logger.error(getDriver().getCurrentUrl());
            getDriver().findElement(By.xpath("//div[@class='panel header']//ul[@class='header links']//button")).click();
        } catch (Exception e){
            logger.error(e.getMessage());
        }

        if (isLoggedIn()) {
            logOut();
            logger.error(getDriver().getCurrentUrl());
        }
    }

    private void logOut() {
        getDriver().findElement(By.xpath("//li[@class='customer-welcome active']//a[contains(@href, 'customer/account/logout/')]")).click();
        WebElement signOutMessage = getDriver().findElement(By.xpath("//span[@class='base' and @data-ui-id='page-title-wrapper']"));
        Assert.assertEquals(signOutMessage.getText(), "You are signed out");
    }

    private boolean isLoggedIn() {
        return !getDriver().findElements(By.xpath("//li[@class='customer-welcome active']//a[contains(@href, 'customer/account/logout/')]")).isEmpty();
    }


    @Test
    void  testSignInUser() throws InterruptedException {
        final String firstName = "Jack";
        final String lastName = "Sparrow";
        final String email = getEmail(5);
        getDriver().get(url);
        logger.error(getDriver().getTitle());
        new SignUpPage(getDriver())
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .enterPassword("Password123!")
                .enterConfirmPassword("Password123!")
                .clickCreateAccountButton(getDriver());

        Thread.sleep(10000);

        logger.error(getDriver().getTitle());
        try {
            String actualUser = getDriver().findElement(By.xpath("//div[@class='box-content']/p")).getText();
            Assert.assertTrue(actualUser.contains(firstName), "Text does not contain first name: " + firstName);
            Assert.assertTrue(actualUser.contains(lastName), "Text does not contain last name: " + lastName);
            Assert.assertTrue(actualUser.contains(email), "Text does not contain email: " + email);

            String confirmation = getDriver().findElement(By.xpath("//div[@role='alert']/div/div")).getText();
            String expectedText = "Thank you for registering with Main Website Store.";
            Assert.assertEquals(confirmation.trim(), expectedText, "Text does not match expected");
        } catch (Exception e){
            logger.error(getDriver().getCurrentUrl());
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
