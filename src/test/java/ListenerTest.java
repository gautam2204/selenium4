import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

public class ListenerTest  {

  @Test
  public void testEventWebdriver() {
    WebDriverManager.chromedriver().browserVersion("97.0.4692.71").setup();
    ChromeOptions options=new ChromeOptions();
    options.addArguments("--lang=en");
WebDriver driver=new ChromeDriver(options);
    String url = "https://facebook.com";

    WebDriverListener listener = new EventCapture();
    WebDriver decoratedWebDriver = new EventFiringDecorator(listener).decorate(driver);
    decoratedWebDriver.get(url);
    WebElement forgotUserId =
        decoratedWebDriver.findElement(By.xpath("//a[contains(text(),'Forgotten password?')]"));


    forgotUserId.click();

    decoratedWebDriver.quit();

  }
}
