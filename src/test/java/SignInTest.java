import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import runner.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.AssertJUnit.assertEquals;

public class SignInTest extends BaseTest {

    @Test
    public void testSingInPW() {
        getPage().navigate("https://magento.softwaretestingboard.com/");

            getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("\n" +
                    "Sign In ")).click();
            getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).click();
            getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill("Tester3@gmail.com");
            getPage().getByLabel("Password").click();
            getPage().getByLabel("Password").fill("123456789vk_");
            getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

            assertThat(getPage().getByRole(AriaRole.BANNER).getByText("Welcome, tester3 tester3!")).isVisible();

    }
}
