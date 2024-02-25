package Accessibilty;

import com.github.kklisura.cdt.launch.ChromeLauncher;
import com.github.kklisura.cdt.protocol.commands.Network;
import com.github.kklisura.cdt.protocol.commands.Page;
import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.ChromeService;
import com.github.kklisura.cdt.services.types.ChromeTab;
//import com.github.kklisura.cdt.launch.

import java.util.Map;

/**
 * Log requests example with DevTools java client.
 *
 * <p>The following example will open chrome, create a tab with about:blank url, subscribe to
 * requestWillBeSent event and then navigate to github.com.
 *
 * @author Kenan Klisura
 */
public class ChromeDevToolsExample {


    public static void main(String[] args) {
//        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
//        loggerContext.getLogger("com.github.kklisura.cdt.launch.chrome.output").setLevel(Level.DEBUG);
//        Map<String, String> env = System.getenv();
//        env.put("CHROME_PATH","D:\\Git Project\\selenium4\\src\\test\\driver\\chrome-win64\\chrome.exe");
//    System.setProperty(
//        "CHROME_PATH", "D:\\Git Project\\selenium4\\src\\test\\driver\\chrome-win64\\chrome.exe");

        // Create chrome launcher.
        final ChromeLauncher launcher = new ChromeLauncher();

        // Launch chrome either as headless (true) or regular (false).
        final ChromeService chromeService = launcher.launch(false);

        // Create empty tab ie about:blank.
        final ChromeTab tab = chromeService.getTabs().get(0);

        // Get DevTools service to this tab
        final ChromeDevToolsService devToolsService = chromeService.createDevToolsService(tab);

        // Get individual commands
        final Page page = devToolsService.getPage();
        final Network network = devToolsService.getNetwork();

        // Log requests with onRequestWillBeSent event handler.
        network.onRequestWillBeSent(
                event ->
                        System.out.printf(
                                "request: %s %s%s",
                                event.getRequest().getMethod(),
                                event.getRequest().getUrl(),
                                System.lineSeparator()));

        network.onLoadingFinished(
                event -> {
                    // Close the tab and close the browser when loading finishes.
                    chromeService.closeTab(tab);
                    launcher.close();
                });

        // Enable network events.
        network.enable();

        // Navigate to github.com.
        page.navigate("http://github.com");

        devToolsService.waitUntilClosed();
    }
}