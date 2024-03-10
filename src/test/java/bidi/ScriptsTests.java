package bidi;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.bidi.Script;
import org.openqa.selenium.bidi.script.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class ScriptsTests extends BaseClass {

  @Test
  public void scriptCalls() throws IOException, InterruptedException {
    driver = startChromedriver();


      driver.get("https://www.selenium.dev/selenium/web/bidi/logEntryAdded.html");
    JavascriptExecutor executor= (JavascriptExecutor)driver;
    String val = (String) executor.executeScript("return document.readyState");
    System.out.println(val);
    if(val.isEmpty())
    {
      Thread.sleep(1000);
    }

    executor.executeScript("var element_found;");
    executor.executeScript("document.addEventListener('contextmenu', function (event) {\n" +
            "        element_found = event.target;\n" +
            "        console.log(\"element_found\")\n" +
            "        \n" +
            "    });");

    System.out.println("wait");
  }
}
