package bidi;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class BrowserContextsClass extends BaseClass {
  private static Logger LOGGER = LoggerFactory.getLogger(BrowserContextsClass.class);

  private WebDriver chromeDriver;

  @Test
  public void test_BrowsingContext() throws IOException {
    chromeDriver = startChromedriver();
    BrowsingContext browsingContext = new BrowsingContext(chromeDriver, WindowType.WINDOW);
    LOGGER.info("Browsing context id ::"+browsingContext.getId());
      Assert.assertTrue(StringUtils.isNotEmpty(browsingContext.getId()));
  }

}
