package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "pass")
    private WebElement passField;

    @FindBy(id = "send2")
    private WebElement signInBtn;

    public LandingPage login(String email, String password) {
        fillEmailField(email);
        fillPasswordField(password);
        clickSignInButton();
        return new LandingPage(getDriver());
    }

    private void fillEmailField(String email) {
        emailField.sendKeys(email);
    }

    private void fillPasswordField(String password) {
        passField.sendKeys(password);
    }

    private void clickSignInButton() {
        signInBtn.click();

    }
}