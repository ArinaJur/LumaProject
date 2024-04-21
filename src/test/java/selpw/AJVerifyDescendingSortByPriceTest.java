package selpw;

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

public class AJVerifyDescendingSortByPriceTest extends BaseTest {

    //Test Objective:
    //To verify that products are displayed in descending order of price when the 'Sort By' option is set to descending order.
    //
    //Test Data:
    //Sample products with various prices.
    //
    //Test Environment:
    //Catalog page of the application.
    //Selenium - Chrome Driver
    //Playwright - Chromium Driver
    //
    //Preconditions:
    //The user is on the catalog Men > Tops page as a guest.
    //
    //Test Steps:
    //Open the 'Sort By' dropdown menu.
    //Select the 'Price' option from the dropdown menu.
    //Choose the descending order option (arrow down).
    //Collect the displayed prices of the products.
    //
    //Expected Result:
    //The products should be displayed in descending order of price.
    //The first product displayed should have the highest price, and subsequent products should follow in descending order.

    private static final String BASE_URL = "https://magento.softwaretestingboard.com";

    private void openMenTopsPageSelenium() {
        getDriver().get(BASE_URL);
        getDriver().findElement(By.linkText("Men")).click();
        getDriver().findElement(By.linkText("Tops")).click();
    }

    private Locator textExact(String text) {
        return getPage().getByText(text, new Page.GetByTextOptions().setExact(true));
    }

    private Locator link(String text) {
        return getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(text));
    }

    private Locator css(String css) {
        return getPage().locator(css);
    }

    private Locator xpath(String xpath) {
        return getPage().locator(xpath);
    }

    private void navigate(String url) {
        getPage().navigate(url);
    }

    private Locator label(String label) {
        return getPage().getByLabel(label);
    }

    private Locator title(String title) {
        return getPage().getByTitle(title);
    }


    private void openMenTopsPagePW() {
        navigate(BASE_URL);
        textExact("Men").click();
        link("Tops").click();
    }

    private double getMax(List<Double> list) {
        double max = Double.MIN_VALUE;
        for(Double number : list) {
            max = Math.max(max, number);
        }

        return max;
    }

    private boolean isDescending(List<Double> list) {
        Double previousNumber = list.get(0);

        for(Double number : list) {
            if (number > previousNumber) {

                return false;
            } else {
                previousNumber = number;
            }
        }

        return true;
    }

    @Test
    public void testDescendingSortByPriceAJSelenium() {
        openMenTopsPageSelenium();

        getDriver().findElement(By.id("sorter")).click();
        getDriver().findElement(By.xpath("//option[@value='price']")).click();
        getDriver().findElement(By.linkText("Set Descending Direction")).click();

        List<WebElement> productsElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']"));

        List<WebElement> pricesElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']//span[@class='price']"));
        List<Double> prices = new ArrayList<>();
        for(WebElement price : pricesElements) {
            prices.add(
                    Double.parseDouble(
                            price.getText().substring(1)
                    )
            );
        }

        Assert.assertTrue(prices.size() > 1, "Not enough products to compare prices");
        Assert.assertEquals(prices.size(), productsElements.size());
        Assert.assertTrue(prices.get(0) >= prices.get(prices.size() - 1), "The products displayed NOT in descending order of price");
        Assert.assertFalse(prices.get(0) < prices.get(prices.size() - 1), "The price of first product is LESS than the price of last product");

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).getText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice);

        boolean actualIsDescendingPriceSort = isDescending(prices);

        Assert.assertTrue(actualIsDescendingPriceSort, "Subsequent products do NOT follow a descending order.");
    }

    @Test()
    public void testDescendingSortByPriceAJPW() throws InterruptedException {
        openMenTopsPagePW();

        label("Sort By").selectOption("price");

        link("Set Descending Direction").click();

        List<Locator> productsElements = xpath("//li[@class='item product product-item']").all();

        List<Locator> pricesElements = xpath("//li[@class='item product product-item']//span[@class='price']").all();
        List<Double> prices = new ArrayList<>();
        for(Locator price : pricesElements) {
            prices.add(
                    Double.parseDouble(
                            price.innerText().substring(1)
                    )
            );
        }

        System.out.println(prices);

        Assert.assertTrue(prices.size() > 1, "There are not enough products to compare prices.");

        Assert.assertEquals(prices.size(), productsElements.size());
        assertThat(xpath("//li[@class='item product product-item']")).hasCount(prices.size());

        Assert.assertTrue(
                prices.get(0) >= prices.get(prices.size() - 1),
                "The products displayed are NOT in descending order of price."
        );
        Assert.assertFalse(
                prices.get(0) < prices.get(prices.size() - 1),
                "The price of the first product is LESS than the price of the last product."
        );

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).innerText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice);

        boolean actualIsDescendingPriceSort = isDescending(prices);

        Assert.assertTrue(actualIsDescendingPriceSort, "Subsequent products do NOT follow a descending order.");
    }

}
