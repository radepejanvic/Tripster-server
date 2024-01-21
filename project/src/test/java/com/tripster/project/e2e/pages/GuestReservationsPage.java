package com.tripster.project.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GuestReservationsPage {

    private WebDriver driver;

    @FindBy(xpath = "//p[text()='Search results']")
    WebElement title;

    WebElement selectedReservation;

    public GuestReservationsPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.visibilityOf(title));

        return title.getText().equals("Search results");
    }

    public void cancelReservation() {
        WebElement cancel = selectedReservation.findElement(By.name("cancel-button"));
        cancel.click();
    }

    public void findReservation(String title, String timeStamp) {
        String query = String.format(
                "//app-guest-reservation-card/*//h5[text()='%s']/../p[text()='%s']/../../../../..", title, timeStamp);
        selectedReservation = driver.findElement(By.xpath(query));
    }

    public boolean compareStatus(String expected, String title, String timeStamp) {
        driver.navigate().refresh();
        findReservation(title, timeStamp);
        WebElement status = selectedReservation.findElement(By.name("status"));
        return status.getText().equals(expected);
    }






}
