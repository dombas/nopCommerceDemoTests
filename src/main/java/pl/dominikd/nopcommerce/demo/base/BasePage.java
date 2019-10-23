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

    @FindBy(css = "a.ico-register")
    private WebElement registerLink;

    @FindBy(css = "a.ico-login")
    private WebElement loginLink;

    @FindBy(css = "a.ico-logout")
    private WebElement logoutLink;

    @FindBy(id = "newsletter-subscribe-button")
    private WebElement subscribeButton;

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
        waitForNotificationBarToDisappear();
        shoppingCartLink.click();
    }

    public void openRegisterForm() {
        waitForNotificationBarToDisappear();
        registerLink.click();
    }

    public void openLoginForm() {
        waitForNotificationBarToDisappear();
        loginLink.click();
    }

    private void waitForNotificationBarToDisappear() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOf(notificationBar));
        } catch (NoSuchElementException ignored) {
        }
    }

    public void closeNotification() {
        barNotificationCloseSpan.click();
    }

    public boolean isLoggedIn() {
        try {
            return logoutLink.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
