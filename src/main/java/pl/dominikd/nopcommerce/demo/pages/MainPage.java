package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class MainPage {
    private WebDriver driver;

    @FindBy(id = "customerCurrency")
    WebElement wannabeCurrencySelect;
    Select currencySelect;

    @FindBy(className = "actual-price")
    List<WebElement> priceSpans;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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
}
