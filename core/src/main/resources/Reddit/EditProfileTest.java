package main.resources.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2021.47.0
// steps：点击铃铛图标（inbox） --> 点击头像图标 --> 点击myprofile文本 --> 点击edit profile文本 -->第一个输入框输入test --> 第二个输入框输入test repair  --> 点击关闭图标（content visibility）--> 点击SAVE文本 --> 点击share图标
// 失效：39-铃铛图标（inbox）-定位不到   41-myprofile文本-定位不到   42-edit profile文本-定位不到   43-第一个输入框-定位不到  44-第二个输入框-定位不到  45-关闭图标（content visibility）-滑动+定位不到   46-SAVE文本-定位不到    47--share图标--定位不到

public class EditProfileTest {

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
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Inbox\"]/android.widget.ImageView").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Logged in avatar\"]/android.widget.ImageView").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup[2]/android.widget.ScrollView/android.widget.LinearLayout/android.view.ViewGroup[1]/android.widget.TextView").click();
        driver.findElementById("com.reddit.frontpage:id/profile_edit").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.EditText").sendKeys("test");
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.EditText").sendKeys("test repair");
        driver.findElementById("com.reddit.frontpage:id/content_visible_switch").click();
        driver.findElementById("com.reddit.frontpage:id/action_save").click();
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Share\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
