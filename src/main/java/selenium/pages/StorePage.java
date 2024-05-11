package selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;
import java.util.List;
import java.util.stream.IntStream;

public class StorePage extends BasePage {

    @FindBy(className = "product-item-link")
    private List<WebElement> products;

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public ProductPage goToProductPage(String productName) {
        return productName.equals("Random")
                ? selectRandomProduct() :
                selectProduct(productName);
    }

    public NavBar getNavBar() {
        return new NavBar(getDriver());
    }

    public ProductPage selectRandomProduct() {
        products.get(new Random().nextInt(products.size() - 1)).click();
        return new ProductPage(getDriver());
    }

    public ProductPage selectProduct(String productName) {
        products.stream()
                .filter(p -> productName.equals(p.getText()))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .click();
        return new ProductPage(getDriver());
    }

    public LandingPage addProductsToCart(int quantity) {
        IntStream.range(0, quantity).forEach(i -> selectRandomProduct().addProductToCart().goBackToCategory());
        return new LandingPage(getDriver());
    }
}
