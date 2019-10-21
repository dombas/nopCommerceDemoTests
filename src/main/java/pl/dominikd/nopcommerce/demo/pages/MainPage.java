package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pl.dominikd.nopcommerce.demo.base.AbstractPage;

import java.util.List;

public class MainPage extends AbstractPage {
    @FindBy(id = "customerCurrency")
    WebElement wannabeCurrencySelect;
    Select currencySelect;

    @FindBy(className = "actual-price")
    List<WebElement> priceSpans;

    @FindBy(className = "product-title")
    List<WebElement> productLinks;

    @FindBy(id = "small-searchterms")
    WebElement searchInput;

    @FindBy(className = "search-box-button")
    WebElement searchButton;

    public MainPage(WebDriver driver) {
        super(driver);
        this.currencySelect = new Select(wannabeCurrencySelect);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void selectCurrencyDollars() {
        currencySelect.selectByIndex(0);
    }

    public void selectCurrencyEuro() {
        currencySelect.selectByIndex(1);
    }

    public String getFirstPriceText() {
        return priceSpans.get(0).getText();
    }

    public void sendKeysToSearchInput(String text) {
        searchInput.sendKeys(text);
    }

    public void clickSearchSubmitButton() {
        searchButton.click();
    }
}
