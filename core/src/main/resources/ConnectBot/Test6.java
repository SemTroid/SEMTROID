package main.resources.ConnectBot;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Test6 {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "org.connectbot");
        desiredCapabilities.setCapability("appActivity", "org.connectbot.HostListActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementById("org.connectbot:id/transport_selection").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[2]").click();
        driver.findElementById("org.connectbot:id/transport_selection").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[3]").click();
        driver.findElementById("org.connectbot:id/transport_selection").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 39、40、41、42、43、44