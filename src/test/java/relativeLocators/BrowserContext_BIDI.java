package relativeLocators;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContextInfo;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;

public class BrowserContext_BIDI {
  private static final Logger LOGGER = LoggerFactory.getLogger(BrowserContext_BIDI.class);

  WebDriver driver;

  @Test
  public void testRelativeLocators() {
    driver = startDriver_BID("chrome");

    BrowsingContext browserContext = new BrowsingContext(driver,driver.getWindowHandle());
    browserContext.navigate("https://phptravels.com/");
    WebElement loginButton =
            driver.findElement(By.xpath("//*[strong[text()=\"Login\"]//parent::a]"));
    loginButton.click();
    List<BrowsingContextInfo> contexts = browserContext.getTopLevelContexts();
    contexts.forEach(e->System.out.println(e.getUrl()));

    driver.switchTo().window(browserContext.getTopLevelContexts().get(1).getId());
    Assert.assertEquals("Child window title","Login - PHPTRAVELS",driver.getTitle());
    browserContext.close();
    LOGGER.info("");

  }

  private WebDriver startDriver_BID(String chrome) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.setBinary("src/test/driver/chrome-win64/chrome.exe");
    options.setCapability("webSocketUrl", true);
    return new ChromeDriver(options);
  }
}
