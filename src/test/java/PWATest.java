import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.performance.Performance;
import org.openqa.selenium.devtools.v96.performance.model.Metric;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PWATest {

    @Test
    public void pwaWebsiteTest() throws InterruptedException {
        WebDriverManager.chromedriver().browserVersion("97.0.4692.71").setup();

        ChromeDriver driver=new ChromeDriver();

        DevTools devTools = driver.getDevTools();

        devTools.createSession();
      //  devTools.send(Performance.enable(Optional.empty()));
        //driver.executeCdpCommand("Performance.getMetrics",);


        Command<List<Metric>> met = Performance.getMetrics();

        driver.get("https://www.starbucks.in/");
        Thread.sleep(10000);
        List<Number> val = devTools.send(Performance.getMetrics()).stream().map(Metric::getValue).collect(Collectors.toList());
        Map<String,Number> map=new HashMap<>();

        driver.close();
    }
}
