package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.Assert;

public class MiniCard {
    private final WebDriver driver;
    private final By action = By.cssSelector("a.action");
    private final By quantity = By.cssSelector(".details-qty input");
    private final By message = By.cssSelector(".message-success");
    private final By miniCart = By.cssSelector("#ui-id-1");
    private final By miniCartView = By.cssSelector(".action.viewcart");
    private final By update = By.cssSelector("[title='Update']");

    public MiniCard(WebDriver driver) {
        this.driver = driver;
    }

    public void isMiniCartPresent() {
        WebElement element = driver.findElement(miniCart);
        Assert.assertTrue(element.isDisplayed(), "Mini cart not present!");
    }

    public void isMiniCartVisible() {
        WebElement element = driver.findElement(miniCart);
        Assert.assertTrue(element.isDisplayed(), "Mini cart not visible!");
    }

    public void isMiniCartViewPresent() {
        WebElement element = driver.findElement(miniCartView);
        Assert.assertTrue(element.isDisplayed(), "Mini cart view not present!");
    }

    public void isMiniCartViewVisible() {
        WebElement element = driver.findElement(miniCartView);
        Assert.assertTrue(element.isDisplayed(), "Mini cart view not visible!");
    }

    public void isMiniCartHaveLink(String cartUrl) {
        WebElement element = driver.findElement(miniCartView);
        Assert.assertEquals(element.getAttribute("href"), cartUrl,
                "Mini cart view href not as expected!");
    }

    public void checkColorInTheMiniCart(String color) {
        WebElement element = driver.findElement(action);
        String expectedColor = Color.fromString(color).asRgba();
        Assert.assertEquals(element.getCssValue("color"), expectedColor,
                "Color not as expected!");
    }

    public void checkSizeColorAndProductNameAreCorrect(By seeDetailsLocator, By sizeLocator, String size, By colorLocator, String color, By nameItemLocator, String name) {
        driver.findElement(seeDetailsLocator).click();
        WebElement sizeElement = driver.findElement(sizeLocator);
        WebElement colorElement = driver.findElement(colorLocator);
        WebElement nameElement = driver.findElement(nameItemLocator);

        Assert.assertEquals(sizeElement.getText(), size, "Size not as expected!");
        Assert.assertEquals(colorElement.getText(), color, "Color not as expected!");
        Assert.assertEquals(nameElement.getText(), name, "Product name not as expected!");
    }

    public void changeQty(String qty) {
        WebElement qtyElement = driver.findElement(quantity);
        qtyElement.click();
        qtyElement.sendKeys(Keys.BACK_SPACE + qty);
        driver.findElement(update).click();
    }

    public void shouldBeQuantityChange(String qty) {
        WebElement qtyElement = driver.findElement(quantity);
        Assert.assertEquals(qtyElement.getAttribute("value"), qty, "Quantity not as expected!");
    }

    public void shouldBeSuccessMessage() {
        WebElement messageElement = driver.findElement(message);
        Assert.assertTrue(messageElement.isDisplayed(), "Success message not visible!");
    }

    public void shouldBeChangeSubtotal(By priceItemLocator, String price, By cartSubtotalLocator, String total) {
        WebElement priceElement = driver.findElement(priceItemLocator);
        WebElement subtotalElement = driver.findElement(cartSubtotalLocator);
        Assert.assertEquals(priceElement.getText(), price, "Price not as expected!");
        Assert.assertEquals(subtotalElement.getText(), total, "Subtotal not as expected!");
    }

    public void clickMiniCart() {
        WebElement miniCartElement = driver.findElement(miniCart);
        assert miniCartElement.isEnabled() : "Mini cart not clickable!";
        miniCartElement.click();
    }
}
