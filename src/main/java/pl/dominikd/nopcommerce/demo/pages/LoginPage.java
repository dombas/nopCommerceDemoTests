package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.dominikd.nopcommerce.demo.base.BasePage;

public class LoginPage extends BasePage {

    @FindBy(id = "Email")
    private WebElement emailInput;

    @FindBy(id = "Password")
    private WebElement passwordInput;

    @FindBy(css = "input.login-button")
    private WebElement loginButton;

    @FindBy(id = "Email-error")
    private WebElement emailError;

    @FindBy(css = "div.message-error")
    private WebElement loginError;

    public LoginPage(WebDriver driver) {
        super(driver);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(emailInput));
    }

    public void enterEmail(String email) {
        emailInput.click();
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void submitLogin() {
        loginButton.submit();
    }

    public String getEmailError() {
        return emailError.getText();
    }

    public String getLoginError() {
        return loginError.getText();
    }

}
