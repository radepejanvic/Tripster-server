package com.tripster.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class HostReservationsPage {

    private WebDriver driver;

    @FindBy(xpath = "//p[text()='Search results']")
    WebElement title;

    WebElement selectedReservation;

    public HostReservationsPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOf(title));

        return title.getText().equals("Search results");
    }

    public void acceptReservation() {
        WebElement accept = selectedReservation.findElement(By.name("accept-button"));
        Actions actions = new Actions(driver);
        actions.moveToElement(accept).click().build().perform();
    }

    public void findReservation(String title, String timeStamp) {
        String query = String.format(
                "//app-host-reservation-card/*//h5[text()='%s']/../p[text()='%s']/../../../../..", title, timeStamp);
        selectedReservation = driver.findElement(By.xpath(query));
    }

    public boolean compareStatus(String expected, String title, String timeStamp) {
        driver.navigate().refresh();
        findReservation(title, timeStamp);
        WebElement status = selectedReservation.findElement(By.name("status"));
        return status.getText().equals(expected);
    }

    public int countByStatus(String status) {
        String query = String.format("//*[@name='status' and text()='%s']", status);
        return driver.findElements(By.xpath(query)).size();
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

}
