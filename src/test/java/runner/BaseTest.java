package runner;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.*;
import reporter.LoggerUtils;

import java.lang.reflect.Method;
import java.nio.file.Paths;

import java.util.List;

public abstract class BaseTest {
    private WebDriver driver;
    private final Playwright playwright = Playwright.create();
    private final Browser browser = BaseUtils.createPWBrowser(playwright);
    private BrowserContext context;
    private Page page;
    final String consentButtonSelector = "body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button > p";


    @BeforeSuite
    void createPlaywrightBrowser() {
        if (!browser.isConnected()) {
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
        if (playwright != null) {
            playwright.close();
            LoggerUtils.logInfo("Playwright closed");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Page getPage() {
        return page;
    }

    public void openBaseUrlSelenium() {
        getDriver().get(TestData.BASE_URL);
        LoggerUtils.logInfo("Base URL Selenium opened");

        List<WebElement> consentElements = getDriver().findElements(By.xpath("//p[text()='Consent']"));
        if (!consentElements.isEmpty()) {
            getDriver().findElement(By.xpath("//p[text()='Consent']")).click();
        }
    }

    public void openBaseUrlPW() {
        getPage().navigate(TestData.BASE_URL);
        LoggerUtils.logInfo("Base URL PW opened");
        if (getPage().isVisible(consentButtonSelector)) {
            handleOverlays(getPage());
        } else {
            LoggerUtils.logInfo("No overlays found");
        }
    }

    private void handleOverlays(Page page) {
        page.waitForSelector(consentButtonSelector);

        if (!page.isVisible(consentButtonSelector)) {
            return;
        }
        page.click(consentButtonSelector);
    }
}
