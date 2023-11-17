package cdpRevision;

import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

import java.io.File;
import java.io.IOException;

public class getNetworkCalls {

    private WebDriver driver;
    private DevTools devtool;
    private ChromeOptions options;

    protected File getTempFile(String prefix, String suffix) {
        File logLocation = null;
        try {
            logLocation = File.createTempFile(prefix, suffix);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logLocation.deleteOnExit();

        return logLocation;
    }
  @Before
  public void getDriver() {
//      File logLocation = getTempFile("logsToFile", ".log");
    options = new ChromeOptions();
    options.setBinary("src/test/driver/chrome-win64/chrome.exe");
    ChromeDriverService chromeDriverService =
        new ChromeDriverService.Builder().withLogFile(new File("src/test/java/cdpRevision/logLocation.log")).build();

    driver = new ChromeDriver(chromeDriverService, options);

//    devtool = ((ChromeDriver) driver).getDevTools();
        }

    @After
    public void close()
    {
        this.driver.close();
        this.driver.quit();
    }

    private void launchApp(String s) {
        this.driver.navigate().to(s);
    }

    @Test
    public void test_get_requestsLogs()
    {
        launchApp("https://auth.tiaa.org/public/authentication/forgotpassword");
    }

}
