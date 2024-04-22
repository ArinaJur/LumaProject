package selpw;

import com.microsoft.playwright.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.PWLocator;
import runner.TestData;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static runner.TestData.BASE_URL;

public class AJVerifyDescendingSortByPriceTest extends PWLocator {

    private void openMenTopsPageSelenium() {
        getDriver(BASE_URL).get(BASE_URL);
        getDriver(BASE_URL).findElement(By.linkText("Men")).click();
        getDriver(BASE_URL).findElement(By.linkText("Tops")).click();
    }

    //Wrappers для локкаторов перенесены в класс PWLocator
//    private Locator textExact(String text) {
//        return getPage().getByText(text, new Page.GetByTextOptions().setExact(true));
//    }
//
//    private Locator link(String text) {
//        return getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(text));
//    }
//
//    private Locator css(String css) {
//        return getPage().locator(css);
//    }
//
//    private Locator xpath(String xpath) {
//        return getPage().locator(xpath);
//    }
//
//    private void navigate(String url) {
//        getPage().navigate(url);
//    }
//
//    private Locator label(String label) {
//        return getPage().getByLabel(label);
//    }
//
//    private Locator title(String title) {
//        return getPage().getByTitle(title);
//    }

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

        getDriver(BASE_URL).findElement(By.id("sorter")).click();
        getDriver(BASE_URL).findElement(By.xpath("//option[@value='price']")).click();
        getDriver(BASE_URL).findElement(By.linkText("Set Descending Direction")).click();

        List<WebElement> productsElements = getDriver(BASE_URL).findElements(By.xpath("//li[@class='item product product-item']"));
        List<WebElement> pricesElements = getDriver(BASE_URL).findElements(
                By.xpath("//li[@class='item product product-item']//span[@class='price']")
        );
        List<Double> prices = new ArrayList<>();
        for(WebElement price : pricesElements) {
            prices.add(
                    Double.parseDouble(
                            price.getText().substring(1)
                    )
            );
        }

        Assert.assertTrue(prices.size() > 1, "There aren NOT enough products for price comparison.");
        Assert.assertEquals(prices.size(), productsElements.size());
        Assert.assertTrue(
                prices.get(0) >= prices.get(prices.size() - 1),
                "The products are NOT displayed in descending order of price."
        );
        Assert.assertFalse(
                prices.get(0) < prices.get(prices.size() - 1),
                "The price of the first product is LOWER than the price of the last product."
        );

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).getText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice);

        boolean actualIsDescendingPriceSort = isDescending(prices);

        Assert.assertTrue(
                actualIsDescendingPriceSort,
                "The subsequent products are NOT arranged in descending order."
        );
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

        Assert.assertTrue(prices.size() > 1, "There aren NOT enough products for price comparison.");

        Assert.assertEquals(prices.size(), productsElements.size());
        assertThat(xpath("//li[@class='item product product-item']")).hasCount(prices.size());
        Assert.assertTrue(
                prices.get(0) >= prices.get(prices.size() - 1),
                "The products are NOT displayed in descending order of price."
        );
        Assert.assertFalse(
                prices.get(0) < prices.get(prices.size() - 1),
                "The price of the first product is LOWER than the price of the last product."
        );

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).innerText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice);

        boolean actualIsDescendingPriceSort = isDescending(prices);

        Assert.assertTrue(
                actualIsDescendingPriceSort,
                "The subsequent products are NOT arranged in descending order."
        );
    }
}
