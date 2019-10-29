package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

    public void setQuantity(int quantity) {
        quantityInput.clear();
        quantityInput.sendKeys(Integer.toString(quantity));
    }
}
