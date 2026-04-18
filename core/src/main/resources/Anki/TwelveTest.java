package main.resources.Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TwelveTest {
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
    public void sampleTest() throws InterruptedException{
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/flip_card").click();
        driver.findElementById("com.ichi2.anki:id/nextTime1").click();
        driver.findElementById("com.ichi2.anki:id/flip_card").click();
        driver.findElementById("com.ichi2.anki:id/nextTime2").click();
        driver.findElementById("com.ichi2.anki:id/flip_card").click();
        driver.findElementById("com.ichi2.anki:id/nextTime3").click();
        driver.findElementById("com.ichi2.anki:id/action_mark_card").click();
        driver.findElementById("com.ichi2.anki:id/action_mark_card").click();
        driver.findElementById("com.ichi2.anki:id/action_mark_card").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//第一个牌组、show answer、<1min、show answer、<10min、show answer、4d、星标、星标、星标、show answer、11min、箭头、返回
//Break:41/42/43