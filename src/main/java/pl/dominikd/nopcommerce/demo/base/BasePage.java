package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage extends AbstractPage {

    @FindBy(id = "customerCurrency")
    private WebElement wannabeCurrencySelect;
    private Select currencySelect;

    @FindBy(id = "small-searchterms")
    private WebElement searchInput;

    @FindBy(className = "search-box-button")
    private WebElement searchButton;

    @FindBy(id = "bar-notification")
    private WebElement notificationBar;

    @FindBy(css = ".bar-notification>.close")
    private WebElement barNotificationCloseSpan;

    @FindBy(css = "a.ico-cart")
    private WebElement shoppingCartLink;

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

    public void openShoppingCart() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOf(notificationBar));
        } catch (NoSuchElementException e) {
        }
        shoppingCartLink.click();
    }

    public void closeNotification() {
        barNotificationCloseSpan.click();
    }

}
