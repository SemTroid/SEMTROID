package main.resources.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2021.47.0
// steps：点击home图标 -->点击搜索框 -> 输入chatgpt -->  点击results for “chagpt”文本 --> 点击posts文本
// 失效：39-home图标-定位不到   40-搜索框-定位不到   42-results for chagpt文本-定位不到

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
        desiredCapabilities.setCapability("udid", "emulator-5554");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Home\"]/android.widget.ImageView").click();
        driver.findElementById("com.reddit.frontpage:id/search_view").click();
        driver.findElementById("com.reddit.frontpage:id/search").sendKeys("chatgpt");
        driver.findElementById("com.reddit.frontpage:id/community_results").click();
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Posts\"]/android.widget.TextView").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
