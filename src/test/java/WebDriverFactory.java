import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WebDriverFactory {
  private Logger logger = LogManager.getLogger(WebDriverFactory.class);

  public static WebDriver getDriver(String browserName) {
    switch (browserName) {
      case "chrome":
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
      case "firefox":
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
      default:
        throw new RuntimeException("Incorrect browser");
    }
  }
}
