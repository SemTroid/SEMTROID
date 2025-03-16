package main.correctTestCase.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2024.04.0
// steps：点击home图标 -->点击搜索框 -> 输入chatgpt -->  点击results for “chagpt”文本 --> 点击posts文本


public class ViewPostsTest {

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
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Home\"]/android.widget.ImageView").click();
        driver.findElementById("com.reddit.frontpage:id/feed_control_search_icon").click();
        driver.findElementById("com.reddit.frontpage:id/search").sendKeys("chatgpt");
        driver.findElementByXPath("//android.view.View[8]/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Posts\"]/android.widget.TextView").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
