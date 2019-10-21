package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class BasePage extends AbstractPage {

    @FindBy(id = "customerCurrency")
    WebElement wannabeCurrencySelect;
    Select currencySelect;

    @FindBy(id = "small-searchterms")
    WebElement searchInput;

    @FindBy(className = "search-box-button")
    WebElement searchButton;

    @FindBy(className = "cart-label")
    WebElement cartLink;

    public BasePage(WebDriver driver) {
        super(driver);
        this.currencySelect = new Select(wannabeCurrencySelect);
    }

    public void selectCurrencyDollars() {
        currencySelect.selectByIndex(0);
    }

    public void selectCurrencyEuro() {
        currencySelect.selectByIndex(1);
    }

    public void sendKeysToSearchInput(String text) {
        searchInput.sendKeys(text);
    }

    public void clickSearchSubmitButton() {
        searchButton.click();
    }


}
