package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import pl.dominikd.nopcommerce.demo.base.BasePage;
import pl.dominikd.nopcommerce.demo.base.ProductInShoppingCart;
import pl.dominikd.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {

    @FindBy(className = "update-cart-button")
    private WebElement updateCartButton;

    @FindBy(className = "page-title")
    private WebElement pageTitleElement;

    private List<ProductInShoppingCart> productInShoppingCartListCache;
    private boolean isProductListValid;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        isProductListValid = false;
    }

    public void selfCheck() {
        Assert.assertTrue(StringUtils.containsIgnoreCase(pageTitleElement.getText(), "Shopping cart"), "Expected to find \"shopping cart\" in title");
    }

    public List<String> getProductNames() {
        List<String> productNames = new ArrayList<>();
        List<ProductInShoppingCart> productInShoppingCartList = getProductList();
        for (ProductInShoppingCart productInShoppingCart : productInShoppingCartList) {
            productNames.add(productInShoppingCart.getName());
        }
        return productNames;
    }

    public List<ProductInShoppingCart> getProductList() {
        if (!isProductListValid) {
            populateProductList();
        }
        return productInShoppingCartListCache;
    }

    private void populateProductList() {
        productInShoppingCartListCache = new ArrayList<>();
        WebElement cartTable = driver.findElement(By.className("cart"));
        WebElement cartTableBody = cartTable.findElement(By.tagName("tbody"));
        List<WebElement> cartTableRows = cartTableBody.findElements(By.tagName("tr"));
        for (WebElement cartTableRow : cartTableRows) {
            ProductInShoppingCart productInShoppingCart = new ProductInShoppingCart(cartTableRow);
            productInShoppingCartListCache.add(productInShoppingCart);
        }
        isProductListValid = true;
    }

    public void updateCart() {
        updateCartButton.click();
        isProductListValid = false;
    }
}
