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

public class NOVerifyDescendingSortByPriceWomenTopsPageTest extends PWLocator {

    private void openWomenTopsPageSelenium() {
        getDriver().get(TestData.BASE_URL);
        getDriver().findElement(By.linkText("Women")).click();
        getDriver().findElement(By.linkText("Tops")).click();
    }

    private void openWomenTopsPagePW() {
        navigate(TestData.BASE_URL);
        textExact("Women").click();
        link("Tops").click();
    }

    private double getMax(List<Double> list) {
        double max = Double.MIN_VALUE;
        for (Double number : list) {
            max = Math.max(max, number);
        }

        return max;
    }

    private boolean isDescending(List<Double> list) {
        Double previousNumber = list.get(0);

        for (Double number : list) {
            if (number > previousNumber) {

                return false;
            } else {
                previousNumber = number;
            }
        }

        return true;
    }

    @Test
    public void testVerifyDescendingSortByPriceWomenTopsPageNOSelenium() {
        openWomenTopsPageSelenium();

        getDriver().findElement(By.id("sorter")).click();
        getDriver().findElement(By.xpath("//option[@value='price']")).click();
        getDriver().findElement((By.linkText("Set Descending Direction"))).click();

        List<WebElement> productsElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']"));

        List<WebElement> pricesElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']//span[@class='price']"));
        List<Double> prices = new ArrayList<>();
        for (WebElement price : pricesElements) {
            String temp = price.getText().substring(1);

            Double finalPrice = Double.parseDouble(temp);

            prices.add(finalPrice);
        }

        Assert.assertTrue(prices.size() > 1, "Not enough products to compare prices");
        Assert.assertEquals(prices.size(), productsElements.size());
        Assert.assertTrue(
                prices.get(0) >= prices.get(prices.size() - 1), "The products displayed NOT in descending order of price.");
        Assert.assertFalse(
                prices.get(0) < prices.get(prices.size() - 1), "The price of first product is LESS than the price of last product.");

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).getText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice);

        boolean actualIsDescendingPriceSort = isDescending(prices);

        Assert.assertTrue(actualIsDescendingPriceSort, "Subsequent products do NOT follow a descending order.");
    }

    @Test
    public void testVerifyDescendingSortByPriceWomenTopsPageNOPlaywright() {
        openWomenTopsPagePW();

        label("Sort By").selectOption("price");
        link("Set Descending Direction").click();

        List<Locator> productsElements = xpath("//li[@class='item product product-item']").all();

        List<Locator> pricesElements = xpath("//li[@class='item product product-item']//span[@class='price']").all();
        List<Double> prices = new ArrayList<>();
        for (Locator price : pricesElements) {
            String temp = price.innerText().substring(1);

            Double finalPrice = Double.parseDouble(temp);

            prices.add(finalPrice);
        }

        Assert.assertTrue(prices.size() > 1, "Not enough products to compare prices");

        Assert.assertEquals(prices.size(), productsElements.size());
        assertThat(xpath("//li[@class='item product product-item']")).hasCount(prices.size());

        Assert.assertTrue(
                prices.get(0) >= prices.get(prices.size() - 1), "The products displayed NOT in descending order of price.");
        Assert.assertFalse(
                prices.get(0) < prices.get(prices.size() - 1), "The price of first product is LESS than the price of last product.");

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).innerText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice);

        boolean actualIsDescendingPriceSort = isDescending(prices);

        Assert.assertTrue(actualIsDescendingPriceSort, "Subsequent products do NOT follow a descending order.");
    }
}
