package com.rover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    private static final String URL = "https://magento.softwaretestingboard.com/customer/account/login/referer/aHR0cHM6Ly9tYWdlbnRvLnNvZnR3YXJldGVzdGluZ2JvYXJkLmNvbS9jdXN0b21lci9hY2NvdW50L2NyZWF0ZS8%2C/";

    @FindBy(css = "#email")
    public WebElement inputEmail;

    @FindBy(css = "input[name='login[password]']")
    public WebElement inputRequired;

    @FindBy(css = "button[class$='primary']")
    public WebElement buttonSend;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        driver.get(URL);
    }

    void clickButton() {
        buttonSend.click();
    }
}