package pages;


import com.automation.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import utils.ElementHelper;

import java.time.Duration;

public class Page extends BasePage {

    private static final long WAIT_TIMEOUT_SECONDS = 20;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Page.class);

    public void navigateToPage(String url) {
        driver.get(url);
    }

    public void clickElement(String key) {
        click(key);
        logger.info(key + " elementine başarıyla tıklandı.");

    }

    public void enterText(String key, String text) {

        sendKeys(key, text);
    }

    public boolean isElementVisible(String key) {
        logger.info(key + " elementine başarıyla goruldu.");
        return isElementDisplayed(key);
    }

    public void waitForElementToBeClickable(String key) {
        By by = ElementHelper.getElementBy(key);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(by));
        logger.info(key + " elementine başarıyla goruldu.");

    }
}