package Accessibilty;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v122.accessibility.Accessibility;
import org.openqa.selenium.devtools.v122.accessibility.model.AXNode;
import org.openqa.selenium.devtools.v122.accessibility.model.AXNodeId;
import org.openqa.selenium.devtools.v122.dom.DOM;
import org.openqa.selenium.devtools.v122.dom.model.Node;
import org.openqa.selenium.devtools.v122.dom.model.NodeId;
import org.openqa.selenium.devtools.v122.page.Page;
import org.openqa.selenium.devtools.v122.page.model.FrameId;
import org.openqa.selenium.devtools.v122.page.model.FrameTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;



public class CdpSelCall {
    final static Logger logger = LoggerFactory.getLogger(CdpSelCall.class);

  public static void main(String[] args) throws InterruptedException {

      String currentPath = System.getProperty("user.dir");
//        System.out.println("currentPath = "+ currentPath);
      logger.info("currentPath = "+ currentPath);
      ChromeOptions chromeOptions=new ChromeOptions();
      chromeOptions.setBinary("src/test/driver/chrome-win64/chrome.exe");
//      chromeOptions.setCapability("webSocketUrl", true);
      System.setProperty("webdriver.chrome.driver", "src/test/driver/chromedriver-win64/chromedriver.exe");


//      chromeOptions.addArguments( Arrays.asList("--remote-debugging-address=127.0.0.1","--remote-debugging-port=9200","--enable-logging"));

      ChromeDriver driver=new ChromeDriver(chromeOptions);
//      driver.get("https://www.selenium.dev/selenium/web/devToolsRequestInterceptionTest.html");
      driver.get("https://auth.tiaa.org/public/authentication/securelogin");
//      BrowsingContext browsingContext = new BrowsingContext(driver, WindowType.WINDOW);

      DevTools devTools = driver.getDevTools();
      devTools.createSession();
      Thread.sleep(10000);

      //Returns the root DOM node (and optionally the subtree) to the caller. Implicitly enables the DOM domain events for the current target.
//      depth = -1
//              pierce = 0
      devTools.send(DOM.enable(Optional.of(DOM.EnableIncludeWhitespace.ALL)));

      //once you get the parent nodeID and frameID  travesrse to find child nodes

      Node doc = devTools.send(DOM.getDocument(Optional.empty(), Optional.of(Boolean.TRUE)));
      NodeId nodeId = doc.getNodeId();

      logger.debug("wait");

      //Enable the page in devtool
      devTools.send(Page.enable());
      //fetch the frame id of parent/root as above we got parent node
      FrameTree frameTree = devTools.send(Page.getFrameTree());
      //from frame tree get frame ide
      FrameId frameID = frameTree.getFrame().getId();


      devTools.send(Accessibility.enable());
      Thread.sleep(10000);
//      List<AXNode> listOfnOdes = devTools.send(Accessibility.getAXNodeAndAncestors(Optional.empty(), Optional.empty(), Optional.empty()));
//      List<AXNode> listOfNodes = devTools.send(Accessibility.getFullAXTree(Optional.empty(), Optional.empty()));

      //get Root access node
      AXNode rootAccessNode = devTools.send(Accessibility.getRootAXNode(Optional.of(frameID)));
      AXNodeId rootAccessNodeID = rootAccessNode.getNodeId();

      List<AXNode> childNodesDetails = devTools.send(Accessibility.getChildAXNodes(rootAccessNodeID, Optional.of(frameID)));
      childNodesDetails.forEach(e->System.out.println("Node Details ::::::::::"+e.getDescription()));





  }
}
