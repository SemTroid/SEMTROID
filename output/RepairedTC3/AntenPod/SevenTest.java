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

public class SevenTest {

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
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Subscription Filter\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Subscription Filter\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Persistent Playback Controls\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Persistent Playback Controls\"]").click();
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Set Compact Notification Buttons\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Set Compact Notification Buttons\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Rewind\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Fast forward\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Skip episode\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Rewind\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Fast forward\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Skip episode\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Set Lockscreen Background\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Set Lockscreen Background\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Back Button Behavior\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Default\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Back Button Behavior\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Open navigation drawer\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Back Button Behavior\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Double tap to exit\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Back Button Behavior\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Confirm to exit\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Back Button Behavior\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Default\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Back Button Behavior\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Go to page…\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Queue\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Episodes\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Subscriptions\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Back Button Behavior\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.navigate().back();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
