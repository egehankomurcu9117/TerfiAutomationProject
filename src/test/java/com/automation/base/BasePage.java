package com.automation.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementHelper;

import java.time.Duration;

public class BasePage {
    protected static WebDriver driver;
    protected static WebDriverWait wait;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    protected static WebElement findElement(String key) {
        try {
            By by = ElementHelper.getElementBy(key);
            return driver.findElement(by);
        } catch (Exception e) {
            throw new RuntimeException("Element bulunamadı: " + key, e);
        }
    }

    protected void waitForElementVisible(String key) {
        try {
            By by = ElementHelper.getElementBy(key);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            throw new RuntimeException("Element görünür olmadı: " + key, e);
        }
    }


    protected static void waitForElementClickable(String key) {
        try {
            By by = ElementHelper.getElementBy(key);
            wait.until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            throw new RuntimeException("Element tıklanabilir olmadı: " + key, e);
        }
    }

    protected static void click(String key) {
        try {
            waitForElementClickable(key);
            findElement(key).click();
        } catch (Exception e) {
            throw new RuntimeException("Elemente tıklanamadı: " + key, e);
        }
    }

    protected void sendKeys(String key, String text) {
        try {
            waitForElementVisible(key);
            WebElement element = findElement(key);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            throw new RuntimeException("Elemente text yazılamadı: " + key, e);
        }
    }

    protected String getText(String key) {
        try {
            waitForElementVisible(key);
            return findElement(key).getText();
        } catch (Exception e) {
            throw new RuntimeException("Elementin texti alınamadı: " + key, e);
        }
    }

    protected boolean isElementDisplayed(String key) {
        try {
            return findElement(key).isDisplayed();
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }


    public static void waitForPageLoad() {
        try {
            wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                    .executeScript("return document.readyState").equals("complete"));
        } catch (Exception e) {
            throw new RuntimeException("Sayfa yüklenemedi", e);
        }
    }



}

