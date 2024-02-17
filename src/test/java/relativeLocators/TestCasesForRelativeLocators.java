package relativeLocators;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestCasesForRelativeLocators extends BaseClass {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestCasesForRelativeLocators.class);
  @Test
  public void testRelativeLocators() {
    driver = startDriver("chrome");
    driver.navigate().to("https://phptravels.com/");
    WebElement loginButton =
        driver.findElement(By.xpath("//*[strong[text()=\"Login\"]//parent::a]"));
    explicitWait(loginButton, ELEMENTSTATE.VISIBLE, 10);

    LOGGER.info("For Right_Of");
    WebElement getNowButton =
        driver.findElement(
            RelativeLocator.with(By.xpath("//*[strong[\"Signup\"]]"))
                    .toRightOf(loginButton));
      explicitWait(getNowButton,ELEMENTSTATE.VISIBLE,10);

      LOGGER.info("For Left OF");
      WebElement company =
              driver.findElement(
                      RelativeLocator.with(By.cssSelector("a>span"))
                              .toLeftOf(loginButton));
      explicitWait(company,ELEMENTSTATE.VISIBLE,10);

      LOGGER.info("For near default value is 50 pixels");
      WebElement feature=driver.findElement(RelativeLocator
              .with(By.cssSelector("a>span"))
      .near(company));
    Assert.assertEquals(feature.getText(),"Features");

      driver.close();

  }

  @Test
  public void test_two() {
    Map<String, Object> deviceMetrics = new HashMap<>();

    deviceMetrics.put("width", 360);

    deviceMetrics.put("height", 640);

    deviceMetrics.put("pixelRatio", 3.0);

    Map<String, Object> mobileEmulation = new HashMap<>();

    mobileEmulation.put("deviceMetrics", deviceMetrics);

    mobileEmulation.put(
        "userAgent",
        "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

    Map<String, Object> clientHints = new HashMap<>();

    clientHints.put("platform", "Android");

    clientHints.put("mobile", true);

    mobileEmulation.put("clientHints", clientHints);

    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
    WebDriver driver = new ChromeDriver(chromeOptions);
    driver.navigate().to("https://phptravels.com/");
    driver.getTitle();
  }
}
