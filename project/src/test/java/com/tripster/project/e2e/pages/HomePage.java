package com.tripster.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    @FindBy(css = ".dropbtn")
    WebElement hamburger;

    @FindBy(css = "[ng-reflect-router-link='/guest/reservation']")
    WebElement guestReservations;

    @FindBy(css = "[ng-reflect-router-link='/host/reservation']")
    WebElement hostReservations;


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

    public void openGuestReservationsPage() {
        Actions a = new Actions(driver);
        a.moveToElement(hamburger).build().perform();

        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(guestReservations)).click();
    }

    public void openHostReservationsPage() {
        Actions a = new Actions(driver);
        a.moveToElement(hamburger).build().perform();

        (new WebDriverWait(driver, Duration.ofSeconds(10)))
                .until(ExpectedConditions.elementToBeClickable(hostReservations)).click();
    }

}
