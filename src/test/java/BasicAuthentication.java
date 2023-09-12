import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Credentials;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuthentication {

  public static void main(String[] args) {
      WebDriverManager.chromedriver().browserVersion("97.0.4692.71").setup();
      WebDriver driver=new ChromeDriver();

      Predicate<URI> uriPredicate=uri -> uri.getHost().equalsIgnoreCase("authenticationtest.com");
      ((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("user","pass"));

      driver.get("https://authenticationtest.com/HTTPAuth/");
  }
}
