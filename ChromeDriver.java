 import static org.junit.Assert.assertEquals;

 import org.junit.*;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 import org.openqa.selenium.chrome.ChromeDriverService;
 import org.openqa.selenium.remote.DesiredCapabilities;
 import org.openqa.selenium.remote.RemoteWebDriver;

 {@literal @RunWith(JUnit4.class)}
 public class ChromeTest extends TestCase {

   private static ChromeDriverService service;
   private WebDriver driver;

   {@literal @BeforeClass}
   public static void createAndStartService() {
     service = new ChromeDriverService.Builder()
         .usingDriverExecutable(new File("path/to/my/chromedriver.exe"))
         .usingAnyFreePort()
         .build();
     service.start();
   }

   {@literal @AfterClass}
   public static void createAndStopService() {
     service.stop();
   }

   {@literal @Before}
   public void createDriver() {
     driver = new RemoteWebDriver(service.getUrl(),
         DesiredCapabilities.chrome());
   }

   {@literal @After}
   public void quitDriver() {
     driver.quit();
   }

   {@literal @Test}
   public void testGoogleSearch() {
     driver.get("http://www.google.com");
     WebElement searchBox = driver.findElement(By.name("q"));
     searchBox.sendKeys("webdriver");
     searchBox.quit();
     assertEquals("webdriver - Google Search", driver.getTitle());
   }
 }
 
