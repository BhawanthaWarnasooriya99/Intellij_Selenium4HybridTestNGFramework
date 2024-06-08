package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    WebDriver driver;

    //Objects
    @FindBy(xpath = "//input[@id='input-firstname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='input-lastname']")
    private WebElement lastNameField;

    @FindBy(name = "email")
    private WebElement emailAddressField;

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "confirm")
    private WebElement passwordConfirmField;

    @FindBy(name = "agree")
    private WebElement privacyPolicyField;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@name='newsletter'][@value='1']")
    private WebElement yesNewsLetterOption;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement privacyPolicyWarning;

    @FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
    private WebElement firstNameWarning;

    @FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
    private WebElement lastNameWarning;

    @FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
    private WebElement emailWarning;

    @FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
    private WebElement telephoneWarning;

    @FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
    private WebElement passwordWarning;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement duplicateEmailAddressWarning;


    public RegisterPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    //Actions

//    public void enterFirstName(String firstNameText) {
//
//        firstNameField.sendKeys(firstNameText);
//
//    }
//
//    public void enterLastName(String lastNameText) {
//
//        lastNameField.sendKeys(lastNameText);
//
//    }
//
//    public void enterEmailAddress(String emailText) {
//
//        emailAddressField.sendKeys(emailText);
//
//    }
//
//    public void enterTelephoneNumber(String telephoneText) {
//
//        telephoneField.sendKeys(telephoneText);
//
//    }
//
//    public void enterPassword(String passwordText) {
//
//        passwordField.sendKeys(passwordText);
//
//    }
//
//    public void enterConfirmPassword(String passwordText) {
//
//        passwordConfirmField.sendKeys(passwordText);
//
//    }
//
//    public void selectPrivacyPolicy() {
//
//        privacyPolicyField.click();
//
//    }

    public AccountSuccessPage clickOnContinueButton() {

        continueButton.click();
        return new AccountSuccessPage(driver);

    }

//    public void clickYesNewsLetterOption() {
//
//        yesNewsLetterOption.click();
//
//    }

    public String retrievePrivacyPolicyWarning() {

        String privacyPolicyWarningText = privacyPolicyWarning.getText();
        return privacyPolicyWarningText;

    }

    public String retrieveFirstNameWarning() {

        String firstNameWarningText = firstNameWarning.getText();
        return firstNameWarningText;

    }

    public String retrieveLastNameWarning() {

        String lastNameWarningText = lastNameWarning.getText();
        return lastNameWarningText;

    }

    public String retrieveEmailWarning() {

        String emailWarningText = emailWarning.getText();
        return emailWarningText;

    }

    public String retrieveTelephoneWarning() {

        String telephoneWarningText = telephoneWarning.getText();
        return telephoneWarningText;

    }

    public String retrievePasswordWarning() {

        String passwordWarningText = passwordWarning.getText();
        return passwordWarningText;

    }

    public String retrieveDuplicateEmailAddressWarning() {

        String duplicateEmailWarningText = duplicateEmailAddressWarning.getText();
        return duplicateEmailWarningText;

    }

    public AccountSuccessPage registerWithMandatoryFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {

        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastNameText);
        emailAddressField.sendKeys(emailText);
        telephoneField.sendKeys(telephoneText);
        passwordField.sendKeys(passwordText);
        passwordConfirmField.sendKeys(passwordText);
        privacyPolicyField.click();
        continueButton.click();
        return new AccountSuccessPage(driver);

    }

    public AccountSuccessPage registerWithAllFields(String firstNameText, String lastNameText, String emailText, String telephoneText, String passwordText) {

        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(lastNameText);
        emailAddressField.sendKeys(emailText);
        telephoneField.sendKeys(telephoneText);
        passwordField.sendKeys(passwordText);
        passwordConfirmField.sendKeys(passwordText);
        yesNewsLetterOption.click();
        privacyPolicyField.click();
        continueButton.click();
        return new AccountSuccessPage(driver);

    }

}
