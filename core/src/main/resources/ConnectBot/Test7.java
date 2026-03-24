package main.resources.ConnectBot;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Test7 {
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
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[3]").click();
        driver.findElementById("org.connectbot:id/front_quickconnect").sendKeys("biadlo");
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.navigate().back();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 41(False)、42、43、44(False)、45、46、47、48(False)、49(False)、51、52(False)、55(False)