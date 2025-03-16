package main.correctTestCase.BBCNews;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// From V5.3.0.50
// My News -> Edit My News -> (Delete:Business)
public class HealthDeleteTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "8");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "bbc.mobile.news.uk");
        desiredCapabilities.setCapability("appActivity", "bbc.mobile.news.v3.app.TopLevelActivity");
        desiredCapabilities.setCapability("noReset", true);
        //desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() {
        driver.findElementByXPath("//android.widget.TextView[@text=\"My News\"]").click();
        driver.findElementByAccessibilityId("Edit My News").click();
        driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"Remove topic\"])[1]").click();
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
