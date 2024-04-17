import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;

import java.util.concurrent.CompletableFuture;


public class SignInTest extends BaseTest {

    @Test
    public void testSingInPW() throws InterruptedException {
        getPage().navigate("https://magento.softwaretestingboard.com/");

        String consentButtonSelector = "body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button > p";

        if (getPage().isVisible(consentButtonSelector)) {
            handleOverlays(getPage());
            getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("\n" +
                    "Sign In ")).click();
        } else {
            getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("\n" +
                    "Sign In ")).click();
        }

        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("Tester3@gmail.com");
        getPage().getByLabel("Password").click();
        getPage().getByLabel("Password").fill("123456789vk_");
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        getPage().locator(" span.not-logged-in")
                .getByText("Click “Write for us” link in the footer to submit a guest post").isVisible();

        // Locator welcomeElement = getPage().locator("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.greet.welcome > span").getByText("Welcome, tester3 tester3!");
        // Locator welcomeElement = getPage().locator("#store\\.links > ul > li.greet.welcome > span").getByText("Welcome, tester3 tester3!");
        Thread.sleep(3000);
        Locator welcomeElement = getPage().locator("//div[@class='panel header']//ul[@class='header links']//li[@class='greet welcome']//span[@class='logged-in']");
        System.out.println(welcomeElement);
        String actual = welcomeElement.innerText();
        Assert.assertEquals(actual, "Welcome, tester3 tester3!");
        Assert.assertTrue(welcomeElement.isVisible());

//        if (actual) {
//            System.out.println("First element is visible");
//            String welcomeText = welcomeElement.innerText();
//            Assert.assertEquals(welcomeText, "Welcome, tester3 tester3!");
//        } else {
//            Locator secondLocator = getPage().locator(".logged-in").first().getByText("Welcome, tester3 tester3!");
//            boolean isSecondVisible = secondLocator.isVisible();
//            if (isSecondVisible) {
//                System.out.println("Second element is visible");
//                String secondText = secondLocator.innerText();
//                Assert.assertEquals(secondText, "Welcome, tester3 tester3!");
//            } else {
//                String welcome = "Welcome, tester3 tester3!";
//                String notVisible = "Element is not visible";
//                String getText = getPage().getByText("Welcome, tester3 tester3!").first().isVisible() ? welcome : notVisible;
//                System.out.println(getText);
//                Assert.assertEquals(getText, "Welcome, tester3 tester3!");
//            }
//        }
    }

    private void handleOverlays(Page page) {
        String consentButtonSelector = "body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button > p";
        page.waitForSelector(consentButtonSelector);

        if (!page.isVisible(consentButtonSelector)) {
            return;
        }
        page.click(consentButtonSelector);
    }
}

