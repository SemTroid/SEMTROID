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

public class ElevenTest {

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
        driver.findElementByXPath("//android.widget.TextView[@text=\"Storage\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Choose Data Folder\"]").click();
        driver.findElementById("android:id/button2").click();
        //;
        //;
        driver.findElementByXPath("//android.widget.TextView[@text=\"Auto Delete\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Auto Delete\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Keep Favorite Episodes\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Keep Favorite Episodes\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Delete removes from Queue\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Delete removes from Queue\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Import/Export\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Database export\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Database import\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"OPML export\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"OPML Import\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"HTML export\"]").click();
        driver.navigate().back();
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Favorites export\"]").click();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
