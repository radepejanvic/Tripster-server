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
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class UpdateAccommodationPage {
    @FindBy(xpath = "//h1[2]")
    WebElement title;
    @FindBy(xpath = "//mat-card[1]")
    WebElement startDate;
    @FindBy(xpath = "//mat-card[2]")
    WebElement endDate;
    @FindBy(id = "standard")
    WebElement standard;
    @FindBy(css = "#add-price-list>button")
    WebElement add;
    @FindBy(css = "app-price-list>#add-new>button.btn")
    WebElement submit;
    @FindBy(css = "#table-scroll.active-pricelists>table>tr:not(#table-header)")
    List<WebElement> list;
    @FindBy(css = "#add-price-list>#policy>option[value='false']")
    WebElement disable;

    @FindBy(css = "#add-price-list>button")
    WebElement disableButton;
    private WebDriver driver;
    public UpdateAccommodationPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {

        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title,"We`ll start with some base info."));

        return isOpened;
    }
    public boolean scrollToBottom() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;


        // Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight-1000)");
        AtomicBoolean status = new AtomicBoolean(false);
        // Wait until the scroll position reaches the bottom
        wait.until(webDriver -> {
            Number innerHeight = (Number) js.executeScript("return window.innerHeight;");
            Number scrollY = (Number) js.executeScript("return window.scrollY;");
            Number bodyScrollHeight = (Number) js.executeScript("return document.body.scrollHeight;");

            double innerHeightValue = innerHeight.doubleValue();
            double scrollYValue = scrollY.doubleValue();
            double bodyScrollHeightValue = bodyScrollHeight.doubleValue();

            System.out.println("Checking scroll position - Inner height: " + innerHeightValue + ", Scroll Y: " + scrollYValue + ", Body scroll height: " + bodyScrollHeightValue);

            double targetScrollPosition = bodyScrollHeightValue - innerHeightValue - 1000;
            if (scrollYValue >= targetScrollPosition) {
                status.set(true);
            }else {
                status.set(false);
            }

            return scrollYValue >= targetScrollPosition;
        });
        return status.get();
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
    public void submit(){
        WebElement  wait = new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(submit));
        wait.click();
    }

    public boolean isValidNumberOfDateInterval(int number){
        boolean until = (new WebDriverWait(driver, Duration.ofSeconds(10))).until(driver1 -> {return  list.size() == number;});
        return until;
    }

    public void disableDays(String start,String end){
        String startD  = "button[aria-label='"+start+"']";
        String eD  = "button[aria-label='"+end+"']";
        startDate.findElement(By.cssSelector(startD)).click();
        endDate.findElement(By.cssSelector(eD)).click();
        disable.click();
        WebElement  wait = new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(disableButton));
        wait.click();
    }
}
