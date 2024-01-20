package com.tripster.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AddAccommodationPage {
    private WebDriver driver;

    @FindBy(xpath = "//form/h1[2]")
    WebElement title;

    @FindBy(id = "name")
    WebElement name;
    @FindBy(id = "short-desc")
    WebElement shortDes;

    @FindBy(id = "min-cap")
    WebElement minCap;

    @FindBy(id = "max-cap")
    WebElement maxCap;
    @FindBy(id = "automatic")
    WebElement automaticReservation;
    @FindBy(id = "country")
    WebElement country;
    @FindBy(id = "city")
    WebElement city;
    @FindBy(id = "street")
    WebElement street;
    @FindBy(id = "zip-code")
    WebElement zipCode;
    @FindBy(id = "number")
    WebElement number;
    @FindBy(id = "type")
    WebElement type;
    @FindBy(id = "desc")
    WebElement desc;

    @FindBy(css = "select[formcontrolname='pricePerNight']")
    WebElement pricePerNight;

    @FindBy(css = "select[formcontrolname='cancelDuration']")
    WebElement cancelDuration;

    @FindBy(css = "button.btn[type='submit']")
    WebElement submit;

    public AddAccommodationPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {

        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, "We`ll start with some base info."));

        return isOpened;
    }

    public void setText(String name,String shortDescription,String  minCap,String maxCap){
        this.name.clear();
        this.name.sendKeys(name);
        this.shortDes.clear();
        this.shortDes.sendKeys(shortDescription);
        this.minCap.clear();
        this.minCap.click();
        this.minCap.sendKeys(minCap);
        this.maxCap.clear();
        this.maxCap.click();
        this.maxCap.sendKeys(maxCap);
    }
    public void setRoomTypeAndReservationType(String roomType){

        String type = "option[value='"+roomType+"']";
        this.type.findElement(By.cssSelector(type)).click();
        automaticReservation.click();
    }

    public void setAddressAndDescription(String country,String city,String street,String zip,String number,String desc){
        this.country.clear();
        this.country.sendKeys(country);
        this.city.clear();
        this.city.sendKeys(city);
        this.street.clear();
        this.street.sendKeys(street);
        this.zipCode.clear();
        this.zipCode.sendKeys(zip);
        this.number.clear();
        this.number.sendKeys(number);
        this.desc.clear();
        this.desc.sendKeys(desc);
    }

    public void setPolicyAndCancelDuration(Boolean pricePerNight,int cancelDuration){

        String perNight = "option[value='"+String.valueOf(pricePerNight)+"']";
        String cancel = "option[value='"+String.valueOf(cancelDuration)+"']";
        this.pricePerNight.findElement(By.cssSelector(perNight)).click();
        this.cancelDuration.findElement(By.cssSelector(cancel)).click();
    }

    public void scrollToBottom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Wait until the scroll position reaches the bottom
        wait.until(webDriver -> {
            Number innerHeight = (Number) js.executeScript("return window.innerHeight;");
            Number scrollY = (Number) js.executeScript("return window.scrollY;");
            Number bodyScrollHeight = (Number) js.executeScript("return document.body.scrollHeight;");

            double innerHeightValue = innerHeight.doubleValue();
            double scrollYValue = scrollY.doubleValue();
            double bodyScrollHeightValue = bodyScrollHeight.doubleValue();

            System.out.println("Checking scroll position - Inner height: " + innerHeightValue + ", Scroll Y: " + scrollYValue + ", Body scroll height: " + bodyScrollHeightValue);

            double margin = 10.0; // Adjust as needed
            return (innerHeightValue + scrollYValue + margin) >= bodyScrollHeightValue;
        });}

    public void submit(){
        submit.click();
    }
}
