package steps;

import com.automation.base.BasePage;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import pages.Page;


import java.time.Duration;

import static utils.ElementHelper.getElementBy;

public class Steps {
    private Page loginPage = new Page();
    private long startTime;
    private WebDriver driver;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Page.class);

    @Step("Hesap Makinesi Login <url> sayfasına git")
    public void navigateToPage(String url) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        logger.info(url + " adresine gidiliyor");
        logger.info("Login sayfasına gitme süresi: " + elapsedTime + " ms");
        loginPage.navigateToPage(url);

    }

    @Step("<key> elementine tıkla")
    public void clickElement(String key) {
        loginPage.waitForElementToBeClickable(key);
        loginPage.clickElement(key);

    }


    @Step("<key> elementine <text> textini yaz")
    public void enterText(String key, String text) {
        loginPage.enterText(key, text);

    }

    @Step("<key> elementi görünür mü")
    public void verifyElementVisible(String key) {
        BasePage.waitForPageLoad();
        Assert.assertTrue(loginPage.isElementVisible(key));

    }

    @Step("Bekleme <milliseconds> milisaniye")
    public void wait(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }


    }

