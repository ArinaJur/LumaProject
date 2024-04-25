package playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.PWLocator;
import runner.TestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class VerifyDescendingSortByPriceTest extends PWLocator {

    @Test
    public void testDescendingSortByPriceTest() throws InterruptedException {
        openBaseUrlPW();
        getPage().getByLabel("Sort By").selectOption("price");
        getPage().locator("//a[@data-value='desc']").click();

        Thread.sleep(5000);

    }


}
