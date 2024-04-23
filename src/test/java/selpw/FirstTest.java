package selpw;

import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FirstTest extends BaseTest {

    @Test
    public void testSelenium() {
        getDriver().get("https://magento.softwaretestingboard.com");

        Assert.assertEquals(getDriver().getCurrentUrl(), "https://magento.softwaretestingboard.com/");
        Assert.assertEquals(getDriver().getTitle(), "Home Page");
    }

    @Test
    public void testPW() {
        openBaseUrlPW();

        assertThat(getPage()).hasURL("https://magento.softwaretestingboard.com/");
        assertThat(getPage()).hasTitle("Home Page");
    }
}
