package bidi;

import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v117.network.Network;
import org.openqa.selenium.devtools.v117.network.model.Request;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

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
        new ChromeDriverService.Builder().withLogFile(new File("./ogLocation.log")).build();

    driver = new ChromeDriver(chromeDriverService, options);

    devtool = ((ChromeDriver) driver).getDevTools();
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
        devtool.createSession();
        devtool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        ArrayList<Request> requests = new ArrayList<>();

    devtool.addListener(
        Network.requestWillBeSent(),
        entry -> {
          requests.add(entry.getRequest());
          System.out.println("Log request "+entry.getRequest().getUrl());
        });

        launchApp("https://auth.tiaa.org/public/authentication/forgotpassword");
        driver.findElement(By.cssSelector("#tiaa-userId")).sendKeys("Hello");
        driver.findElement(By.cssSelector("button#forgotpasswordnextid")).click();
        requests.forEach(e -> System.out.println(e.toString()));
    }

}
