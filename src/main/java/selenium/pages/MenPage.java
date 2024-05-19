package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static selenium.pages.NavComponent.MEN_PAGE_URL;

public class MenPage extends BasePage {

    public MenPage(WebDriver driver) {
        super(driver);
        visit(MEN_PAGE_URL);
    }

    public void assertUrl() {
        Assert.assertEquals(getCurrentUrl(), MEN_PAGE_URL);
    }
}
