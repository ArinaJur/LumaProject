package com.rover.model;

import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SignUpPageTest extends BaseTest {
    private static final String url = "https://magento.softwaretestingboard.com/customer/account/create/";

    @Test
    void  testSignIn(){
        final String firstName = "Jack";
        final String lastName = "Sparrow";
        final String email = getEmail(5);
        final String expectedUrl = "https://magento.softwaretestingboard.com/customer/account/createpost/";

        SignUpPage signUpPage = new SignUpPage(getDriver());
        getDriver().get(url);

        signUpPage.enterFirstName(firstName);
        signUpPage.enterLastName(lastName);
        signUpPage.enterEmail(email);
        signUpPage.enterPassword("Password123!");
        signUpPage.enterConfirmPassword("Password123!");
        signUpPage.clickCreateAccountButton();

        Assert.assertEquals(getDriver().getCurrentUrl(), expectedUrl);
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
