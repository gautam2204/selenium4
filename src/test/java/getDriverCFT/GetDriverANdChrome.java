package getDriverCFT;

import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GetDriverANdChrome {

  private static final String CHROME_BASE_URL =
      "https://googlechromelabs.github.io/chrome-for-testing/last-known-good-versions.json";
  static String cft_version_response = "";
  static CloseableHttpClient httpClient = HttpClients.createDefault();
  static CloseableHttpResponse response = null;

  public static void main(String[] args) {

    Properties properties = new Properties();

    try {
      properties.load(
          new InputStreamReader(
              Objects.requireNonNull(
                  GetDriverANdChrome.class
                      .getClassLoader()
                      .getResourceAsStream("myProperties.properties"))));
    } catch (IOException e) {
      e.printStackTrace();
    }

    String cftVersion = properties.getProperty("cft");
    String version = null;
    if (StringUtils.containsIgnoreCase(cftVersion, "latest")
        || StringUtils.containsIgnoreCase(cftVersion, "") && StringUtils.isNotEmpty(cftVersion)) {

      response = callService(CHROME_BASE_URL);

      try {
        assert response != null;
        cft_version_response = EntityUtils.toString(response.getEntity());

      } catch (IOException | ParseException e) {
        e.printStackTrace();
      }
      version = JsonPath.read(cft_version_response, "$.channels.Stable.version");
    } else if (StringUtils.isNotEmpty(cftVersion)){
      version = cftVersion;
    }

    System.out.println(version);
    String chromeDownloadUrl =
        "https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/"
            + version
            + "/win64/chrome-win64.zip";
    String chromeDriverDownloadUrl =
        "https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/"
            + version
            + "/win64/chromedriver-win64.zip";
    callServiceToDownload(chromeDownloadUrl, "src/test/driver/chrome.zip");
    callServiceToDownload(chromeDriverDownloadUrl, "src/test/driver/chromedriver.zip");
    String parentDir = System.getProperty("user.dir");

    String zipFilePathForChrome = "src/test/driver/chrome.zip";
    String extractPath = "src/test/driver/";

    if (unzipFile(zipFilePathForChrome, extractPath)) {
      System.out.println("ZIP file extracted successfully.");
    } else {
      System.out.println("Failed to extract ZIP file.");
    }
    String zipFilePathForChromeDriver = "src/test/driver/chromedriver.zip";

    if (unzipFile(zipFilePathForChromeDriver, extractPath)) {
      System.out.println("ZIP file extracted successfully.");
    } else {
      System.out.println("Failed to extract ZIP file.");
    }
  }

  public static boolean unzipFile(String zipFilePath, String extractPath) {
    try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
      byte[] buffer = new byte[1024];
      ZipEntry entry;

      while ((entry = zipInputStream.getNextEntry()) != null) {
        String entryName = entry.getName();
        File entryFile = new File(extractPath + entryName);

        if (entry.isDirectory()) {
          entryFile.mkdirs();
        } else {
          File parentDir = entryFile.getParentFile();
          if (!parentDir.exists()) {
            parentDir.mkdirs();
          }

          try (FileOutputStream fos = new FileOutputStream(entryFile)) {
            int length;
            while ((length = zipInputStream.read(buffer)) > 0) {
              fos.write(buffer, 0, length);
            }
          }
        }
      }

      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

  private static void callServiceToDownload(String chromeDownloadUrl, String filePath) {
    response = callService(chromeDownloadUrl);
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(filePath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // Get the response entity and write it to the output stream
    try {
      response.getEntity().writeTo(fileOutputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Close the output stream
    try {
      assert fileOutputStream != null;
      fileOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("ZIP file downloaded successfully.");
  }

  private static CloseableHttpResponse callService(String url) {
    HttpGet get = new HttpGet(url);
    try {
      return httpClient.execute(get);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
