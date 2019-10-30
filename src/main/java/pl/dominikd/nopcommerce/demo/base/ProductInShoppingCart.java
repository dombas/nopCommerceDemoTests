package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.dominikd.utils.Commons;

public class ProductInShoppingCart {
    private WebElement removeCheckbox;
    private WebElement skuElement;
    private WebElement nameElement;
    private WebElement priceElement;
    private WebElement quantityInput;
    private WebElement totalElement;

    public ProductInShoppingCart(WebElement productTableRow) {
        removeCheckbox = productTableRow.findElement(By.name("removefromcart"));
        skuElement = productTableRow.findElement(By.className("sku-number"));
        nameElement = productTableRow.findElement(By.className("product-name"));
        priceElement = productTableRow.findElement(By.className("product-unit-price"));
        quantityInput = productTableRow.findElement(By.className("qty-input"));
        totalElement = productTableRow.findElement(By.className("product-subtotal"));
    }

    public String getName() {
        return nameElement.getText();
    }

    private int trimCurrencyAndCents(String priceText) {
        return Integer.parseInt(priceText.substring(1, priceText.length() - 3));
    }

    public int getTotal() {
        return trimCurrencyAndCents(totalElement.getText());
    }

    public int getPrice() {
        return trimCurrencyAndCents(priceElement.getText());
    }

    public void setQuantity(int quantity, WebDriver driver) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(quantityInput));
        quantityInput.clear();
        quantityInput.sendKeys(Integer.toString(quantity));
    }

    public void setRemoveFromCart(boolean remove) {
        Commons.setCheckbox(removeCheckbox, remove);
    }
}
