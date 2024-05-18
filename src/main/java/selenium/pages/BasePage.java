package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class BasePage {

    private final WebDriver driver;
    private final NavComponent nav;
    private final MiniCard miniCard;

    protected static final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private static final By CART_ICON = By.cssSelector("a.showcart");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".message-success.success.message");

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.nav = new NavComponent(driver);
        this.miniCard = new MiniCard(driver);
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
        WebElement element = driver.findElement(locator);
        assert element.isDisplayed() : "Element not visible!";
    }

    public void assertPresentOfElement(By locator) {
        WebElement element = driver.findElement(locator);
        assert element != null : "Element not present!";
    }

    public void isCartIconPresent(By locator) {
        WebElement element = driver.findElement(locator);
        assert element != null : "Cart icon not present!";
    }

    public void isCartIconClickable(By locator) {
        WebElement element = driver.findElement(locator);
        assert element.isEnabled() : "Cart icon not clickable!";
    }

    public void isCounterNumberPresent(By locator) {
        WebElement element = driver.findElement(locator);
        assert element != null : "Counter number not present!";
    }

    public void isCounterNumberVisible(By locator) {
        WebElement element = driver.findElement(locator);
        assert element.isDisplayed() : "Counter number not visible!";
    }

    public void addProductToCart(By productLocator, By toCartButtonLocator) {
        WebElement product = driver.findElement(productLocator);
        product.click();

        WebElement toCartButton = driver.findElement(toCartButtonLocator);
        assert toCartButton.isDisplayed() && toCartButton.isEnabled() : "Add to Cart button not ready!";
        toCartButton.click();
        isVisibleSuccessMessage();

        WebElement cartIcon = driver.findElement(CART_ICON);
        assert cartIcon.isEnabled() : "Cart icon not clickable!";
        cartIcon.click();
    }

    public void addItemToCart(By sizeLocator, By colorLocator, By addToCartButtonLocator) {
        driver.findElement(sizeLocator).click();
        driver.findElement(colorLocator).click();
        driver.findElement(addToCartButtonLocator).click();
    }

    public void gotoCartPage(By cartIconLocator) {
        isCartIconPresent(cartIconLocator);
        driver.findElement(cartIconLocator).click();
        miniCard.isMiniCartVisible();
        miniCard.clickMiniCart();
    }

    public void scrollToHotSellers(By productsGridLocator) {
        WebElement element = driver.findElement(productsGridLocator);
        scrollTo(element);
    }

    public double getSubtotal(By cartIconLocator, By amountPriceLocator) {
        isCartIconClickable(cartIconLocator);
        driver.findElement(cartIconLocator).click();
        WebElement amountPrice = driver.findElement(amountPriceLocator);
        String price = amountPrice.getAttribute("innerText");
        return Double.parseDouble(price.replace("$", ""));
    }

    public void setSize(By sizeLocator) {
        chooseFirst(driver.findElements(sizeLocator));
    }

    public void setColor(By colorLocator) {
        chooseFirst(driver.findElements(colorLocator));
    }

    private void chooseFirst(java.util.List<WebElement> elements) {
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

    public static String getText(WebDriver driver, By locator) {
        return driver.findElement(locator).getAttribute("innerText");
    }

    public static void clickOnLink(WebDriver driver, By locator) {
        driver.findElement(locator).click();
    }

    public void addProductToCartWithQty(By sizeLocator, By colorLocator, By qtyLocator, By addToCartButtonLocator, String qty) {
        driver.findElement(sizeLocator).click();
        driver.findElement(colorLocator).click();
        WebElement qtyElement = driver.findElement(qtyLocator);
        qtyElement.click();
        qtyElement.clear();
        qtyElement.sendKeys(qty);
        driver.findElement(addToCartButtonLocator).click();
        isVisibleSuccessMessage();
    }
}
