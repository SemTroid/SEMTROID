package Anki;

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
        driver.findElementByXPath("//android.widget.TextView[@text=\"Appearance\"]").click();
        driver.navigate().back();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Decks\"]").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Night mode\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
