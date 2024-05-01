package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class AINavigationToMenPageTest extends BaseTest {

    @Test
    public void testNavigationToMenPage() {
        String expectedResult = "Men";
        List<String> expectedMenCategories = List.of("Tops", "Bottoms");

        openBaseUrlSelenium();
        getDriver().findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/men.html']"))
                .click();

        String actualPageHeading = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(actualPageHeading, expectedResult);
        Assert.assertEquals(getDriver().getTitle(), expectedResult);
        String menCategoriesCountItems = getDriver().findElement(By.xpath("//ol[@class='items']")).getText();
        System.out.println(menCategoriesCountItems);

        List<WebElement> menLinks = getDriver().findElements(By.xpath("//div[@class='sidebar sidebar-main']//ol/li/a"));
        List<String> menCategoriesLinksNames = new ArrayList<>();
        for(WebElement menLink : menLinks) {
            menCategoriesLinksNames.add(menLink.getText());
        }
        System.out.println(menCategoriesLinksNames);
        Assert.assertEquals(menCategoriesLinksNames, expectedMenCategories);
    }
}
