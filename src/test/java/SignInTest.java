import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import runner.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class SignInTest extends BaseTest {

    @Test
    public void testSingInPW() {
        getPage().navigate("https://magento.softwaretestingboard.com/");

        handleOverlays(getPage());

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("\n" +
                "Sign In ")).click();

        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("Tester3@gmail.com");
        getPage().getByLabel("Password").click();
        getPage().getByLabel("Password").fill("123456789vk_");
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

        assertThat(getPage().getByRole(AriaRole.BANNER).getByText("Welcome, tester3 tester3!")).isVisible();
    }

    private void handleOverlays(Page page) {
        String consentButtonSelector = "body > div.fc-consent-root > div.fc-dialog-container > div.fc-dialog.fc-choice-dialog > div.fc-footer-buttons-container > div.fc-footer-buttons > button.fc-button.fc-cta-consent.fc-primary-button > p";
        page.waitForSelector(consentButtonSelector);

        if (page.isVisible(consentButtonSelector)) {
            page.click(consentButtonSelector);
        }
    }
}
