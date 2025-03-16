package main.resources.NetworkMonitor;

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
        driver.findElementByXPath("(//*[@resource-id='ca.rmen.android.networkmonitor:id/switchWidget'])[1]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='ca.rmen.android.networkmonitor:id/switchWidget'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[10]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:38/39/40
//COSER action:5