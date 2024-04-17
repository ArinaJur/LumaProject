package com.rover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CustomerPage {
    public CustomerPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
