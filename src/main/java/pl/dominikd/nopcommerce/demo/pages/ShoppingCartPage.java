package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.dominikd.nopcommerce.demo.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(className = "product-name")
    List<WebElement> productNameElements;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        for (WebElement productElement : productNameElements) {
            productNames.add(productElement.getText());
        }
        return productNames;
    }
}
