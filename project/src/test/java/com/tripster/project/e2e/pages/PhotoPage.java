package com.tripster.project.e2e.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PhotoPage {

    @FindBy(xpath = "//h1[1]")
    WebElement title;

    @FindBy(css = "input#drop-zone")
    WebElement photos;
    @FindBy(css = "button[type=submit]")
    WebElement submit;
    private WebDriver driver;
    public PhotoPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {

        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, "Show us the photos of your unit."));

        return isOpened;
    }

    public void setPhotos(List<String> list){
        for (String l:list){
            photos.sendKeys(l);
        }
        submit.click();
    }

}
