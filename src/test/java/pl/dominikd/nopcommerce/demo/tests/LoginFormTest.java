package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.LoginPage;
import pl.dominikd.nopcommerce.demo.pages.MainPage;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class LoginFormTest extends TestBase {

    private final String email = "dombas13@gmail.com";
    private final String password = "T@zvKtuvTd8sdqL";
    private final String wrongPassword = "adofiajsif";

    public LoginFormTest() throws JAXBException, FileNotFoundException {
        super();
    }

    @Test
    public void testSubmitEmptyForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.submitLogin();
        String emailError = loginPage.getEmailError();
        Assert.assertNotEquals(emailError, "", "Email error should not be empty");
    }

    @Test
    public void testSubmitWrongPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(wrongPassword);
        loginPage.submitLogin();
        String loginError = loginPage.getLoginError();
        Assert.assertNotEquals(loginError, "", "Login error should not be empty");
    }

    @Test
    public void testCorrectLogin() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertFalse(loginPage.isLoggedIn(), "Already logged in");
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.submitLogin();
        Assert.assertTrue(loginPage.isLoggedIn(), "Failed to log in");
    }
}
