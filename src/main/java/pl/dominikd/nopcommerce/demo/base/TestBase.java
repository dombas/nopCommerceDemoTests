package pl.dominikd.nopcommerce.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.dominikd.utils.NopTestConfig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private static final String NOP_CONFIG_XML = "../nop-test-config.xml";
    private static final String FALLBACK_NOP_CONFIG_XML = "./nop-test-config.xml";
    protected WebDriver driver;
    private String url;
    private String chosenWebDriver;
    private boolean CLOSE_DRIVER = true;

    public TestBase() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(NopTestConfig.class);
        Unmarshaller um = context.createUnmarshaller();
        NopTestConfig nopTestConfig;
        try {
            nopTestConfig = (NopTestConfig) um.unmarshal(new FileReader(
                    NOP_CONFIG_XML));
        } catch (FileNotFoundException e) {
            nopTestConfig = (NopTestConfig) um.unmarshal(new FileReader(
                    FALLBACK_NOP_CONFIG_XML));
        }

        System.setProperty("webdriver.chrome.driver", nopTestConfig.getChromeDriverPath());
        System.setProperty("webdriver.gecko.driver", nopTestConfig.getGeckoDriverPath());
        this.url = nopTestConfig.getUrl();
        this.chosenWebDriver = nopTestConfig.getWebDriver();
    }

    public void setCLOSE_DRIVER(boolean CLOSE_DRIVER) {
        this.CLOSE_DRIVER = CLOSE_DRIVER;
    }

    @BeforeMethod
    public void setUp() {
        if (chosenWebDriver.equals("FIREFOX"))
            driver = new FirefoxDriver();
        else
            driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown() {

        if (CLOSE_DRIVER) {
            // chrome driver leaves processes running unless we first close() and then quit()
            if (chosenWebDriver.equals("CHROME"))
                driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
