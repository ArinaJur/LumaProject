package vfespw;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.Assert;
import org.testng.annotations.Test;
import vfespw.runner.TestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HeaderTest extends BaseLocators {
    @Test
    public void test_EmptyCartMessage() {
        getPage().navigate(TestData.BASE_URL);
        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(" My Cart")).click();

        Locator miniCart = getPage().locator("#ui-id-1");
        assertThat(miniCart).isVisible();

        String actualEmptyCardMessage = getPage().locator(".block-minicart .subtitle.empty").innerText();

        Assert.assertEquals(actualEmptyCardMessage, TestData.EMPTY_CARD_MESSAGE,
                "If FAIL: the Empty Cart Message message is wrong or missing");
    }
}
