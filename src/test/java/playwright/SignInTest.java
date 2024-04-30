package playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;

public class SignInTest extends BaseTest {
    @Ignore
    @Test
    public void testSingInPW() throws InterruptedException {
        openBaseUrlPW();

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign In")).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("tester1234@gmail.com");
        getPage().getByLabel("Password").click();
        getPage().getByLabel("Password").fill("tester1234!");
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
        Thread.sleep(3000);
        getPage().locator(" span.not-logged-in")
                .getByText("Click “Write for us” link in the footer to submit a guest post").isVisible();

            Locator locator = getPage().locator(".logged-in").first().getByText("Welcome, tester3 tester3!");
            boolean isVisible = locator.isVisible();
            if (isVisible) {
                String text = locator.innerText();
                Assert.assertEquals(text, "Welcome, tester3 tester3!");
            }
        }
}

