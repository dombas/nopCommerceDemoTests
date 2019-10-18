package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.MainPage;
import pl.dominikd.nopcommerce.demo.pages.SearchResultPage;

import java.util.List;

public class NopcommerceDemoTest extends TestBase {
    private final String testSearchQuery = "htc";

    @Test
    public void testTitle() {
        final String correctPageTitle = "nopCommerce demo store";

        MainPage mainPage = new MainPage(driver);

        Assert.assertEquals(mainPage.getTitle(), correctPageTitle);
    }

    @Test
    public void testCurrencyEuro() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        mainPage.selectCurrencyEuro();
        String priceText = mainPage.getFirstPriceText();

        Assert.assertTrue(priceText.contains("\u20AC"));

        //Assert.assertTrue(priceText.contains("\u0402")); //character used currently
    }

    @Test
    public void testCurrencyDollar() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        mainPage.selectCurrencyDollars();

        String priceText = mainPage.getFirstPriceText();

        Assert.assertTrue(priceText.contains("\u0024"));
    }

    @Test
    public void testSearch() {
        MainPage mainPage = new MainPage(driver);
        mainPage.sendKeysToSearchInput(testSearchQuery);
        mainPage.clickSearchSubmitButton();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Assert.assertNotEquals(searchResultPage.getSearchResultCount(), 0);

        List<String> productTitles = searchResultPage.getSearchResultProductTitleTexts();
        for (String productTitle : productTitles) {
            Assert.assertTrue(productTitle.contains(testSearchQuery));
        }
    }
}
