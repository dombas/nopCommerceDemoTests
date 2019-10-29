package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.dominikd.nopcommerce.demo.base.BasePage;
import pl.dominikd.nopcommerce.demo.base.ProductInResults;

import java.util.ArrayList;
import java.util.List;

public class CategoryPage extends BasePage {

    private List<ProductInResults> productsInResults;

    @FindBy(className = "product-item")
    private List<WebElement> productsTiles;

    public CategoryPage(WebDriver driver) {
        super(driver);
        productsInResults = new ArrayList<>();
        for (WebElement productDetails : productsTiles) {
            productsInResults.add(new ProductInResults(productDetails));
        }
    }

    public int getNumberOfProducts() {
        return productsInResults.size();
    }

    public void addAllToCart() {
        for (ProductInResults productInResults : productsInResults) {
            productInResults.addToCart(driver);
            closeNotification();
            waitForNotificationBarToDisappear();
        }
    }

}
