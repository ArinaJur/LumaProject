package vfespw;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import runner.BaseTest;
import vfespw.runner.ProjectUtils;

abstract class BaseLocators extends BaseTest {

    protected Locator link(String text) {

        return getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(text));
    }
    protected Locator button(String text) {

        return getPage().getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator locator(String css) {

        return getPage().locator(css);
    }
    protected Locator textbox() {

        return getPage().getByRole(AriaRole.TEXTBOX);
    }
    protected Locator exactTextbox(String text) {
        return getPage().getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName(text).setExact(true));
    }

    Locator label(String label) {
        return getPage().getByLabel(label);
    }
    Locator exactLabel(String label) {
        return getPage().getByLabel(label, new Page.GetByLabelOptions().setExact(true));
    }

    //"to run 1 time for creation a user until their database get cleared, no way to delete existing user";
    public void createUser(String firstName, String lastName) {
        link("\n" + "Create an Account").click();
        label("First Name").fill(firstName);
        label("Last Name").fill(lastName);

        exactLabel("Email").fill(ProjectUtils.getProperties().getProperty("email"));
        exactTextbox("Password*").fill(ProjectUtils.getProperties().getProperty("password"));
        label("Confirm Password").fill(ProjectUtils.getProperties().getProperty("password"));
        button("Create an Account").click();
    }

    public void signIn() {
        link("\n" + "Sign In ").click();
        exactLabel("Email").fill(ProjectUtils.getProperties().getProperty("email"));
        label("Password").fill(ProjectUtils.getProperties().getProperty("password"));
        button("Sign In").click();
    }
    public void signIn(String email, String password) {
        link("\n" + "Sign In ").click();
        exactLabel("Email").fill(email);
        label("Password").fill(password);
        button("Sign In").click();
    }
    public void signOut() {
        getPage().getByRole(
                AriaRole.BANNER).locator("button").filter(new Locator.FilterOptions().setHasText("Change")
        ).click();
        link("Sign Out").click();
    }
}
