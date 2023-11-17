import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HandleShadowDom {
    WebDriver driver;

    @Test
    public void test_shadowDom()
    {
        System.setProperty("webdriver.chrome.driver","src/test/driver/chromedriver-win64/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.setBinary("src/test/driver/chrome-win64/chrome.exe");
        options.addArguments("--remote-allow-origins=*");
        driver=new ChromeDriver(options);
        driver.get("http://watir.com/examples/shadow_dom.html");
        WebElement someTextHostElm = driver.findElement(By.id("shadow_host"));
        JavascriptExecutor executor =  (JavascriptExecutor) driver;
        String val = executor.executeScript("return document.querySelector('div#shadow_host').shadowRoot.querySelector('span#shadow_content>span').textContent;").toString();
        System.out.println(val);
        driver.quit();
    }
}
