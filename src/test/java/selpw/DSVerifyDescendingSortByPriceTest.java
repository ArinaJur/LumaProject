package selpw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.ArrayList;
import java.util.List;

//Just to commit changes
public class DSVerifyDescendingSortByPriceTest extends BaseTest {
    public static final String BASE_URL = "https://magento.softwaretestingboard.com";

    private void openMenTopsPage() {
        getDriver().findElement(By.linkText("Men")).click();
        getDriver().findElement(By.linkText("Tops")).click();
    }

    private Double getMax(List<Double> list) {
        Double max = Double.MIN_VALUE;
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
    public void testDescendingSortByPriceDSSelenium() {

    getDriver().get(BASE_URL);
    //getDriver().findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/men.html']")).click();

        openMenTopsPage();

        getDriver().findElement(By.id("sorter")).click();
        getDriver().findElement(By.xpath("//option[@value='price']")).click();
        getDriver().findElement(By.linkText("Set Descending Direction")).click();

        List<WebElement> productsElement = getDriver().findElements(By.xpath("//li[@class='item product product-item']"));
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
        Assert.assertEquals(prices.size(), productsElement.size());
        Assert.assertTrue(prices.get(0) >= prices.get(prices.size() - 1), "The products NOT in descending order of price");
        Assert.assertFalse(prices.get(0) < prices.get(prices.size() - 1), "The price of first product is LESS than the price of last product");

        double maxPrice = getMax(prices);
        Double firstCardPrice = Double.parseDouble(pricesElements.get(0).getText().substring(1));

        boolean actualIsDescendingSort = isDescending(prices);
        Assert.assertTrue(actualIsDescendingSort, "Subsequence products do NOT follow a descending order.");
    }

    @Test
    public void testDescendingSortByPriceDSPW() {

    }
}
