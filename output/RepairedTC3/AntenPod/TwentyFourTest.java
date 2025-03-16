package AntenPod;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class TwentyFourTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "de.danoeh.antennapod");
        desiredCapabilities.setCapability("appActivity", "de.danoeh.antennapod.activity.OnlineFeedViewActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("de.danoeh.antennapod:id/sort_items").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Date (New → Old)\"]").click();
        driver.findElementById("de.danoeh.antennapod:id/sort_items").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Date (Old → New)\"]").click();
        driver.findElementById("de.danoeh.antennapod:id/sort_items").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Title (A → Z)\"]").click();
        driver.findElementById("de.danoeh.antennapod:id/sort_items").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Title (Z → A)\"]").click();
        driver.findElementById("de.danoeh.antennapod:id/sort_items").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Duration (Short → Long)\"]").click();
        driver.findElementById("de.danoeh.antennapod:id/sort_items").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Duration (Long → Short)\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
