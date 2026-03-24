package main.resources.GoogleNews;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 5.50.0.433421834
// steps：点击Newsstand文本 -->（滑动）--> 点击More Entertainment文本 --> 点击五角星图标（收藏Maxim）--> 点击Maxim --> 点击MoreOptions图标 --> 点击Share文本 --> 点击More文本 --> 点击Messaging文本
// 失效： 39-Newsstand文本-定位不到  40-More Entertainment文本-定位不到  41-五角星图标（收藏Maxim）-定位不到  42-Maxim文本-定位不到  45-More文本-元素删除    46-Messaging文本-定位错

public class ShareEntertainmentTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.google.android.apps.magazines");
        desiredCapabilities.setCapability("appActivity", "com.google.apps.dots.android.newsstand.activity.DeepLinkActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.LinearLayout/android.widget.LinearLayout[4]/android.widget.TextView").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.TextView").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[6]/android.widget.FrameLayout[2]/android.widget.ImageView").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.LinearLayout[6]/android.widget.LinearLayout/android.widget.TextView[1]").click();
        driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"More options\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Share']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='More']").click();
        driver.findElementByXPath("(//android.widget.TextView)[3]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
