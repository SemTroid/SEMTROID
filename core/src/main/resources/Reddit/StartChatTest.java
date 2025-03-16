package main.resources.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2021.47.0
// steps：点击chat图标 -->点击右上角chat图标 --> 输入框输入testUser --> 点击testUser文本 --> 点击start chat文本
// 失效： 39-chat图标-定位不到  40-右上角chat图标-定位不到  41-输入框-定位不到  42-testUser文本-定位不到 43-start chat文本-定位不到

public class StartChatTest {

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
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Chat\"]/android.widget.ImageView").click();
        driver.findElementById("com.reddit.frontpage:id/action_chat").click();
        driver.findElementById("com.reddit.frontpage:id/name_completion_view").sendKeys("testUser");
        driver.findElementById("com.reddit.frontpage:id/username").click();
        driver.findElementById("com.reddit.frontpage:id/invite_button").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
