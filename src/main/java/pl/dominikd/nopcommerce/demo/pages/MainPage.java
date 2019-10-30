package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.dominikd.nopcommerce.demo.base.BasePage;
import pl.dominikd.utils.Commons;

import java.util.List;

public class MainPage extends BasePage {

    @FindBy(className = "actual-price")
    private List<WebElement> priceSpans;

    @FindBy(className = "product-title")
    private List<WebElement> productLinks;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getFirstPriceText() {
        return priceSpans.get(0).getText();
    }

    public boolean openProductPageByName(String nameFragment) {
        for (WebElement product : productLinks) {
            String innerText = product.getText();
            if (Commons.containsIgnoreCase(innerText, nameFragment)) {
                WebElement innerLink = product.findElement(By.tagName("a"));
                innerLink.click();
                return true;
            }
        }
        return false;
    }
}
