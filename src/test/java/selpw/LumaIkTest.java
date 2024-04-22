package selpw;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.PWLocator;

import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LumaIkTest extends PWLocator {

    private final String BASE_URL = "https://magento.softwaretestingboard.com/";
    private final List<String> EXPECTED_NAMES_WOMEN_DROPDOWN = List.of("Tops", "Bottoms");
    private final List<String> EXPECTED_NAMES_TOPS_WOMEN_DROPDOWN = List.of("Jackets", "Hoodies & Sweatshirts", "Tees", "Bras & Tanks");
    private final List<String> EXPECTED_NAMES_BOTTOMS_WOMEN_DROPDOWN = List.of("Pants", "Shorts");

    private void openHomePage() {
        getPage().navigate(BASE_URL);
    }

    @Test
    public void testNavigationBar(){

        List<String> expectedListOfNavBar = List.of("What's New", "Women", "Men", "Gear", "Training","Sale");

        getDriver().get("https://magento.softwaretestingboard.com/");

        List<WebElement> listOfNavBar = getDriver().findElements(By.cssSelector(".nav-sections .navigation >ul>li>a"));
        List<String> namesOfNavbar = new ArrayList<>();
        for(WebElement text : listOfNavBar) {
            namesOfNavbar.add(text.getText());
        }

        Assert.assertEquals(namesOfNavbar, expectedListOfNavBar);
    }

    @Test
    public void testNavigationBarPW(){
        String [] expectedListOfNavBar = {"What's New", "Women", "Men", "Gear", "Training", " Sale"} ;

        getPage().getByLabel("Consent",new Page.GetByLabelOptions());
        getPage().navigate("https://magento.softwaretestingboard.com/");
        Locator navBar = getPage().locator(".nav-sections .navigation >ul>li>a");

        assertThat(navBar).hasCount(6);
        assertThat(navBar).containsText(expectedListOfNavBar);
    }

    @Test
    public void testNamesWomenDropDownPW() throws InterruptedException {
        openHomePage();
        textExact("Women").hover();
        Locator listWomenDropDown = css("li.nav-2>ul>li>a.ui-corner-all");
        List<String> namesWomensDropDown = getListNames(listWomenDropDown);
        Locator listTopDropDown = css(".nav-2-1>ul>li>a>span");
        List<String>namesTopDropDown = getListNames(listTopDropDown);
        Locator listBottomosDropDown = css(".nav-2-2>ul>li");
        List<String> namesBottomsSection = getListNames(listBottomosDropDown);

        assertThat(listWomenDropDown).hasCount(2);
        assertThat(listTopDropDown).hasCount(4);
        assertThat(listBottomosDropDown).hasCount(2);
        Assert.assertEquals(namesWomensDropDown, EXPECTED_NAMES_WOMEN_DROPDOWN);
        Assert.assertEquals(namesBottomsSection, EXPECTED_NAMES_BOTTOMS_WOMEN_DROPDOWN);
        Assert.assertEquals(namesTopDropDown, EXPECTED_NAMES_TOPS_WOMEN_DROPDOWN);
    }

    @Test()
    public void testWomenDropDownButtonsClickablePW1()  {
        Locator listWomenDRopDown = css(".navigation li.nav-2>ul>li>a");
        Locator logo = css(".logo");
        Locator headerWomensSectons = css(".page-title");
        Locator listTopDropDown = css("li.nav-2-1>ul>li>a");
        String[] hrefsWomenDropDown = {"https://magento.softwaretestingboard.com/women/tops-women.html", "https://magento.softwaretestingboard.com/women/bottoms-women.html"};
        String[] hrefsWomenTopsDropDown = {"https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html", "https://magento.softwaretestingboard.com/women/tops-women/hoodies-and-sweatshirts-women.html",
                "https://magento.softwaretestingboard.com/women/tops-women/tees-women.html", "https://magento.softwaretestingboard.com/women/tops-women/tanks-women.html"};

        openHomePage();

        for (int i = 0; i < listWomenDRopDown.count(); i++) {
            textExact("Women").isVisible();
            textExact("Women").hover();
            createLocatorWithHrefLink(hrefsWomenDropDown, i).click();
            String text = headerWomensSectons.innerText();

            Assert.assertEquals(text, EXPECTED_NAMES_WOMEN_DROPDOWN.get(i));

            logo.click();

            assertThat(getPage()).hasURL(BASE_URL);
        }

        for (int i = 0; i < listTopDropDown.count(); i++) {
            textExact("Women").isVisible();
            textExact("Women").hover();
            createLocatorWithHrefLink(hrefsWomenDropDown,0).isVisible();
            createLocatorWithHrefLink(hrefsWomenDropDown,0).hover();
            createLocatorWithHrefLink(hrefsWomenTopsDropDown, i).click();
            String actualHeader = headerWomensSectons.innerText();

            Assert.assertEquals(actualHeader, EXPECTED_NAMES_TOPS_WOMEN_DROPDOWN.get(i));

            logo.click();

            assertThat(getPage()).hasURL(BASE_URL);
        }
    }
}


