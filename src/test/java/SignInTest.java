import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignInTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SignInTest.class);

    @Ignore
    @Test
    public void testSingInPW() throws InterruptedException {
        getPage().navigate("https://magento.softwaretestingboard.com/");

        Thread.sleep(5000);
        logger.info(getPage().url());

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In ")).click();

        Thread.sleep(5000);
        logger.info(getPage().url());

        assertThat(getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true))).isVisible();

        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("Tester3@gmail.com");
        getPage().getByLabel("Password").fill("123456789vk_");

        assertThat(getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In"))).isEnabled();

        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

        Thread.sleep(5000);
        logger.info(getPage().url());

        Locator welcomeElement = getPage().locator(".panel.header .logged-in");
        String actual = welcomeElement.innerText();

        logger.info(actual);

        Assert.assertEquals(actual, "Welcome, tester3 tester3!");
        Assert.assertTrue(welcomeElement.isVisible());
    }

    @Test
    public void testSignInSelenium() throws InterruptedException {
        Thread.sleep(5000);
        getDriver().get("https://magento.softwaretestingboard.com/customer/account/logout/");

        Thread.sleep(1000);
        logger.info(getDriver().getCurrentUrl());

        getDriver().get("https://magento.softwaretestingboard.com/");
        getDriver().findElement(By.linkText("Sign In")).click();

        Thread.sleep(5000);
        logger.info(getDriver().getCurrentUrl());

        getDriver().findElement(By.id("email")).sendKeys("test+123@test.com");
        getDriver().findElement(By.id("pass")).sendKeys("Tester123");
        getDriver().findElement(By.id("send2")).click();

        Thread.sleep(5000);
        logger.info(getDriver().getCurrentUrl());

        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@class='panel header']//span[@class='logged-in']")).isDisplayed());
    }



}

