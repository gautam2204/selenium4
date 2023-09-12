import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;

interface SuperInterface {
  void walk();
}

public class DriverClass {
  public ChromiumDriver driver;

  public DriverClass() {
    WebDriverManager.chromedriver().browserVersion("97.0.4692.71").setup();
    driver = new ChromeDriver();
    driver.getDevTools();
  }
}

class Parent implements SuperInterface {
  public void run() {
    System.out.println("Running in parent");
  }

  @Override
  public void walk() {
    System.out.println("I walk in parent");
  }
}

class Child extends Parent {
  public static void main(String[] args) {
    SuperInterface ref = new Parent();
    ref.walk();
    // ref.run();

    Parent p = new Child();
    p.run();
    p.walk();


  }

  public void run() {
    System.out.println("jump in child");
  }

  public void Happy() {
    System.out.println("Happy to be in Child concrete alone");
  }
}
