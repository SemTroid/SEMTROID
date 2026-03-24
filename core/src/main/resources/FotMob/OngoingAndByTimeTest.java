package main.resources.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 136-0-9401-20211103
// steps：点击日历图标 --> 点击TODAY文本 --> 下滑（一点） --> 点击ONGOING文本 --> 点击BY TIME文本
// 失效：39-日历图标-定位错    41-ONGOING文本-定位不到    42-BY TIME文本-路径增加+定位不到

public class OngoingAndByTimeTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.mobilefootie.wc2010");
        desiredCapabilities.setCapability("appActivity", "com.mobilefootie.fotmob.gui.v2.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
//        driver.findElementByXPath("(//android.widget.TextView)[1]").click();
//        driver.findElementById("com.mobilefootie.wc2010:id/action_today").click();
        driver.findElementById("com.mobilefootie.wc2010:id/ongoingBtn").click();
        driver.findElementById("com.mobilefootie.wc2010:id/sortByTime").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
