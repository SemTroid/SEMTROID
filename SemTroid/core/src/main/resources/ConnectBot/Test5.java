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

public class Test5 {
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
        driver.findElementByXPath("(//*[@content-desc='More options'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementByXPath("(//android.widget.Button)[1]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//android.widget.Button)[2]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//android.widget.Button)[3]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//android.widget.Button)[4]").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 40、43、45、47