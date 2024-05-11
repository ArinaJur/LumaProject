package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WishlistPage extends BasePage {

    @FindBy(className = "message-success")
    private WebElement productMessage;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(productMessage));
        return productMessage.isDisplayed();
    }
}
