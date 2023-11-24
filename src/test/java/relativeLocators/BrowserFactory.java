package relativeLocators;

import java.util.HashMap;
import java.util.Map;

public enum BrowserFactory {

    CHROME("chrome"),
    FIREFOX("firefox"),
    OPERA("opera"),
    CHROME_WITHADDIN("chrome_withAddin"),
    FIREFOX_WITHADDIN("firefox_withAddin");

    String name;
    public static final Map<String,Object> PROPERTY_STRING_TO_ENUM =  new HashMap<>();

    static {
        for (BrowserFactory browser:BrowserFactory.values())
        {
            PROPERTY_STRING_TO_ENUM.put(browser.getName(),browser);
        }
    }

    public String getName() {
        return name;
    }

    BrowserFactory(String browserName) {
        this.name = browserName;
    }

}


class Main
{
  public static void main(String[] args) {
      System.out.println(BrowserFactory.FIREFOX);
    System.out.println(BrowserFactory.FIREFOX.getName());
    System.out.println(BrowserFactory.PROPERTY_STRING_TO_ENUM.get("chrome"));
//    System.out.println(Browser.FIREFOX);
  }
}