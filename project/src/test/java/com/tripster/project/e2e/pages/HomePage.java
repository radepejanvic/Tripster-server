package com.tripster.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private static final String SITE="http://localhost:4200/home";
    private WebDriver driver;

    @FindBy(css = "input[formcontrolname='username']")
    WebElement username;

    @FindBy(css = "input[formcontrolname='password']")
    WebElement password;

    @FindBy(css = "button.btn-secondary")
    WebElement loginButton;

    @FindBy(css = "[ng-reflect-router-link='/addAccommodation']")
    WebElement addAccommodationButton;
    public HomePage(WebDriver driver){
        this.driver = driver;
        driver.get(SITE);
        PageFactory.initElements(driver,this);
    }

    public void login(String username,String password){
        this.username.clear();
        this.username.sendKeys(username);
        this.password.clear();
        this.password.sendKeys(password);
        loginButton.click();
    }

    public void openAddAccommodationPage(){
        addAccommodationButton.click();
    }
}
