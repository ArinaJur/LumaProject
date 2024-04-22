package selpw;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import runner.BaseTest;

public class lgVerifyDescendingSortByPriceTest extends BaseTest {
    private static final String BASE_URL = "https://magento.softwaretestingboard.com/";

    private void openMenTopsPageSelenium() {
        openBaseUrlSelenium();
        getDriver(BASE_URL).findElement(By.linkText("Men")).click();
        getDriver(BASE_URL).findElement(By.linkText("Tops")).click();
    }

    @Test

    public void testVerifyDescendingSortByPriceOBSelenium() {
        openMenTopsPageSelenium();
        getDriver(BASE_URL).findElement(By.xpath("//option[@value='price']")).click();
        getDriver(BASE_URL).findElement(By.linkText("Set Descending Direction")).click();

    }




}
