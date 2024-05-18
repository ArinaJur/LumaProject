package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static selenium.pages.NavComponent.MEN_PAGE_URL;
import static selenium.pages.NavComponent.NAV_MEN;

public class MenPage extends BasePage{

    public MenPage(WebDriver driver) {
        super(driver);
        openPage();
    }

    private void openPage() {
        visit(MEN_PAGE_URL);
    }

    public static void isActive(WebDriver driver) {
        String font = "rgba(51, 51, 51, 1)";
        WebElement navMenElement = driver.findElement(NAV_MEN);
        Assert.assertEquals(navMenElement.getCssValue("color"), font,
                "Font color not as expected!");
    }
}
