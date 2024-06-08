package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;

    //Objects
    @FindBy(linkText = "HP LP3065")
    private WebElement validHPProduct;

    @FindBy(xpath = "//p[text()='There is no product that matches the search criteria.']")
    private WebElement noProductMessage;

    public SearchPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    //Actions

    public boolean displayStatusOfHPValidProduct() {

        boolean displayStatus = validHPProduct.isDisplayed();
        return  displayStatus;

    }

    public String retrieveNoProductMessageText() {

        String noProductMessageText = noProductMessage.getText();
        return noProductMessageText;

    }

}
