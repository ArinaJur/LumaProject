import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignInTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(SignInTest.class);

    @Test
    public void testSingInPW() throws InterruptedException {
        getPage().navigate("https://magento.softwaretestingboard.com/");

        Thread.sleep(5000);
        logger.info(getPage().url());

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In ")).click();

        Thread.sleep(5000);
        logger.info(getPage().url());

        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("Tester3@gmail.com");
        getPage().getByLabel("Password").fill("123456789vk_");
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

        Thread.sleep(5000);
        logger.info(getPage().url());

        Locator welcomeElement = getPage().locator(".panel.header .logged-in");
        String actual = welcomeElement.innerText();

        logger.info(actual);

        Assert.assertEquals(actual, "Welcome, tester3 tester3!");
        Assert.assertTrue(welcomeElement.isVisible());
    }

}

