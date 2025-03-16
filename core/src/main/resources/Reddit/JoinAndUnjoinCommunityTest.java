package main.resources.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2021.47.0
// steps：点击popular文本 --> 点击加号图标（Join）--> 点击对应Community图标 --> 点击Joined文本 --> 点击Leave文本
// 失效：39-popular文本-路径增加    40-加号图标（Join）-定位不到    41-对应Community图标-定位不到   42-Joined文本-定位不到

public class JoinAndUnjoinCommunityTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.reddit.frontpage");
        desiredCapabilities.setCapability("appActivity", "launcher.default");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Popular\"]/android.widget.TextView").click();
        driver.findElementById("com.reddit.frontpage:id/subscribe_button").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Avatar\"]/android.widget.ImageView").click();
        driver.findElementById("com.reddit.frontpage:id/profile_follow").click();
        driver.findElementById("android:id/button1").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
