package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Product {
    private WebElement removeCheckbox;
    private WebElement skuElement;
    private WebElement nameElement;
    private WebElement priceElement;
    private WebElement quantityInput;
    private WebElement totalElement;

    public Product(WebElement productTableRow) {
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

}
