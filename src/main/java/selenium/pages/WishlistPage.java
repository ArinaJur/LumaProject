package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WishlistPage extends BasePage {

    @FindBy(className = "message-success")
    private WebElement productMessage;

    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public boolean isMessageDisplayed() {
        return productMessage.isDisplayed();
    }
}
