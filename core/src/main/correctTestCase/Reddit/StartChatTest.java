package main.correctTestCase.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2024.04.0
// steps：点击chat图标 -->点击右上角chat图标 --> 输入框输入testUser --> 点击testUser文本 --> 点击start chat文本


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
        desiredCapabilities.setCapability("udid", "192.168.58.109:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Chat\"]/android.widget.ImageView").click();
        driver.findElementById("com.reddit.frontpage:id/action_new_chat").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id='box_search_input']").sendKeys("testUser");
        driver.findElementByXPath("//android.widget.TextView[@text='testuser']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Start Chat']").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
