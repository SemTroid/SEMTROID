package AntenPod;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class ThirteenTest {

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
        driver.findElementById("de.danoeh.antennapod:id/nav_settings").click();
        new TouchAction(driver).longPress(PointOption.point(400, 707)).moveTo(PointOption.point(400, 100)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Statistics\"]").click();
        driver.findElementByAccessibilityId("Playback").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Reset statistics data\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Reset statistics data\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Statistics mode\"]").click();
        driver.findElementById("de.danoeh.antennapod:id/statistics_mode_normal").click();
        driver.findElementById("de.danoeh.antennapod:id/statistics_mode_count_all").click();
        driver.findElementById("de.danoeh.antennapod:id/statistics_mode_normal").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByAccessibilityId("Downloads").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
