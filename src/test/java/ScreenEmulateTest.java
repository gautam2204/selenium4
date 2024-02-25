import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v121.emulation.Emulation;
//import org.openqa.selenium.devtools.v96.emulation.Emulation;

import java.util.Optional;

public class ScreenEmulateTest {

  @Test
  public void ScreenETest() throws InterruptedException {
    System.setProperty(
        "webdriver.chrome.driver",
        "D:\\Git Project\\selenium4\\src\\test\\resources\\chromedriver.exe");
    ChromeDriver driver = new ChromeDriver();
    DevTools devTools = driver.getDevTools();
    devTools.createSession();

    devTools.send(
        Emulation.setDeviceMetricsOverride(
            600,
            1000,
            60,
            true,Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));
driver.get("https://www.browserstack.com/guide/run-selenium-tests-using-selenium-chromedriver");
Thread.sleep(1000);
  }

}
