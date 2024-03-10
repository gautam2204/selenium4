package bidi;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.bidi.LogInspector;
import org.openqa.selenium.bidi.log.ConsoleLogEntry;
import org.openqa.selenium.bidi.log.JavascriptLogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.*;

public class Log extends BaseClass {

  @Test
  public void readLogsFromConsole() throws IOException {
    driver = startChromedriver();
    CopyOnWriteArrayList<ConsoleLogEntry> logs = new CopyOnWriteArrayList<>();

    try (LogInspector logInspector = new LogInspector(driver)) {
      logInspector.onConsoleEntry(logs::add);
    }

    driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
    driver.findElement(By.id("consoleLog")).click();

    new WebDriverWait(driver, Duration.ofSeconds(5)).until(_d -> !logs.isEmpty());
    Assert.assertEquals("Hello, world!", logs.get(0).getText());

//      quitBrowser();

  }
}
