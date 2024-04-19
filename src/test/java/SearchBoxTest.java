import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;

import static com.rover.DriverSingleton.getDriver;

public class SearchBoxTest extends BaseTest {

    @Test
    public void testFindBackPack() throws InterruptedException {
        getDriver().get("https://magento.softwaretestingboard.com");
        getDriver().findElement(By.xpath("//input[@name='q']")).sendKeys("back");

    }

}
