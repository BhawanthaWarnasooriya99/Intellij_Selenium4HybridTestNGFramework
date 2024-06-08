package com.tutorialsninja.qa.testcases;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest extends Base {

    SearchPage searchPage;
    HomePage homePage;

    public SearchTest(){
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setUp(){
        //initialize the browser
        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        homePage = new HomePage(driver);

    }

    @AfterMethod
    public void tearDown(){

        driver.quit();

    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct() {

        searchPage = homePage.searchForAProduct(dataProp.getProperty("validProduct"));
        Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(), "Valid product HP not Displayed in search result.");

    }

    @Test(priority = 2)
    public void verifySearchWithInvalidProduct() {

        searchPage = homePage.searchForAProduct(dataProp.getProperty("invalidProduct"));

        String actualSearchMessage = searchPage.retrieveNoProductMessageText();
        Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTextInSearchResults"),"Error message is not displayed");

    }

    @Test(priority = 3)
    public void verifySearchWithoutAnyProduct() {

        searchPage = homePage.clickOnSearchButton();

        String actualSearchMessage = searchPage.retrieveNoProductMessageText();
        Assert.assertEquals(actualSearchMessage,dataProp.getProperty("noProductTextInSearchResults"),"Error message is not displayed");

    }

}
