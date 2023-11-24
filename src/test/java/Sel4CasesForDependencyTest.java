import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v117.performance.Performance;
import org.openqa.selenium.devtools.v117.performance.model.Metric;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Sel4CasesForDependencyTest {

  @Test
  public void pwaWebsiteTest() throws InterruptedException, IOException {
    //        WebDriverManager.chromedriver().browserVersion("97.0.4692.71").setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");
    ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();

    ChromeDriver driver = new ChromeDriver(chromeDriverService, chromeOptions);

    DevTools devTools = driver.getDevTools();

    devTools.createSession();
    devTools.send(Performance.enable(Optional.empty()));
    driver.executeCdpCommand("Performance.getMetrics", new HashMap<>());
    // devTools.send(Emulation.emu)
    //        devTools.addListener(event -> event.);
    Command<List<Metric>> met = Performance.getMetrics();

    driver.get("https://www.starbucks.in/");
    Thread.sleep(10000);
    List<Number> val =
        devTools.send(Performance.getMetrics()).stream()
            .map(Metric::getValue)
            .collect(Collectors.toList());
    Map<String, Number> map = new HashMap<>();
    System.out.println(val);

    driver.close();
  }

  @Test
  public void pTest() throws InterruptedException, IOException {
    //        WebDriverManager.chromedriver().browserVersion("97.0.4692.71").setup();
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");
    ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();
    Map<String, String> mobileEmulation = new HashMap<>();

    mobileEmulation.put("deviceName", "Nexus 5");

    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

    WebDriver driver = new ChromeDriver(chromeDriverService, chromeOptions);
    //    ChromeDriver driver = new ChromeDriver(chromeDriverService, chromeOptions);
    driver.navigate().to("https://phptravels.com/");
    System.out.println(driver.getTitle());
  }

  @Test
  public void FirefoxMobile_Test() throws InterruptedException, IOException {
    String user_agent =
        "Mozilla/5.0 (iPhone; U; CPU iPhone OS 3_0 like Mac OS X; en-us) AppleWebKit/528.18 (KHTML, like Gecko) Version/4.0 Mobile/7A341 Safari/528.16";
    WebDriver driver = new FirefoxDriver();
    driver.navigate().to("https://phptravels.com/");
    System.out.println(driver.getTitle());
  }

  @Test
  public void AddDesiredCapabilityToChromeOptionsTest() throws InterruptedException, IOException {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");
    ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();
    Map<String, String> mobileEmulation = new HashMap<>();

    mobileEmulation.put("deviceName", "Nexus 5");

    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("pageLoadStrategy", "eager");

    chromeOptions.setCapability(ChromeOptions.CAPABILITY, desiredCapabilities);

    WebDriver driver = new ChromeDriver(chromeDriverService, chromeOptions);

    driver.navigate().to("https://phptravels.com/");
    System.out.println(driver.getTitle());
  }

  @Test
  public void checkMergeMethodTest() throws InterruptedException, IOException {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");

    ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();

    Map<String, String> mobileEmulation = new HashMap<>();
    mobileEmulation.put("deviceName", "Nexus 5");

    //    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

    DesiredCapabilities desiredCapabilities1 = new DesiredCapabilities();
    desiredCapabilities1.setCapability("setWindowRect", "fullscreen");

    ChromeOptions chromeOptions1 = new ChromeOptions();
    chromeOptions1.setCapability(ChromeOptions.CAPABILITY, desiredCapabilities1);

    ChromeOptions mergeAllOPtionsAndCaps = chromeOptions.merge(chromeOptions1);
    System.out.println(mergeAllOPtionsAndCaps);

    WebDriver driver = new ChromeDriver(chromeDriverService, mergeAllOPtionsAndCaps);
    //    ChromeDriver driver = new ChromeDriver(chromeDriverService, chromeOptions);
    driver.navigate().to("https://phptravels.com/");
    System.out.println(driver.getTitle());
    driver.getWindowHandle();
    BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.WINDOW);
    browsingContext.getId();
  }

  @Test
  public void AddDesiredCapability() throws InterruptedException, IOException {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");
    ChromeDriverService chromeDriverService = ChromeDriverService.createDefaultService();
    Map<String, String> mobileEmulation = new HashMap<>();

    mobileEmulation.put("deviceName", "Nexus 5");
//    HashMap<Object, Object> mobileEmulationCaps = new HashMap<>();
//    mobileEmulationCaps.put("mobileEmulation", mobileEmulation);

    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    desiredCapabilities.setCapability("pageLoadStrategy", "eager");
    desiredCapabilities.setCapability("strictFileInteractability", true);

    chromeOptions.setCapability(ChromeOptions.CAPABILITY,desiredCapabilities);
    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);



    WebDriver driver = new ChromeDriver(chromeDriverService, chromeOptions);

    driver.navigate().to("https://phptravels.com/");
    System.out.println(driver.getTitle());
  }
}
