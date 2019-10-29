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
    public ShoppingCartTest() throws JAXBException, FileNotFoundException {
        super();
    }
//    changing items quantity
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
    }

    @Test
    public void testChangeQuantity() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.selfCheck();
        List<ProductInShoppingCart> products = shoppingCartPage.getProductList();
        ProductInShoppingCart product = products.get(0);
        int testQuantity = 3;
        product.setQuantity(testQuantity);
        shoppingCartPage.updateCart();

        shoppingCartPage = new ShoppingCartPage(driver);
        products = shoppingCartPage.getProductList();
        product = products.get(0);
        Assert.assertEquals(product.getPrice() * testQuantity, product.getTotal(), "Total price doesn't match the entered quantity");
    }
}
