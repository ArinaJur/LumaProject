import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LumaIkTest extends BaseTest {

    @Test
    public void testNavigationBar(){
        List<String> expectedListOfNavBar = List.of("What's New", "Women", "Men", "Gear", "Training","Sale");

        getDriver().get("https://magento.softwaretestingboard.com/");
        List<WebElement> listOfNavBar = getDriver().findElements(By.cssSelector(".nav-sections .navigation >ul>li>a"));
        List<String> namesOfNavbar = new ArrayList<>();
        for(WebElement text : listOfNavBar) {
            namesOfNavbar.add(text.getText());
        }

        Assert.assertEquals(namesOfNavbar, expectedListOfNavBar);
    }

    @Test
    public void testNavigationBarPW(){
        String [] expectedListOfNavBar = {"What's New", "Women", "Men", "Gear", "Training", " Sale"} ;

        getPage().navigate("https://magento.softwaretestingboard.com/");
        Locator navBar = getPage().locator(".nav-sections .navigation >ul>li>a");

        assertThat(navBar).hasCount(6);
        assertThat(navBar).containsText(expectedListOfNavBar);
    }

    @Test
    public void testSingInPW() throws InterruptedException {
        getPage().navigate("https://magento.softwaretestingboard.com/");


        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("\n" +
                "Sign In ")).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("tester1234@gmail.com");
        getPage().getByLabel("Password").click();
        getPage().getByLabel("Password").fill("tester1234!");
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        getPage().waitForLoadState();
        Locator element = getPage().getByRole(AriaRole.BANNER).getByText("Welcome, Tester Tester!");
        String text = element.innerText().substring(0,23);
//        String text = getPage().locator(".page-header ul[class='header links']>li>span.logged-in").innerText().substring(0,23);
        Assert.assertEquals(text, "Welcome, Tester Tester!");
    }

}
