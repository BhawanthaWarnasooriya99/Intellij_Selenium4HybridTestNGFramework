package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends Base {

    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    public RegisterTest()  {
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){

        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        HomePage homePage = new HomePage(driver);
        registerPage = homePage.navigateToRegisterPage();

    }

    @AfterMethod
    public void tearDown(){

        driver.quit();

    }

    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryFields(){

        accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));

        String actualAccountCreatedText = accountSuccessPage.retrieveAccountSuccessPageHeading();
        Assert.assertEquals(actualAccountCreatedText,dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page  is not displayed.");

    }

    @Test(priority = 2)
    public void verifyRegisteringAccountByProvidingAllFields(){

        accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));

        String actualAccountCreatedText = accountSuccessPage.retrieveAccountSuccessPageHeading();
        Assert.assertEquals(actualAccountCreatedText,dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page  is not displayed.");

    }

    @Test(priority = 3)
    public void verifyRegisteringAccountWithExistingEmailAddress(){

        registerPage.registerWithAllFields(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("validEmail"),dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));

        String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
        Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")), "Warning message of email already registered is not displayed.");

    }

    @Test(priority = 4)
    public void verifyRegisteringAccountWithoutFillingAnyDetails(){

        registerPage.clickOnContinueButton();

        String actualPrivacyPolicyWarningText = registerPage.retrievePrivacyPolicyWarning();
        Assert.assertEquals(actualPrivacyPolicyWarningText,dataProp.getProperty("privacyPolicyWarning"),"Not Displayed Privacy policy warning message");

        String actualFirstNameErrorText =  registerPage.retrieveFirstNameWarning();
        Assert.assertEquals(actualFirstNameErrorText,dataProp.getProperty("firstNameWarning"),"Not Displayed First Name error message");

        String actualLastNameErrorText = registerPage.retrieveLastNameWarning();
        Assert.assertEquals(actualLastNameErrorText,dataProp.getProperty("lastNameWarning"),"Not Displayed Last Name error message");

        String actualEmailErrorText = registerPage.retrieveEmailWarning();
        Assert.assertEquals(actualEmailErrorText,dataProp.getProperty("emailWarning"),"Not Displayed Email error message");

        String actualTelephoneErrorText = registerPage.retrieveTelephoneWarning();
        Assert.assertEquals(actualTelephoneErrorText,dataProp.getProperty("telephoneWarning"),"Not Displayed Telephone error message");

        String actualPasswordErrorText = registerPage.retrievePasswordWarning();
        Assert.assertEquals(actualPasswordErrorText,dataProp.getProperty("passwordWarning"),"Not Displayed Password error message");

    }

}
