package main.correctTestCase.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2024.04.0
// steps：点击底栏第二个图标 --> 点击custom feeds文本 --> 点击create new custom feed文本 --> 第一个输入框输入technology --> 第二个输入test repair --> 点击done 文本


public class AddCustomFeedTest {

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
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "192.168.58.109:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.reddit.frontpage:id/item_community_nav_icon_large").click();
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Custom Feeds\"]").click();
        driver.findElementById("com.reddit.frontpage:id/create_new_feed_title").click();
        driver.findElementById("com.reddit.frontpage:id/create_custom_feed_name").sendKeys("technology");
        driver.findElementById("com.reddit.frontpage:id/create_custom_feed_description").sendKeys("test repair");
        driver.findElementById("com.reddit.frontpage:id/custom_feed_done_button").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
