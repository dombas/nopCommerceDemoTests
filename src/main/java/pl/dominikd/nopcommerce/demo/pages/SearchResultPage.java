package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pl.dominikd.nopcommerce.demo.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(className = "product-title")
    List<WebElement> productTitles;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public Integer getSearchResultCount() {
        return productTitles.size();
    }

    public List<String> getSearchResultProductTitleTexts() {
        List<String> titleTexts = new ArrayList<>();
        for (WebElement productTitle : productTitles) {
            titleTexts.add(productTitle.getText());
        }
        return titleTexts;
    }
}
