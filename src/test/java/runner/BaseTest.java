package runner;

import com.microsoft.playwright.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporter.LoggerUtils;

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
            LoggerUtils.logFatal("FATAL: PWBrowser is not created");
            System.exit(1);
        }
    }

    @BeforeMethod
    protected void beforeMethod() {
        driver = BaseUtils.createDriver();
        LoggerUtils.logInfo("Browser instance started");
        LoggerUtils.logInfo("Selenium Test started");

        context = BaseUtils.createContext(browser);
        LoggerUtils.logInfo("Context created");

        page = context.newPage();
        LoggerUtils.logInfo("Page created");
        LoggerUtils.logInfo("PW Test started");
    }

    @AfterMethod
    protected void afterMethod(ITestResult testResult, Method testMethod) {
        if (driver != null) {
            driver.quit();
            LoggerUtils.logInfo("Browser closed");
        }

        if (page != null) {
            page.close();
            LoggerUtils.logInfo("Page closed");
        }

        if (!testResult.isSuccess()) {
            page.video().saveAs(Paths.get("video/" + testMethod.getName() + ".webm"));
            page.video().delete();
        } else {
            page.video().delete();
        }

        if (context != null) {
            context.close();
            LoggerUtils.logInfo("Context closed");
        }
    }

    @AfterSuite(alwaysRun = true)
    void closeBrowser() {
        if (browser.isConnected()) {
            browser.close();
            LoggerUtils.logInfo("Browser closed");
        }
        if(playwright != null) {
            playwright.close();
            LoggerUtils.logInfo("Playwright closed");
        }
    }

    public WebDriver getDriver(String baseUrl) {
        return driver;
    }

    public Page getPage() {
        return page;
    }

    public void openBaseUrlSelenium() {
        getDriver().get(TestData.BASE_URL);
        LoggerUtils.logInfo("Base URL Selenium opened");

        List<WebElement> consentElements = getDriver().findElements(By.xpath("//p[text()='Consent']"));
        if(!consentElements.isEmpty()) {
            getDriver(BASE_URL).findElement(By.xpath("//p[text()='Consent']")).click();
        }
    }

    public void openBaseUrlPW(){
        getPage().navigate(TestData.BASE_URL);
        LoggerUtils.logInfo("Base URL PW opened");

        if(getPage().locator("//p[text()='Consent']").count() != 0) {
            getPage().locator("//p[text()='Consent']").click();
        }
    }
}
