package selpw;

import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static runner.TestData.BASE_URL;

public class FirstTest extends BaseTest {

    @Test
    public void testSelenium() {
        getDriver(BASE_URL).get("https://magento.softwaretestingboard.com");

        Assert.assertEquals(getDriver(BASE_URL).getCurrentUrl(), "https://magento.softwaretestingboard.com/");
        Assert.assertEquals(getDriver(BASE_URL).getTitle(), "Home Page");
    }

    @Test
    public void testPW() {
        getPage().navigate("https://magento.softwaretestingboard.com");

        assertThat(getPage()).hasURL("https://magento.softwaretestingboard.com/");
        assertThat(getPage()).hasTitle("Home Page");
    }
}
