package pl.dominikd.nopcommerce.demo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pl.dominikd.nopcommerce.demo.base.BasePage;

public class RegisterPage extends BasePage {

    @FindBy(id = "gender-male")
    private WebElement genderMaleRadio;

    @FindBy(id = "gender-female")
    private WebElement genderFemaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameInput;
    @FindBy(id = "FirstName-error")
    private WebElement firstNameError;

    @FindBy(id = "LastName")
    private WebElement lastNameInput;
    @FindBy(id = "LastName-error")
    private WebElement lastNameError;

    @FindBy(name = "DateOfBirthDay")
    private WebElement dateOfBirthDaySelectWannabe;
    private Select dateOfBirthDaySelect;

    @FindBy(name = "DateOfBirthMonth")
    private WebElement dateOfBirthMonthSelectWannabe;
    private Select dateOfBirthMonthSelect;

    @FindBy(name = "DateOfBirthYear")
    private WebElement dateOfBirthYearSelectWannabe;
    private Select dateOfBirthYearSelect;

    @FindBy(id = "Email")
    private WebElement emailInput;
    @FindBy(id = "Email-error")
    private WebElement emailError;

    @FindBy(id = "Company")
    private WebElement companyNameInput;

    @FindBy(id = "Newsletter")
    private WebElement newsletterRadio;

    @FindBy(id = "Password")
    private WebElement passwordInput;
    @FindBy(id = "Password-error")
    private WebElement passwordError;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordInput;
    @FindBy(id = "ConfirmPassword-error")
    private WebElement confirmPasswordError;

    @FindBy(id = "register-button")
    private WebElement submitButton;

    public RegisterPage(WebDriver driver) {
        super(driver);
        dateOfBirthDaySelect = new Select(dateOfBirthDaySelectWannabe);
        dateOfBirthMonthSelect = new Select(dateOfBirthMonthSelectWannabe);
        dateOfBirthYearSelect = new Select(dateOfBirthYearSelectWannabe);
    }

    public void enterFormData(String gender, String firstName, String lastName,
                              int dayOfBirth, int monthOfBirth, int yearOfBirth,
                              String email, String companyName, boolean newsletter,
                              String password, String passwordConfirmation) {
        enterGender(gender);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
        enterEmail(email);
        enterCompanyName(companyName);
        enterNewsletter(newsletter);
        enterPassword(password);
        enterPasswordConfirmation(passwordConfirmation);
    }

    public void enterPasswordConfirmation(String passwordConfirmation) {
        confirmPasswordInput.sendKeys(passwordConfirmation);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void enterNewsletter(boolean newsletter) {
        if (newsletter ^ newsletterRadio.isSelected()) {
            newsletterRadio.click();
        }
    }

    public void enterCompanyName(String companyName) {
        companyNameInput.sendKeys(companyName);
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterDateOfBirth(int dayOfBirth, int monthOfBirth, int yearOfBirth) {
        dateOfBirthDaySelect.selectByValue(String.valueOf(dayOfBirth));
        dateOfBirthMonthSelect.selectByValue(String.valueOf(monthOfBirth));
        dateOfBirthYearSelect.selectByValue(String.valueOf(yearOfBirth));
    }

    public void enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
    }

    public void enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
    }

    public void enterGender(String gender) {
        if (gender.equals("male"))
            genderMaleRadio.click();
        else if (gender.equals("female"))
            genderFemaleRadio.click();
    }

    public void submitForm() {
        submitButton.submit();
    }

    public String getFirstNameError() {
        return firstNameError.getText();
    }

    public String getLastNameError() {
        return lastNameError.getText();
    }

    public String getEmailError() {
        return emailError.getText();
    }

    public String getPasswordError() {
        return passwordError.getText();
    }

    public String getPasswordConfirmError() {
        return confirmPasswordError.getText();
    }
}
