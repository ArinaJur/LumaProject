package runner;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.nio.file.Paths;

import java.util.List;

import static runner.TestData.BASE_URL;

public abstract class BaseTest {
    private WebDriver driver;
    private final Playwright playwright = Playwright.create();
    private final Browser browser = BaseUtils.createPWBrowser(playwright);
    private BrowserContext context;
    private Page page;

    @BeforeSuite
    void createPlaywrightBrowser() {
        if(!browser.isConnected()) {
            System.exit(1);
        }
    }

    @BeforeMethod
    protected void beforeMethod() {
        driver = BaseUtils.createDriver();
        context = BaseUtils.createContext(browser);
        page = context.newPage();
    }

    @AfterMethod
    protected void afterMethod(ITestResult testResult, Method testMethod) {
        if (driver != null) {
            driver.quit();
        }

        if (page != null) {
            page.close();
        }

        if (!testResult.isSuccess()) {
            page.video().saveAs(Paths.get("video/" + testMethod.getName() + ".webm"));
            page.video().delete();
        } else {
            page.video().delete();
        }

        if (context != null) {
            context.close();
        }
    }

    @AfterSuite(alwaysRun = true)
    void closeBrowser() {
        if (browser.isConnected()) {
            browser.close();
        }
        if(playwright != null) {
            playwright.close();
        }
    }

    public WebDriver getDriver(String baseUrl) {
        return driver;
    }

    public Page getPage() {
        return page;
    }

    public void openBaseUrlSelenium() {
        getDriver(BASE_URL).get(BASE_URL);
        List<WebElement> consentElements = getDriver(BASE_URL).findElements(By.xpath("//p[text()='Consent']"));
        if(!consentElements.isEmpty()) {
            getDriver(BASE_URL).findElement(By.xpath("//p[text()='Consent']")).click();
        }
    }

    public void openBaseUrlPW(){
        getPage().navigate(BASE_URL);
        if(getPage().locator("//p[text()='Consent']").count() != 0) {
            getPage().locator("//p[text()='Consent']").click();
        }
    }
}
