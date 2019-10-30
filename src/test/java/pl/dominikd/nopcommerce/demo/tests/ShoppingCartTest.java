package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.ProductInShoppingCart;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.CategoryPage;
import pl.dominikd.nopcommerce.demo.pages.MainPage;
import pl.dominikd.nopcommerce.demo.pages.ShoppingCartPage;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public class ShoppingCartTest extends TestBase {
    private ShoppingCartPage shoppingCartPage;
    private List<ProductInShoppingCart> products;

    public ShoppingCartTest() throws JAXBException, FileNotFoundException {
        super();
    }
//    removing items
//    gift wrapping, discount code, gift card, estimate shipping forms
//    terms of service popup

    @BeforeMethod
    public void initAddToCart() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openCellPhones();
        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.getProductsList().get(0).addToCart(driver);
        categoryPage.closeNotification();
        categoryPage.openShoppingCart();
        refreshPageObjects();
        shoppingCartPage.selfCheck();
    }

    private void refreshPageObjects() {
        shoppingCartPage = new ShoppingCartPage(driver);
        products = shoppingCartPage.getProductList();
    }

    private void updateCart() {
        shoppingCartPage.updateCart();
        refreshPageObjects();
    }

    @Test
    public void testChangeQuantity() {
        ProductInShoppingCart product = products.get(0);
        int testQuantity = 3;
        product.setQuantity(testQuantity, driver);
        updateCart();
        product = products.get(0);
        Assert.assertEquals(product.getTotal(), product.getPrice() * testQuantity, "Total price doesn't match the entered quantity");
    }

    @Test
    public void testRemoveFromCart() {
        ProductInShoppingCart product = products.get(0);
        product.setRemoveFromCart(true);
        updateCart();
        Assert.assertEquals(products.size(), 0, "Expected shopping cart to be empty");
    }
}
