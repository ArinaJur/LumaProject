package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class AINavigateToGearPageTest extends BaseTest {

    @Test
    public void testNavigationToGearPage() {
        final String expectedResult = "Gear";
        List<String> expectedCategories = List.of("Bags", "Fitness Equipment", "Watches");

        openBaseUrlSelenium();
        getDriver()
                .findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/gear.html']"))
                .click();

        String actualHeading = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(actualHeading, expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResult);

        List<WebElement> gearLinks = getDriver().findElements(By.xpath("//div[@class='sidebar sidebar-main']//ol/li/a"));
        List<String> categoriesLinkNames = new ArrayList<String>();
        for (WebElement gearLink : gearLinks) {
            categoriesLinkNames.add(gearLink.getText());
        }
        Assert.assertEquals(categoriesLinkNames, expectedCategories);
    }
}
