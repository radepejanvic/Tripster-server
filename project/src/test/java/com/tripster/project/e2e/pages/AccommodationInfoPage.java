package com.tripster.project.e2e.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;

public class AccommodationInfoPage {
    @FindBy(xpath = "//h1[1]")
    WebElement title;

    @FindBy(css = "#menu>button:last-child")
    WebElement managed;
    private WebDriver driver;
    public AccommodationInfoPage(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened(String name) {

        boolean isOpened = (new WebDriverWait(driver, Duration.ofSeconds(15)))
                .until(ExpectedConditions.textToBePresentInElement(title, name));

        return isOpened;
    }

    public boolean scrollToTop() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, 0)");
        AtomicBoolean status = new AtomicBoolean(false);
        wait.until(webDriver -> {
            Number scrollY = (Number) js.executeScript("return window.scrollY;");

            double scrollYValue = scrollY.doubleValue();

            System.out.println("Checking scroll position - Scroll Y: " + scrollYValue);

            double margin = 10.0;
            if (scrollYValue <= 0 + margin) {
                status.set(true);
            }else {
                status.set(false);
            }

            return scrollYValue <= 0 + margin;
        });
        return status.get();
    }

    public void mangedClick(){
        WebElement wait = new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(managed));
        wait.click();
    }

}
