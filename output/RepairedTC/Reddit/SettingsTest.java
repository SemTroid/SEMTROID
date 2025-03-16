package Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class SettingsTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.reddit.frontpage");
        desiredCapabilities.setCapability("appActivity", "launcher.default");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Inbox\"]/android.widget.ImageView[1]").click();
        driver.findElementById("com.reddit.frontpage:id/inner_user_icon").click();
        driver.findElementById("com.reddit.frontpage:id/drawer_nav_settings").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Card\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Classic\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Always\"]").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Never\"]/android.widget.ImageView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
