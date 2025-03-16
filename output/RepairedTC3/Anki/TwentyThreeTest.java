package Anki;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class TwentyThreeTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.ichi2.anki");
        desiredCapabilities.setCapability("appActivity", "com.ichi2.anki.IntentHandler");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5554");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Reviewing\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New card position\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Mix new cards and reviews\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New card position\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"New cards after reviews\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New card position\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"New cards before reviews\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New card position\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Start of next day\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Learn ahead limit\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Learn ahead limit\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("10");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Timebox time limit\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Timebox time limit\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("1");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"App bar buttons\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Reset to default\"]").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementById("android:id/checkbox").click();
        driver.findElementById("android:id/checkbox").click();
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Fullscreen mode\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Hide the system bars\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Fullscreen mode\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Hide the system bars and answer buttons\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Fullscreen mode\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Off\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Fullscreen mode\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByAccessibilityId("Navigate up").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
