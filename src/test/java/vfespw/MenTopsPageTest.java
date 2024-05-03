package vfespw;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import vfespw.runner.TestData;
import vfespw.runner.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MenTopsPageTest extends BaseLocators {
    @Test
    public void test_DescendingSortByPrice() {
        getPage().navigate(TestData.BASE_URL);
        getPage().getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName(" Men")).click();
        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Tops")).click();

        getPage().getByLabel("Sort By").selectOption("price");
        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Set Descending Direction")).click();

        Locator productElements = locator("li.item.product.product-item");

        List<Locator> priceElements = locator("div.price-box.price-final_price > span > span > span > span ").all();

        List<Double> prices = new ArrayList<>();

        for (Locator price : priceElements ) {
            prices.add(
                    Double.parseDouble(
                            price.innerText().substring(1)
                    )
            );
        }
        double firstPrice = Double.parseDouble(String.valueOf(prices.get(0)));
        double lastPrice = Double.parseDouble(String.valueOf(prices.get(prices.size() - 1)));

        Assert.assertTrue(prices.size() > 1, "If FAIL: Not enough product to compare prices");
        assertThat(productElements).hasCount(prices.size());

        Assert.assertTrue(firstPrice >= lastPrice,
                "If FAIL: The product displayed Not in descending order of price");

        Assert.assertFalse((prices.get(0) < prices.get(prices.size() - 1)),
                "If FAIL: The price of the first product by descending order list is LESS than the price of last product");

        double maxPrice = TestUtils.getMax(prices);
        Double firstCardPrice = Double.parseDouble(priceElements.get(0).innerText().substring(1));

        Assert.assertEquals(maxPrice, firstCardPrice,
                "If FAIL: The price of the first product by descending order list is NOT max price");

        boolean actualDescendingPriceSort = TestUtils.isDescending(prices);

        Assert.assertTrue(actualDescendingPriceSort, "If FAIL: Subsequent products do NOT follow order");
    }
}
