package pl.dominikd.nopcommerce.demo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.dominikd.nopcommerce.demo.base.TestBase;
import pl.dominikd.nopcommerce.demo.pages.MainPage;
import pl.dominikd.nopcommerce.demo.pages.RegisterPage;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public class RegisterFormTest extends TestBase {

    public RegisterFormTest() throws JAXBException, FileNotFoundException {
        super();
    }

    @Test
    public void testRegisterFormErrorsEmptyForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openRegisterForm();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.submitForm();
        String firstNameError = registerPage.getFirstNameError();
        String lastNameError = registerPage.getLastNameError();
        String emailError = registerPage.getEmailError();
        String passwordError = registerPage.getPasswordError();
        String passwordConfirmError = registerPage.getPasswordConfirmError();
        Assert.assertNotEquals(firstNameError, "", "First name error should not be empty");
        Assert.assertNotEquals(lastNameError, "", "Last name error should not be empty");
        Assert.assertNotEquals(emailError, "", "Email error should not be empty");
        Assert.assertNotEquals(passwordError, "", "Password error should not be empty");
        Assert.assertNotEquals(passwordConfirmError, "", "Password confirmation error should not be empty");
    }

    @Test
    public void testRegisterFormShortPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openRegisterForm();
        RegisterPage registerPage = new RegisterPage(driver);
        String password = "12345";
        registerPage.enterPassword(password);
        registerPage.submitForm();
        String passwordError = registerPage.getPasswordError();
        Assert.assertNotEquals(passwordError, "", "Password error should not be empty");
    }

    @Test
    public void testRegisterFormWrongPasswordConfirmation() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openRegisterForm();
        RegisterPage registerPage = new RegisterPage(driver);
        String password = "123456";
        String passwordConfirmation = "abcdef";
        registerPage.enterPassword(password);
        registerPage.enterPasswordConfirmation(passwordConfirmation);
        registerPage.submitForm();
        String passwordConfirmationError = registerPage.getPasswordConfirmError();
        Assert.assertNotEquals(passwordConfirmationError, "", "Password confirmation error should not be empty");
    }

    @Test
    public void testRegisterFormWrongEmail() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openRegisterForm();
        RegisterPage registerPage = new RegisterPage(driver);
        String email = "12345";
        registerPage.enterEmail(email);
        registerPage.submitForm();
        String emailError = registerPage.getEmailError();
        Assert.assertNotEquals(emailError, "", "Email error should not be empty");
    }
}
