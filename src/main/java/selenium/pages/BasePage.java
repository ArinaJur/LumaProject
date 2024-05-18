package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class BasePage {

    private final WebDriver driver;
    private final NavComponent nav;
    private final MiniCard miniCard;

    protected static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private static final By CART_ICON = By.cssSelector("a.showcart");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message-success.success.message");

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.nav = new NavComponent(driver);
        this.miniCard = new MiniCard(driver);
        PageFactory.initElements(driver, this);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    public void hover(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void visit(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void assertVisibleOfElement(By locator) {
        WebElement isDisplayed = driver.findElement(locator);
        Assert.assertTrue(isDisplayed.isDisplayed(), "Element not visible!");
    }

    public void isCartIconClickable(By locator) {
        boolean isEnabled = driver.findElement(locator).isEnabled();
        Assert.assertTrue(isEnabled, "Cart icon not clickable!");
    }

    public void isCounterNumberPresent(By locator) {
        WebElement element = driver.findElement(locator);
        Assert.assertNotNull(element, "Counter number not present!");
    }

    public void addProductToCart(By productLocator, By toCartButtonLocator) {
        clickOnElement(productLocator);
        WebElement toCartButton = driver.findElement(toCartButtonLocator);
        Assert.assertTrue(toCartButton.isDisplayed() && toCartButton.isEnabled(), "Add to Cart button not ready!");
        clickOnElement(toCartButtonLocator);
        isVisibleSuccessMessage();
        WebElement cartIcon = driver.findElement(CART_ICON);
        Assert.assertNotNull(cartIcon, "Cart icon not present!");
        Assert.assertTrue(cartIcon.isEnabled(), "Cart icon not clickable!");
        clickOnElement(CART_ICON);
    }

    public void addItemToCart(By sizeLocator, By colorLocator, By addToCartButtonLocator) {
        clickFirstElement(sizeLocator);
        clickFirstElement(colorLocator);
        clickOnElement(addToCartButtonLocator);
    }

    public void gotoCartPage(By cartIconLocator) {
        assertVisibleOfElement(cartIconLocator);
        driver.findElement(cartIconLocator).click();
        miniCard.isMiniCartVisible();
        miniCard.clickMiniCart();
    }

    public void scrollTo(By locator) {
        scrollTo(driver.findElement(locator));
    }

    public double getSubtotal(By cartIconLocator, By amountPriceLocator) {
        isCartIconClickable(cartIconLocator);
        clickOnElement(cartIconLocator);
        WebElement amountPrice = driver.findElement(amountPriceLocator);
        String price = amountPrice.getAttribute("innerText").replace("$", "");
        return Double.parseDouble(price);
    }

    public void clickFirstElement(By sizeLocator) {
        List<WebElement> elements = driver.findElements(sizeLocator);
        if (!elements.isEmpty()) {
            elements.get(0).click();
        }
    }

    public void isVisibleSuccessMessage() {
        WebElement message = driver.findElement(SUCCESS_MESSAGE);
        Assert.assertTrue(message.isDisplayed()
                        && message.getText().contains("You added")
                        && message.getText().contains("to your shopping cart"),
                "Success message not as expected!");
    }

    public void scrollTo(WebElement element) {
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String getInnerText(By locator) {
        return driver.findElement(locator).getAttribute("innerText");
    }

    public void clickOnElement(By locator) {
        driver.findElement(locator).click();
    }

    public void addProductToCartWithQty(By sizeLocator, By colorLocator, By qtyLocator, By addToCartButtonLocator, String qty) {
        clickOnElement(sizeLocator);
        clickOnElement(colorLocator);
        WebElement qtyElement = driver.findElement(qtyLocator);
        qtyElement.click();
        qtyElement.clear();
        qtyElement.sendKeys(qty);
        clickOnElement(addToCartButtonLocator);
        isVisibleSuccessMessage();
    }
}
