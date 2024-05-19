package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.commons.lang3.ArrayUtils;

import java.util.List;

public class NavBar extends BasePage {

    private final String URL = "https://magento.softwaretestingboard.com/checkout/cart/";

    @FindBy(css = "a.level-top")
    private List<WebElement> categories;

    @FindBy(css = ".parent .category-item a")
    private List<WebElement> subcategories;


    public NavBar(WebDriver driver) {
        super(driver);
    }

    public StorePage selectCategory(String categoryName, String... subcategoryNames) {
        WebElement selectedCategory = categories.stream().filter(c -> categoryName.equals(c.getText()))
                .findFirst().orElseThrow(RuntimeException::new);
        if (!ArrayUtils.isEmpty(subcategoryNames)) {
            hover(selectedCategory);
            for (String subcategoryName : subcategoryNames) {
                selectedCategory = subcategories.stream().filter(sc -> subcategoryName.equals(sc.getText()))
                        .findFirst().orElseThrow(RuntimeException::new);
                hover(selectedCategory);
            }
        }
        selectedCategory.click();
        return new StorePage(getDriver());
    }

    public CartPage goToCart() {
        getDriver().get(URL);
        return new CartPage(getDriver());
    }
}
