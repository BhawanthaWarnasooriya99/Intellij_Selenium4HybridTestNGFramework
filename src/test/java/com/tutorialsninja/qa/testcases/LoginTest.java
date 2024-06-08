package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTest extends Base {

    LoginPage loginPage;

    public LoginTest() {
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp() {

        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.navigateToLoginPage();

    }

    @AfterMethod
    public void tearDown(){

        driver.quit();

    }

    @Test(priority = 1,dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidCredentials(String email,String password){

        AccountPage accountPage = loginPage.login(email,password);
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation(), "Edit your account information option is not displayed");

    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData() {

        Object[] [] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials(){

        loginPage.login(Utilities.generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));

        String  actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Warning: No match for E-Mail Address and/or Password.");

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword(){

        loginPage.login(Utilities.generateEmailWithTimeStamp(),prop.getProperty("validPassword"));

        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Warning: No match for E-Mail Address and/or Password.");

    }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword(){

        loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));

        String  actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Warning: No match for E-Mail Address and/or Password.");

    }

    @Test(priority = 5)
    public void verifyLoginWithoutProvidingCredentials(){

        loginPage.clickLogin();

        String  actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Warning: No match for E-Mail Address and/or Password.");

    }


}
