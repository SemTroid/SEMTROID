package AntenPod;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class ThreeTest {

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
        driver.findElementByAccessibilityId("Open menu").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Subscriptions\"]").click();
        driver.findElementById("de.danoeh.antennapod:id/refresh_item").click();
        driver.findElementById("de.danoeh.antennapod:id/subscriptions_add").click();
        driver.navigate().back();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Filter\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Filter\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Sort by counter\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Sort alphabetically\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Sort by publication date\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Sort by number of played episodes\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Number of columns\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"2\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Number of columns\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"3\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Number of columns\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"4\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Number of columns\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"5\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
