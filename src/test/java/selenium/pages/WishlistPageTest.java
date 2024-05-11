package selenium.pages;

import org.jetbrains.annotations.NotNull;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import runner.BaseTest;

import static vfespw.runner.TestData.EMAIL;
import static vfespw.runner.TestData.PASSWORD;
import static vfespw.runner.TestData.MEN;

public class WishlistPageTest extends BaseTest {

    @BeforeMethod
    public void beforeClass() {
        getPage().close();
    }

    @Test
    public void addRandomProductToWishlistTest() {
        WishlistPage wishlistPage = getLandingPage()
                .getNavBar()
                .selectCategory(MEN)
                .goToProductPage("Random")
                .wishlistProduct();
        Assert.assertTrue(wishlistPage.isMessageDisplayed());
    }

    @Test
    public void addProductToWishlistTest() {
        WishlistPage wishlistPage = getLandingPage()
                .getNavBar()
                .selectCategory("Men", "Tops", "Tees")
                .goToProductPage("Zoltan Gym Tee")
                .wishlistProduct();
        Assert.assertTrue(wishlistPage.isMessageDisplayed());
    }

    @NotNull
    private LandingPage getLandingPage() {
        return new LandingPage(getDriver())
                .openLandingPage()
                .clickSignIn()
                .login(EMAIL, PASSWORD);
    }
}
