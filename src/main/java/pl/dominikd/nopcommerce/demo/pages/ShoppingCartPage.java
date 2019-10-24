package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.dominikd.nopcommerce.demo.base.BasePage;
import pl.dominikd.nopcommerce.demo.base.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(className = "product-name")
    private List<WebElement> productNameElements;

    private List<Product> productListCache;
    private boolean isProductListValid;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        isProductListValid = false;
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        List<Product> productList = getProductList();
        for (Product product : productList) {
            productNames.add(product.getName());
        }
        return productNames;
    }

    public List<Product> getProductList() {
        if (!isProductListValid) {
            populateProductList();
        }
        return productListCache;
    }

    private void populateProductList() {
        productListCache = new ArrayList<>();
        WebElement cartTable = driver.findElement(By.className("cart"));
        WebElement cartTableBody = cartTable.findElement(By.tagName("tbody"));
        List<WebElement> cartTableRows = cartTableBody.findElements(By.tagName("tr"));
        for (WebElement cartTableRow : cartTableRows) {
            Product product = new Product(cartTableRow);
            productListCache.add(product);
        }
        isProductListValid = true;
    }
}
