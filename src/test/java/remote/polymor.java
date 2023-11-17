package remote;


import org.openqa.selenium.PersistentCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

class Parent
{
    public void myName()
    {
    System.out.println("Parent Method");
    }
}

class child1 extends Parent
{
    public void myName()
    {
        System.out.println("Parent Method");
    }
}

class child2 extends Parent
{
    public void myName()
    {
        System.out.println("Parent Method");
    }
}


public class polymor {

  public static void main(String[] args) throws MalformedURLException {
      child2 child2Obj = new child2();
    Remote1 remote=new Remote1("abc",child2Obj);
    child1 child1obj = new child1();
//      Remote1 remote11=new Remote1("abc",child1obj);

      ChromeOptions options=new ChromeOptions();
      RemoteWebDriver remoteWebDriver=new RemoteWebDriver(new URL("local"),options);
      PersistentCapabilities persistentCapabilities=new PersistentCapabilities();
      RemoteWebDriver remoteWebDriver1=new RemoteWebDriver(new URL("local"),persistentCapabilities);




  }
}







