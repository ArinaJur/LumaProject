package selenium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SearchBoxTest extends BaseTest {

    @Test
    public void testFindBackPack() {
        getDriver().get("https://magento.softwaretestingboard.com");
        getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys("back");
    }
}
