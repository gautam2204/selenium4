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
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    String val = (String) executor.executeScript("return document.readyState");
    System.out.println(val);
    if (val.isEmpty()) {
      Thread.sleep(1000);
    }

    executor.executeScript("var element_found;");
    executor.executeScript(
        "document.addEventListener('contextmenu', function (event) {\n"
            + "        element_found = event.target;\n"
            + "        console.log(\"element_found\")\n"
            + "        \n"
            + "    });");

    System.out.println("wait");

    String id = driver.getWindowHandle();
    try (Script script = new Script(id, driver)) {

      EvaluateResult result =
          script.callFunctionInBrowsingContext(
              id,
              "function getElmsAttributes() {\n"
                  + "    var listOfAttibutesInElmentFound = element_found.attributes;\n"
                  + "    var result = \"\";\n"
                  + "for (let index = 0; index < listOfAttibutesInElmentFound.length; index++) {\n"
                  + "    var attributeName= listOfAttibutesInElmentFound[index]\n"
                  + "     result += attributeName.nodeName + \": \" + attributeName.value + \"\\n\";\n"
                  + "}\n"
                  + "    return result\n"
                  + "}",
              true,
              Optional.empty(),
              Optional.empty(),
              Optional.empty());

      EvaluateResultSuccess successResult = (EvaluateResultSuccess) result;
    }
  }
}