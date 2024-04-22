package selenium;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;

import static runner.TestData.BASE_URL;

public class SearchBoxTest extends BaseTest {

    @Test
    public void testFindBackPack() {
        getDriver(BASE_URL).get("https://magento.softwaretestingboard.com");
        getDriver(BASE_URL).findElement(By.xpath("//input[@name='q']")).sendKeys("back");
    }
}
