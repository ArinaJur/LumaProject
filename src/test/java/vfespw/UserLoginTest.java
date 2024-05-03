package vfespw;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import reporter.LoggerUtils;
import vfespw.runner.TestData;
import vfespw.runner.ProjectUtils;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UserLoginTest extends BaseLocators {

    @Test
    public void test_createUser()  {
        getPage().navigate(TestData.BASE_URL);
        createUser(TestData.FIRST_NAME, TestData.LAST_NAME);

        Locator alert = getPage().getByRole(AriaRole.ALERT).locator("div").first();

        if (alert.isVisible()) {
            LoggerUtils.logError("FAIL: " + alert.innerText());
        } else {
            assertThat(getPage()).hasTitle(TestData.MY_ACCOUNT_TITLE);
            assertThat(getPage()).hasURL(TestData.BASE_URL + TestData.MY_ACCOUNT_END_POINT);
        }
    }

    @Test
    public void test_UserNameAfterLogin()  {
        final String expectedName = "Welcome, " +  TestData.USER_NAME + "!";
        final String email = ProjectUtils.getProperties().getProperty("email");
        final String password = ProjectUtils.getProperties().getProperty("password");

        getPage().navigate(TestData.BASE_URL);

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("\n" + "Sign In ")).click();
        getPage().getByLabel("Email", new Page.GetByLabelOptions().setExact(true)).fill(email);
        getPage().getByLabel("Password").fill(password);
        getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();

        Locator alert = getPage().getByRole(AriaRole.ALERT).locator("div").first();
        String actualName = "";
        if (alert.isVisible()) {
            System.out.println(alert.innerText());
            createUser(email, password);
            signIn(email, password);
            actualName = getPage().locator("header .logged-in").innerText();
        } else {
            actualName = getPage().locator("header .logged-in").innerText();
        }

        Assert.assertEquals(actualName, expectedName, "FAIL: User name is invalid or missing");
    }

    @Test
    public void test_ErrorMessageIfSignInWithNotExistingUser()  {
        String[] emailParts = TestData.EMAIL.split("@");
        String newEmail = emailParts[0] + "+1@" + emailParts[1];

        getPage().navigate(TestData.BASE_URL);
        signIn(newEmail, TestData.PASSWORD);
        Locator alert = getPage().getByRole(AriaRole.ALERT).locator("div").first();

        String actualErrorMsg = alert.innerText();

        Assert.assertEquals(actualErrorMsg, TestData.FAILED_LOGIN_ERROR_MESSAGE,
                "FAIL: Error message is invalid or missing");
    }

    @Test
    public void test_SignOutOptionNavigatesToSuccessSignOutPage() {
        getPage().navigate(TestData.BASE_URL);
        signIn();

        getPage().getByRole(
                AriaRole.BANNER).locator("button")
                .filter(new Locator.FilterOptions().setHasText("Change")
        ).click();
        link("Sign Out").click();

        assertThat(getPage()).hasTitle(TestData.SIGN_OUT_TITLE);
        assertThat(getPage()).hasURL(TestData.BASE_URL + TestData.SIGN_OUT_END_POINT);
    }
}
