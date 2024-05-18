package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static selenium.pages.NavComponent.MEN_PAGE_URL;

public class MenPage extends BasePage {

    private final String font = "rgba(51, 51, 51, 1)";

    @FindBy(css = "#ui-id-5")
    private WebElement navMen;

    public MenPage(WebDriver driver) {
        super(driver);
        visit(MEN_PAGE_URL);
    }

    public void isActive() {
        Assert.assertEquals(navMen.getCssValue("color"), font, "Font color not as expected!");
    }
}
