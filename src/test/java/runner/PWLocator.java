package runner;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import java.util.List;

public abstract class PWLocator extends BaseTest {

    protected Locator textExact(String text) {
        return getPage().getByText(text, new Page.GetByTextOptions().setExact(true));
    }

    protected Locator link(String text) {
        return getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(text));
    }

    protected Locator css(String css) {
        return getPage().locator(css);
    }

    protected Locator xpath(String xpath) {
        return getPage().locator(xpath);
    }

    protected void navigate(String url) {
        getPage().navigate(url);
    }

    protected Locator label(String label) {
        return getPage().getByLabel(label);
    }

    protected Locator title(String title) {
        return getPage().getByTitle(title);
    }

    protected List<String> getListNames(Locator locator) {
        return locator.allInnerTexts();
    }

    protected Locator createLocatorWithHrefLink(String[] url, int index) {
        String locatorString = "[href='" + url[index] + "']";
        return getPage().locator(locatorString);
    }


}
