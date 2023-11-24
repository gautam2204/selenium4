package relativeLocators;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class TestCasesForRelativeLocators extends BaseClass{

    @Test
    public void testRelative_By()
    {
        driver = startDriver("chrome");
        driver.navigate().to("https://phptravels.com/");
        WebElement elm = driver.findElement(By.xpath("//h1"));
        explicitWait(elm,ELEMENTSTATE.VISIBLE,10);
        driver.close();

    }

    @Test
    public void test_two()
    {
        Map<String, Object> deviceMetrics = new HashMap<>();

        deviceMetrics.put("width", 360);

        deviceMetrics.put("height", 640);

        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<>();

        mobileEmulation.put("deviceMetrics", deviceMetrics);

        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        Map<String, Object> clientHints = new HashMap<>();

        clientHints.put("platform", "Android");

        clientHints.put("mobile", true);

        mobileEmulation.put("clientHints", clientHints);

        ChromeOptions chromeOptions = new ChromeOptions(); chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.navigate().to("https://phptravels.com/");
        driver.getTitle();
    }
}
