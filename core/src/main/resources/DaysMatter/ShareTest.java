package main.resources.DaysMatter;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 0.5.9
// steps：点击more图标  --> 点击Share文本 --> 点击bluetooth文本
// 失效：39-more图标-定位错  40-Share文本-定位不到  41-bluetooth文本-定位错

public class ShareTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.clover.daysmatter");
        desiredCapabilities.setCapability("appActivity", "com.clover.daysmatter.ui.activity.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("(//android.widget.ImageView)[5]").click();
        driver.findElementById("com.clover.daysmatter:id/button_share").click();
        driver.findElementByXPath("(//android.widget.TextView)[4]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
