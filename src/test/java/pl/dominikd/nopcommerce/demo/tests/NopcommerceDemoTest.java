package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.MainPage;

public class NopcommerceDemoTest extends TestBase {
    @Test
    public void testTitle() {
        final String correctPageTitle = "nopCommerce demo store";

        MainPage searchPage = new MainPage(driver);

        Assert.assertEquals(searchPage.getTitle(), correctPageTitle);
    }
}
