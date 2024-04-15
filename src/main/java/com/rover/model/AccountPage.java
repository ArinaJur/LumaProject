package com.rover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AccountPage {

    @FindBy(xpath = "//div[@class='box-content']//p")
    private WebElement accountInfoParagraph;

    public AccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getAccountInfoText() {
        return accountInfoParagraph.getText();
    }

    private void verify(String actual, String expected) {
        Assert.assertEquals(actual, expected);
    }

    public void assertParagraphText(String expectedText) {
        verify(getAccountInfoText(), expectedText);
    }
}
