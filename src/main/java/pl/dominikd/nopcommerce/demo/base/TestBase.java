package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public final String url = "https://demo.nopcommerce.com/";
    public WebDriver driver;

    @BeforeSuite
    public void setChromePath() {
        System.setProperty("webdriver.chrome.driver", "C:\\IdeaProjects\\demotests\\chromedriver.exe");
        System.setProperty("webdriver.firefox.bin", "C:\\Program Files\\Mozilla Firefox\\firefox.exe");
        System.setProperty("webdriver.gecko.driver", "G:\\IdeaProjects\\nop\\geckodriver.exe");
    }

    @BeforeTest
    public void setUp() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        // chrome driver leaves processes running unless we first close() and then quit()
        //driver.close();
        driver.quit();
    }

    @BeforeMethod
    protected void getInitPage() {
        driver.get(url);
    }
}
