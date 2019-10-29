package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// TODO: 29.10.2019 Make this and ProductInShoppingCart descendants of a class Product
public class ProductInResults {
    private WebElement productItemDiv;
    private WebElement detailsDiv;
    private WebElement nameElement;
    private WebElement priceElement;
    private WebElement addToCartButton;
    private WebElement addToCompareButton;
    private WebElement addToWishListButton;

    public ProductInResults(WebElement productItemDiv) {
        this.productItemDiv = productItemDiv;
        this.detailsDiv = productItemDiv.findElement(By.className("details"));
        this.nameElement = detailsDiv.findElement(By.className("product-title"));
        this.priceElement = detailsDiv.findElement(By.className("actual-price"));
        this.addToCartButton = detailsDiv.findElement(By.xpath(".//input[contains(@class,'add-to-cart-button')]"));
        this.addToCompareButton = detailsDiv.findElement(By.xpath(".//input[contains(@class,'add-to-compare-list-button')]"));
        this.addToWishListButton = detailsDiv.findElement(By.xpath(".//input[contains(@class,'add-to-wishlist-button')]"));
    }

    public String getName() {
        return nameElement.getText();
    }

    public void addToCart(WebDriver driver) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
    }
}
