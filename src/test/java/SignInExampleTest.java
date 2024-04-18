import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SignInExampleTest extends BaseTest {

    @Ignore //Ignored for CI
    @Test
    public void testSingInPW() {
        getPage().navigate("https://magento.softwaretestingboard.com");

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In ")).click();

        assertThat(getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true))).isVisible();

        //getPage().pause();  //for debugging
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("Tester3@gmail.com");
        getPage().getByLabel("Password").fill("123456789vk_");

        assertThat(getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In"))).isEnabled();

        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

        Locator welcomeElement = getPage().locator(".panel.header .logged-in");
        String actualText = welcomeElement.innerText();

        Assert.assertEquals(actualText, "Welcome, tester3 tester3!");
        Assert.assertTrue(welcomeElement.isVisible());
    }

    @Ignore //Ignored for CI
    @Test
    public void testSignInSelenium() {
        getDriver().get("https://magento.softwaretestingboard.com");
        getDriver().findElement(By.linkText("Sign In")).click();

        getDriver().findElement(By.id("email")).sendKeys("test+123@test.com");
        getDriver().findElement(By.id("pass")).sendKeys("Tester123");
        getDriver().findElement(By.id("send2")).click();

        WebElement element = getDriver().findElement(By.xpath("//div[@class='panel header']//span[@class='logged-in']"));

        Assert.assertTrue(element.isDisplayed());
    }

}
