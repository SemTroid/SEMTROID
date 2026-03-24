package main.correctTestCase.DaysMatter;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 1.18.19
// steps：点击加号图标 --> 输入框输入test -->  点击life文本--> 点击work文本 --> 点击pin Top对应开关图标 --> 点击save文本


public class AddEventTest {

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
        driver.findElementById("com.clover.daysmatter:id/action_add").click();
        driver.findElementById("com.clover.daysmatter:id/text_title").sendKeys("test");
        driver.findElementById("com.clover.daysmatter:id/summary_category").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.view.ViewGroup[2]/android.widget.RelativeLayout/android.widget.TextView").click();
        driver.findElementById("com.clover.daysmatter:id/switch_top").click();
        driver.findElementById("com.clover.daysmatter:id/button_save").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
