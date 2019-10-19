package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pl.dominikd.utils.NopTestConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static final String NOP_CONFIG_XML = "../nop-test-config.xml";
    protected WebDriver driver;
    private String url;
    private String chosenWebDriver;

    @BeforeSuite
    public void setWebDriverPath() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(NopTestConfig.class);
        Unmarshaller um = context.createUnmarshaller();
        NopTestConfig nopTestConfig = (NopTestConfig) um.unmarshal(new FileReader(
                NOP_CONFIG_XML));

        System.setProperty("webdriver.chrome.driver", nopTestConfig.getChromeDriverPath());
        System.setProperty("webdriver.gecko.driver", nopTestConfig.getGeckoDriverPath());
        this.url = nopTestConfig.getUrl();
        this.chosenWebDriver = nopTestConfig.getWebDriver();
    }

    @BeforeTest
    public void setUp() {
        if (chosenWebDriver.equals("FIREFOX"))
            driver = new FirefoxDriver();
        else
            driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        // chrome driver leaves processes running unless we first close() and then quit()
        if (chosenWebDriver.equals("CHROME"))
            driver.close();
        driver.quit();
    }

    @BeforeMethod
    protected void getInitPage() {
        driver.get(url);
    }
}
