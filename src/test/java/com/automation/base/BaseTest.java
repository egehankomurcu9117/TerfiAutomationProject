package com.automation.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.thoughtworks.gauge.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {
    protected static WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    private static ExtentReports extent;
    private static ExtentTest test;
    private static ExtentTest scenario;
    private static final String PROJECTROOT = System.getProperty("user.dir");
    private static final String REPORTPATH = PROJECTROOT + "/reports/extent-report.html";
    private static final String SCREENSHOTS_PATH = PROJECTROOT + "/reports/screenshots";

    @BeforeSuite
    public void setupReport() {
        try {
            Files.createDirectories(Paths.get(PROJECTROOT + "/reports"));

            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(REPORTPATH);

            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Hesap Makinesi Uygulaması Test Raporu");
            spark.config().setReportName("Hesap Makinesi Uygulaması Test Raporu");
            spark.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
            spark.config().setEncoding("UTF-8");

            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        } catch (IOException e) {
            log.error("Report dizini oluşturulurken hata: ", e);
        }
    }

    @BeforeSpec
    public void setUp(ExecutionContext context) {
        test = extent.createTest(context.getCurrentSpecification().getName());

    }

    @AfterSpec
    public void tearDown(ExecutionContext context) {
        if (context.getCurrentStep().getIsFailing()) {
            takeScreenshot("Failed_Step_" + System.currentTimeMillis());
            scenario.fail("Adım başarısız: " + context.getCurrentStep().getText());
        }
    }
    @AfterSuite
    public void afterSuite() {
        if (extent != null) {
            try {
                extent.flush();
            } catch (Exception e) {
                log.error("Report oluşturulurken hata: ", e);
            }
        }
    }
    private void takeScreenshot(String name) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver instanceof TakesScreenshot) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                Files.createDirectories(Paths.get(SCREENSHOTS_PATH));

                String fileName = name.replaceAll("[^a-zA-Z0-9.-]", "_") + ".png";
                Path screenshotPath = Paths.get(SCREENSHOTS_PATH, fileName);
                Files.write(screenshotPath, screenshot);

                if (scenario != null) {
                    String relativePath = "screenshots/" + fileName;
                    scenario.addScreenCaptureFromPath(relativePath);
                }
            }
        } catch (IOException e) {
            log.error("Screenshot alınırken hata: ", e);
        }
    }
}