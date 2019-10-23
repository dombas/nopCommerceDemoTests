package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.MainPage;
import pl.dominikd.nopcommerce.demo.pages.ProductPage;
import pl.dominikd.nopcommerce.demo.pages.SearchResultPage;
import pl.dominikd.nopcommerce.demo.pages.ShoppingCartPage;
import pl.dominikd.utils.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public class NopcommerceDemoTest extends TestBase {
    private final String testSearchQuery = "htc";

    public NopcommerceDemoTest() throws JAXBException, FileNotFoundException {
        super();
    }

    @Test
    public void testTitle() {
        final String correctPageTitle = "nopCommerce demo store";

        MainPage mainPage = new MainPage(driver);

        Assert.assertEquals(mainPage.getTitle(), correctPageTitle, "page title is not \"nopCommerce demo store\"");
    }

    @Test
    public void testCurrencyEuro() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        mainPage.selectCurrencyEuro();
        String priceText = mainPage.getFirstPriceText();

        Assert.assertTrue(priceText.contains("\u20AC"), "first price doesn't contain euro sign");

        //Assert.assertTrue(priceText.contains("\u0402")); //character used currently
    }

    @Test
    public void testCurrencyDollar() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);

        mainPage.selectCurrencyDollars();

        String priceText = mainPage.getFirstPriceText();

        Assert.assertTrue(priceText.contains("\u0024"), "first price doesn't contain dollar sign");
    }

    @Test
    public void testSearch() {
        MainPage mainPage = new MainPage(driver);
        mainPage.sendKeysToSearchInput(testSearchQuery);
        mainPage.clickSearchSubmitButton();

        SearchResultPage searchResultPage = new SearchResultPage(driver);
        Assert.assertNotEquals(searchResultPage.getSearchResultCount(), 0, "search results are empty");

        List<String> productTitles = searchResultPage.getSearchResultProductTitleTexts();
        for (String productTitle : productTitles) {
            Assert.assertTrue(StringUtils.containsIgnoreCase(productTitle, testSearchQuery),
                    "one of the search results doesn't contain the search query (\"testSearchQuery\")");
        }
    }

    @Test
    public void testDropDownMenu() {
// FIXME: 22.10.2019 
    }

    @Test
    public void testOpenProductPage() {
        String nameFragment = "htc";
        MainPage mainPage = new MainPage(driver);
        mainPage.openProductPageByName(nameFragment);

        ProductPage productPage = new ProductPage(driver);
        String productName = productPage.getProductName();
        Assert.assertTrue(StringUtils.containsIgnoreCase(productName, nameFragment));
    }

    @Test
    public void testAddToCart() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        // go to HTC product page
        String nameFragment = "htc";
        mainPage.openProductPageByName(nameFragment);
        // add HTC to cart
        ProductPage productPage = new ProductPage(driver);
        productPage.addToCart();
        productPage.closeNotification();
        // enter shopping cart page
        productPage.openShoppingCart();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        // check if HTC is in cart
        List<String> productNames = shoppingCartPage.getProductNames();
        Assert.assertTrue(StringUtils.listContainsIgnoreCase(productNames, nameFragment),
                "Shopping cart does not contain added product");
    }


}
