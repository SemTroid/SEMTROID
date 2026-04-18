package main.resources.WifiAnalyzer;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ThirdTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.vrem.wifianalyzer");
        desiredCapabilities.setCapability("appActivity", "com.vrem.wifianalyzer.SplashActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5554");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementByAccessibilityId("Open navigation drawer").click();
        driver.findElementByXPath("(//*[@resource-id='com.vrem.wifianalyzer:id/design_menu_item_text'])[2]").click();
        driver.findElementById("com.vrem.wifianalyzer:id/action_scanner").click();
        driver.findElementById("com.vrem.wifianalyzer:id/action_scanner").click();
        driver.findElementById("com.vrem.wifianalyzer:id/action_wifi_band").click();
        driver.findElementById("com.vrem.wifianalyzer:id/action_wifi_band").click();


    }

    @After
    public void tearDown() {
        driver.quit();
    }

}


