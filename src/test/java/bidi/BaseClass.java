package bidi;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class BaseClass {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseClass.class);

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected File driverPath;
    protected File browserPath;


    protected ChromeDriver startChromedriver() throws IOException {
        return setWebDriver(new ChromeOptions(),new ChromeDriverService.Builder());
    }

    private ChromeDriver setWebDriver(ChromeOptions chromeOptions, ChromeDriverService.Builder builder) {
        String currentPath = System.getProperty("user.dir");
//        System.out.println("currentPath = "+ currentPath);
        LOGGER.info("currentPath = "+ currentPath);
        chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");
        builder.withLogFile(new File(currentPath+"/TestLatest.log"));
        return new ChromeDriver(builder.build(),chromeOptions);

    }

    protected void quitBrowser()
    {
        driver.close();
        driver.quit();
    }
}
