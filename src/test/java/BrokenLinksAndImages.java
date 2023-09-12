import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksAndImages {
  public static void main(String[] args) {
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    String url = "https://auth.tiaa.org/public/authentication/securelogin";
    driver.get(url);
    List<WebElement> links = driver.findElements(By.tagName("a"));
    List<String> urls = new ArrayList<>();
      long strTime = System.currentTimeMillis();
    for (WebElement e : links) {
         urls.add(e.getAttribute("href"));
      String urlToCheck = e.getAttribute("href");

      findBrokenLinkTraditional(urlToCheck);

     }
      long endTime = System.currentTimeMillis();

      long strTime1 = System.currentTimeMillis();
      urls.parallelStream().forEach(BrokenLinksAndImages::findBrokenLinkTraditional);
      long endTime1 = System.currentTimeMillis();
      System.out.println("Total time intraditional = "+(endTime-strTime));
      System.out.println("Total time parallel = "+(endTime1-strTime1));

      driver.quit();
  }

  public static void findBrokenLinkTraditional(String linkUrl) {
    if (isMalformedUrl(linkUrl)) {
      try {
        URL url = new URL(linkUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.connect();
        System.out.println("Good = "+url.toString());
        if (httpURLConnection.getResponseCode() >= 400) {
          System.out.println(
              "response code ="
                  + httpURLConnection.getResponseCode()
                  + " and Broken Url = "
                  + httpURLConnection.getURL());
        }

      } catch (MalformedURLException e) {

      } catch (IOException e) {
        //throw new RuntimeException("This is IO Exception");
      }
    } else System.out.println("Malformed " + linkUrl);
  }

  private static boolean isMalformedUrl(String linkUrl) {
    try {
      URL url = new URL(linkUrl);
      url.toURI();
      return true;
    } catch (MalformedURLException | URISyntaxException e) {
      return false;
    }
  }
}
