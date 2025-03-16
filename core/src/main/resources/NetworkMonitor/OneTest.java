package main.resources.NetworkMonitor;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class OneTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "ca.rmen.android.networkmonitor");
        desiredCapabilities.setCapability("appActivity", "ca.rmen.android.networkmonitor.app.main.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementById("ca.rmen.android.networkmonitor:id/action_refresh").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementById("ca.rmen.android.networkmonitor:id/ok").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//View log、刷新、三点、fields、三点、select all、三点、select None、三点、WiFi pro、三点、Mobile pro GSM、三点、Mobile pro CDMA、三点、location pro、ok、back
//Break:35?/36?/38?
//manually check Break:37√/39√/41√/43√/45√/47√/49