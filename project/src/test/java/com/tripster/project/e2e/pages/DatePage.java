package com.tripster.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DatePage {

    @FindBy(xpath = "//h1[1]")
    WebElement title;

    @FindBy(xpath = "//mat-card[1]")
    WebElement startDate;
    @FindBy(xpath = "//mat-card[2]")
    WebElement endDate;
    @FindBy(id = "standard")
    WebElement standard;

    @FindBy(css = ".temp")
    WebElement table;
    @FindBy(css = "#add-price-list>button")
    WebElement add;
    @FindBy(css = "#table-container>button")
    WebElement remove;

    @FindBy(css = "button[type='submit']")
    WebElement submit;

    @FindBy(id = "reset")
    WebElement finish;
    private WebDriver driver;
    public DatePage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {

        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, "Now let`s define price lists for your unit."));

        return isOpened;
    }

    public void addDate(String start,String end,int price){
        String startD  = "button[aria-label='"+start+"']";
        String eD  = "button[aria-label='"+end+"']";
        startDate.findElement(By.cssSelector(startD)).click();
        endDate.findElement(By.cssSelector(eD)).click();
        standard.clear();
        standard.sendKeys(String.valueOf(price));
        add.click();
    }

    public void remove(int element){
        String value = String.valueOf(element+1);
        table.findElement(By.xpath("tr["+value+"]")).click();
        remove.click();
    }
    public void submit(){
        submit.click();
    }

    public void finish(){
        finish.click();
    }
}
