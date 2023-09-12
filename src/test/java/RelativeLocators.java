import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {

  @Test
  public void RelativeLocatorsTest() throws InterruptedException {
    WebDriverManager.chromedriver().browserVersion("97.0.4692.71").setup();

    WebDriver driver=new ChromeDriver();


    driver.get("https://www.starbucks.in/");

    WebElement nav = driver.findElement(By.id("nav"));

    List<WebElement> lstOfElmAbove = driver.findElements(RelativeLocator.with(By.tagName("span")).above(nav));

    // Thread.sleep(10000);
    lstOfElmAbove.forEach(webElement -> System.out.println("("+webElement.getLocation().x+","+webElement.getLocation().y+")"));

    driver.close();
  }

}
