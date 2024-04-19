import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
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

        getPage().getByLabel("Consent",new Page.GetByLabelOptions());
        getPage().navigate("https://magento.softwaretestingboard.com/");
        Locator navBar = getPage().locator(".nav-sections .navigation >ul>li>a");

        assertThat(navBar).hasCount(6);
        assertThat(navBar).containsText(expectedListOfNavBar);
    }

}
