package selpw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class AIVerifyDescendingSortByPriceTest extends BaseTest {
    private static final String BASE_URL = "https://magento.softwaretestingboard.com";

    private void gotoMenTopsPage() {
        getDriver().get(BASE_URL);
        getDriver().findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/men.html']")).click();
        getDriver().findElement(By.linkText("Tops")).click();
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
    public void testDescendingSortByPriceAISel() {
        gotoMenTopsPage();
        getDriver().findElement(By.id("sorter")).click();
        getDriver().findElement(By.xpath("//option[@value='price']")).click();
        getDriver().findElement(By.linkText("Set Descending Direction")).click();

        List<WebElement> productsElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']"));

        List<WebElement> pricesElements = getDriver().findElements(By.xpath("//li[@class='item product product-item']//span[@class='price']"));
        List<Double> prices = new ArrayList<>();
        for (WebElement price : pricesElements) {
            String temp = price.getText().substring(1);
            prices.add(Double.parseDouble(temp));
        }

        Assert.assertTrue(prices.size() > 1, "Not enough products to compare prices");
        Assert.assertEquals(prices.size(), productsElements.size());
        Assert.assertTrue(prices.get(0) >= prices.get(prices.size() - 1), "The products displayed NOT in descending order of price");
        Assert.assertFalse(prices.get(0) < prices.get(prices.size() - 1), "The price of the first product is LESS than the price of last product");

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).getText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice);

        boolean actualIsDescendingPriceSort = isDescending(prices);

        Assert.assertTrue(actualIsDescendingPriceSort, "Subsequent products are NOT follow in descending order");
    }
}
