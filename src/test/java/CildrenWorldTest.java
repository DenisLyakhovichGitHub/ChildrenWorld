import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CildrenWorldTest {

  private static WebDriver driver;
  private Logger logger = LogManager.getLogger(CildrenWorldTest.class);
  String env = System.getProperty("browser", "chrome");

  @BeforeEach
  public void setUp() {
    logger.info("env" + env);
    driver = WebDriverFactory.getDriver(env.toLowerCase());
    logger.info("Драйвер стартовал");
  }

  @Test
  public void openPage() {
    driver.get("https://www.detmir.ru");
    logger.info("Загружен сайт - " +
        "https://www.detmir.ru");
  }

  @Test
  public void findProduct() {
    //открытие сайта Детский мир
    driver.manage().window().maximize();
    driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
    driver.get("https://www.detmir.ru");
    logger.info("Загружен сайт - " +
        "https://www.detmir.ru");

    //получение и вывод куки
    logger.info("Cookiee сайта");
    Set<Cookie> cookies = driver.manage().getCookies();
    for (Cookie cookie : cookies ) {
      logger.info("Имя куки:" + cookie.getName());
      logger.info("Значение куки" + cookie.getValue());
      logger.info("_____________________________________");
    }

    //закрываем куки
    WebElement cookiesClose = driver.findElement(By.className("be"));
    cookiesClose.click();

    //поиск товара
    WebElement toysAndGames = driver.findElement(By.linkText("Игрушки и игры"));
    logger.info("Название категории товара - " + toysAndGames.getText());
    toysAndGames.click();

    driver.get("https://www.detmir.ru/catalog/index/name/igry_i_igrushki/");
    WebElement childConstruct = driver.findElement(By.linkText("Детские конструкторы"));
    logger.info("Категория - " + childConstruct.getText());
    childConstruct.click();

    driver.get("https://www.detmir.ru/catalog/index/name/konstruktory/");
    WebElement constructor = driver.findElement(By.xpath("//*[@id=\"app-container\"]/div[2]/div[1]/main/div/div[2]/div[2]/div/div[4]/div/div/div/div[1]/div[1]/a"));
    constructor.click();
    logger.info("Имя выбранного товара - " + constructor.getText());

    //ожидание алерта и его разрешение
    FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
        .withTimeout(Duration.ofSeconds(100))
        .pollingEvery(Duration.ofMillis(100))
        .ignoring(NoSuchElementException.class);
    wait.until(ExpectedConditions.alertIsPresent());
    WebElement allert = driver.findElement(By.linkText("Разрешить"));
    allert.click();
  }

  @AfterEach
  public void setDown() {
    if (driver != null) {
      driver.quit();
      logger.info("Драйвер остановлен!");
    }
  }
}
