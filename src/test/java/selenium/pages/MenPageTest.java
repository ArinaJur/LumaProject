package selenium.pages;

import org.testng.annotations.Test;
import runner.BaseTest;

public class MenPageTest extends BaseTest {

    @Test
    public void testVerifyingMenLinkIsDisplayedClickableRedirectionInTheMainPage() {
        getPage().close();
        MainPage page = new MainPage(getDriver());
        page.verifyMenNav();
        MenPage men = page.gotoMenPage();
        men.assertUrl();
        men.verifyMenNav();
    }
}