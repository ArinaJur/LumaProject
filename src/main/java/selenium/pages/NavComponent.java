package selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

import static selenium.pages.BasePage.BASE_URL;

public class NavComponent {
    public static final String MEN_PAGE_URL = BASE_URL + "/men.html";
    public static final By NAV_MENU = By.cssSelector("#ui-id-2");
    public static final By NAV_MEN = By.cssSelector("#ui-id-5");
    public static final By NAV_MEN_TOPS = By.cssSelector("#ui-id-17");
    public static final By NAV_MEN_BOTTOMS = By.cssSelector("#ui-id-18");
    public static final By NAV_MEN_TOPS_JACKET = By.cssSelector("#ui-id-19");
    public static final By NAV_MEN_TOPS_HOODIES = By.cssSelector("#ui-id-20");
    public static final By NAV_MEN_TOPS_TEES = By.cssSelector("#ui-id-21");
    public static final By NAV_MEN_TOPS_TANKS = By.cssSelector("#ui-id-22");
    public static final By NAV_MEN_SUBMENU = By.cssSelector("li.level1.nav-3-1.category-item.first.parent.ui-menu-item");
    public static final By NAV_MEN_BOTTOMS_SUBMENU = By.cssSelector("li.level1.nav-3-2.category-item.last.parent.ui-menu-item");
    public static final By NAV_MEN_TOPS_SUBMENU_HREFS = By.cssSelector(".nav-3-1 > ul > li > a");
    public static final By NAV_MEN_BOTTOMS_SUBMENU_HREFS = By.cssSelector(".nav-3-2 > ul > li > a");

    public static final Map<String, String> menTopUrls = new HashMap<>();
    public static final Map<String, String> menBottomUrls = new HashMap<>();
    public static final Map<String, String> menSubUrls = new HashMap<>();

    static {
        menTopUrls.put("Jackets", BASE_URL + "/men/tops-men/jackets-men.html");
        menTopUrls.put("Hoodies & Sweatshirts", BASE_URL + "/men/tops-men/hoodies-and-sweatshirts-men.html");
        menTopUrls.put("Tees", BASE_URL + "/men/tops-men/tees-men.html");

        menTopUrls.put("Tanks", BASE_URL + "/men/tops-men/tanks-men.html");
        menBottomUrls.put("Pants", BASE_URL + "/men/bottoms-men/pants-men.html");
        menBottomUrls.put("Shorts", BASE_URL + "/men/bottoms-men/shorts-men.html");

        menSubUrls.put("Tops", BASE_URL + "/men/tops-men.html");
        menSubUrls.put("Bottoms", BASE_URL + "/men/bottoms-men.html");
    }

    private final WebDriver driver;

    public NavComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void isHaveText(By locator, String value) {
        WebElement element = driver.findElement(locator);
        Assert.assertEquals(element.getText(), value, "Text not as expected!");
    }

    public void isClickable(By locator, String url) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isEnabled(), "Element not clickable!");
        Assert.assertEquals(element.getAttribute("href"), url, "URL not as expected!");
    }

    public void isHaveHeader(By headerLocator, String header) {
        WebElement headerElement = driver.findElement(headerLocator);
        Assert.assertEquals(headerElement.getText(), header, "Header text not as expected!");
    }

    public void verifySubMenTops() {
        WebElement submenus = driver.findElement(NAV_MEN_SUBMENU);
        Assert.assertTrue(submenus.isDisplayed(), "Submenus not visible!");
        for (String expectedElement : menTopUrls.keySet()) {
            Assert.assertTrue(submenus.getText().contains(expectedElement), "Expected element not found!");
        }
    }

    public void verifySubMenBottoms() {
        WebElement submenus = driver.findElement(NAV_MEN_BOTTOMS_SUBMENU);
        Assert.assertTrue(submenus.isDisplayed(), "Submenus not visible!");
        for (String expectedElement : menBottomUrls.keySet()) {
            Assert.assertTrue(submenus.getText().contains(expectedElement), "Expected element not found!");
        }
    }

    public void verifyDropdownMenu() {
        verifyNavMensTops();
        verifyNavMensBottoms();
    }

    public void verifyNavMen() {
        verifyNav(NAV_MEN, "Men");
        isClickable(NAV_MEN, MEN_PAGE_URL);
    }

    public void verifyNavMensTops() {
        verifyNav(NAV_MEN_TOPS, "Tops");
        isClickable(NAV_MEN_TOPS, menSubUrls.get("Tops"));
    }

    public void verifyNavMensBottoms() {
        verifyNavMens(NAV_MEN_BOTTOMS, "Bottoms");
        isClickable(NAV_MEN_BOTTOMS, menSubUrls.get("Bottoms"));
    }

    public void verifyNavMens(By locator, String nav) {
        verifyNav(locator, nav);
        isClickable(locator, menSubUrls.get(nav));
    }

    public void verifyNav(By locator, String title) {
        WebElement element = driver.findElement(locator);
        Assert.assertTrue(element.isDisplayed(), "Locator not visible!");
        isHaveText(locator, title);
    }
}
