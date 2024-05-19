package selenium.pages;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import runner.BaseTest;

import static vfespw.runner.TestData.EMAIL;
import static vfespw.runner.TestData.PASSWORD;
import static vfespw.runner.TestData.MEN;

public class CartTest extends BaseTest {

    @Test
    @Ignore
    public void testCartPricing() {
        final int productQuantity = 3;
        CartPage cartPage = new LandingPage(getDriver())
                .openLandingPage()
                .clickSignIn()
                .login(EMAIL, PASSWORD)
                .getNavBar()
                .selectCategory("Women", "Bottoms", "Pants")
                .addProductsToCart(productQuantity)
                .getNavBar().goToCart();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(cartPage.isProductSubtotalValid());
        softAssert.assertTrue(cartPage.isCartSubtotalValid());
        softAssert.assertTrue(cartPage.isTotalValid());
        softAssert.assertAll();
    }

    @Test
    @Ignore
    public void testCartQuantity() {
        final int addedQuantity = 3;
        CartPage cartPage = new LandingPage(getDriver())
                .openLandingPage()
                .getNavBar()
                .goToCart();
        int initialQuantity = cartPage.getProductQuantity();
        StorePage storePage = cartPage.getNavBar(getDriver()).selectCategory(MEN);
        storePage.addProductsToCart(addedQuantity);
        cartPage = storePage.getNavBar().goToCart();
        Assert.assertTrue(cartPage.isProductQuantityValid(initialQuantity, addedQuantity));
    }
}
