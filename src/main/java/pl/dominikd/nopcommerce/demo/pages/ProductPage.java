package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.dominikd.nopcommerce.demo.base.BasePage;

public class ProductPage extends BasePage {

    @FindBy(className = "product-name")
    WebElement productNameDiv;

    @FindBy(className = "add-to-cart-button")
    WebElement addToCartButton;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return productNameDiv.getText();
    }

    public void addToCart() {
        addToCartButton.click();
    }
}
