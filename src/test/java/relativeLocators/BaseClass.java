package relativeLocators;

import org.junit.Assert;
import org.junit.runners.JUnit4;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;

public class BaseClass {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseClass.class);

    protected WebDriver driver;
    final static String CURRENT_DIR = System.getProperty("user.dir");
    final static String logsPath = "/src/test/java/relativeLocators/logs/TestLatest.log";

    protected WebDriver startDriver(String browserName)  {

        switch (browserName)
        {
            case "chrome":

            {
                return setChromeDriver(new ChromeOptions(),new ChromeDriverService.Builder());
            }
            case "firefox":

            {
                return setFirefoxDriver(new FirefoxOptions(),new GeckoDriverService.Builder());
            }
            case "chrome_withAddin":
            {
                return setChromeDriverWithAddin(new ChromeOptions(),new ChromeDriverService.Builder());
            }
            default:
                return new ChromeDriver();
    }
    }

    private WebDriver setChromeDriverWithAddin(ChromeOptions chromeOptions, ChromeDriverService.Builder builder) {
//        Capabilities capabilities= readJsonCapability("")
return new ChromeDriver();
    }

    private WebDriver setFirefoxDriver(FirefoxOptions firefoxOptions, GeckoDriverService.Builder geckoDriverServiceBuilder) {
        File logFilePath = new File(getPath(logsPath));
        LOGGER.info("currentPath = "+ logFilePath);
        geckoDriverServiceBuilder.withLogFile(new File(String.valueOf(logFilePath)));
        return new FirefoxDriver(geckoDriverServiceBuilder.build(),firefoxOptions);
    }

    private String getPath(String pathof) {
        return CURRENT_DIR+pathof;
    }

    private ChromeDriver setChromeDriver(ChromeOptions chromeOptions, ChromeDriverService.Builder builder) {
        File logFilePath = new File(getPath(logsPath));
        LOGGER.info("currentPath = "+ logFilePath);
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");
        builder.withLogFile(new File(String.valueOf(logFilePath)));
        return new ChromeDriver(builder.build(),chromeOptions);
    }

    protected void explicitWait(WebElement elm, ELEMENTSTATE condition, int second)
    {
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(second));
        switch (condition)
        {
            case VISIBLE:
                wait.until(ExpectedConditions.visibilityOf(elm));
                break;
            case CLICKABLE:
                wait.until(ExpectedConditions.elementToBeClickable(elm));
                break;
            case DISPLAYED_NOT_CLICKABLE:
                wait.until(ExpectedConditions.visibilityOf(elm));
                wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(elm)));
                break;
            default:
        LOGGER.info("Add the condtion");
    }
    }

    protected void quitBrowser()
    {
        driver.close();
        driver.quit();
    }



}
