package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.MainPage;

public class NopcommerceDemoTest extends TestBase {
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
}
