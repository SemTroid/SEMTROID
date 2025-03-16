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

public class TwentyTwoTest {

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
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"AnkiDroid\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"AnkiWeb account\"]").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Deck for new cards\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Use current deck\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Deck for new cards\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Decide by note type\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Deck for new cards\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Language\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"System language\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Language\"]").click();
        driver.findElementById("android:id/button2").click();
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Error reporting mode\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Always report\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Error reporting mode\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Never report\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Error reporting mode\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Ask me\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Error reporting mode\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Notify when\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Pending messages available\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Notify when\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByAccessibilityId("Navigate up").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
