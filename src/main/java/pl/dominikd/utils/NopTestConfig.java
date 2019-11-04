package pl.dominikd.utils;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nopTestConfig")
public class NopTestConfig {
    private String geckoDriverPath;
    private String chromeDriverPath;
    private String url;
    private String webDriver;
    private String runHeadless;

    public String getGeckoDriverPath() {
        return geckoDriverPath;
    }

    public void setGeckoDriverPath(String geckoDriverPath) {
        this.geckoDriverPath = geckoDriverPath;
    }

    public String getChromeDriverPath() {
        return chromeDriverPath;
    }

    public void setChromeDriverPath(String chromeDriverPath) {
        this.chromeDriverPath = chromeDriverPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWebDriver() {
        return webDriver;
    }

    public void setWebDriver(String webDriver) {
        this.webDriver = webDriver;
    }

    public String getRunHeadless() {
        return runHeadless;
    }

    public void setRunHeadless(String runHeadless) {
        this.runHeadless = runHeadless;
    }
}
