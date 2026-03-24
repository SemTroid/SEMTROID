package main.resources.Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TwentyTest {
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
    public void sampleTest() throws InterruptedException{
        //
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[3]").click();
        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[2]").click();
        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[3]").click();
        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[4]").click();
        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[3]").click();
        //
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[3]").click();
        driver.findElementByXPath("(//android.widget.TextView)[5]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[3]").click();
        driver.findElementByXPath("(//android.widget.TextView)[6]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[3]").click();
        driver.findElementByXPath("(//android.widget.TextView)[6]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[3]").click();
        //
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[3]").click();
        driver.findElementByXPath("(//android.widget.TextView)[6]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[3]").click();
        driver.findElementByXPath("(//android.widget.TextView)[6]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[3]").click();
        driver.findElementByXPath("(//android.widget.TextView)[7]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_time_chooser").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/radio'])[3]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
