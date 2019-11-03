package pl.dominikd.nopcommerce.demo.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import pl.dominikd.nopcommerce.demo.base.TestBase;

import java.io.File;
import java.io.IOException;

public class ListenerClass extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        String testName = iTestResult.getName();
        String currentPath = System.getProperty("user.dir");
        Object currentClass = iTestResult.getInstance();
        WebDriver webDriver = ((TestBase) currentClass).getDriver();

        File screenshotFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(screenshotFile, new File(currentPath + ".\\screenshots\\" + testName + ".png"));
        } catch (IOException ignored) {
            System.out.println("Screenshot saving error");
        }
    }
}
