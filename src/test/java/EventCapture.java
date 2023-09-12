import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.util.Arrays;

public class EventCapture implements WebDriverListener {

  @Override
  public void beforeGet(WebDriver driver, String url) {
    System.out.println("the driver instance is " + driver + "\n and the url is " + url);
  }

  @Override
  public void beforeClick(WebElement element) {
    System.out.println("Element text is = " + element.getText());
  }
  /*WebDriver driver;
  @Override
  public void beforeAnyCall(Object target, Method method, Object[] args) {
  System.out.println("target="+target+"\n method="+method+"args="+args);
      if (method.getName().equalsIgnoreCase("click")) {

          JavascriptExecutor js = (JavascriptExecutor) target;
          js.executeScript(
                  "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                  args);
      }
  }*/

  /*  @Override
  public void afterFindElement(WebDriver driver, By locator, WebElement result) {
      if (result.isDisplayed()) {

          JavascriptExecutor js = (JavascriptExecutor) driver;
          js.executeScript(
                  "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
                  result);
      }

  }*/

  @Override
  public void beforeAnyWebElementCall(WebElement element, Method method, Object[] args) {
    System.out.println(" element=" + element + "\n method=" + method + "\n args=" + args);
    if (method.getName().equalsIgnoreCase("click")) {
      JavascriptExecutor js = (JavascriptExecutor) ((RemoteWebElement) element).getWrappedDriver();
      js.executeScript(
          "arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",
          element);
      System.out.println(System.getProperty("user.dir"));
        File src = element.getScreenshotAs(OutputType.FILE);

      File dest = new File(System.getProperty("user.dir") + "/target/screenshot"+ element.getText().replaceAll(" ","").replaceAll("[@,?.]]","")+"s .png");
        try {
        System.out.println("DIR="+dest);
          if (!dest.exists())
          {
            dest.mkdir();

          }
         // File desti= new File(dest +"/"+ element.getText().replaceAll(" ","")+" .png");
            FileHandler.copy(src,dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  }
}
