package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.CategoryPage;
import pl.dominikd.nopcommerce.demo.pages.MainPage;
import pl.dominikd.nopcommerce.demo.pages.ShoppingCartPage;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class ProductsTest extends TestBase {

    public ProductsTest() throws JAXBException, FileNotFoundException {
        super();
    }

    @Test
    public void testOpenCellPhones() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openCellPhones();
        CategoryPage categoryPage = new CategoryPage(driver);
        Assert.assertEquals(categoryPage.getNumberOfProducts(), 3, "Expected 3 products");
    }

    @Test
    public void testAddToCart() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openCellPhones();
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.addAllToCart();
        categoryPage.openShoppingCart();
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        Assert.assertEquals(shoppingCartPage.getProductList().size(), 3, "Expected 3 products in cart");
    }
}
