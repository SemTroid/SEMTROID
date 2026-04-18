package main.correctTestCase.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 186-11404-20240318
// steps：点击五角星图标（底栏）--> 点击Players文本 --> 点击大Add图标 --> 点击返回图标 --> 点击Add文本（Messi对应）--> 点击Messi文本 --> 点击五角星图标（取消收藏）


public class FollowAndUnfollowPlayerTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.mobilefootie.wc2010");
        desiredCapabilities.setCapability("appActivity", "com.fotmob.android.ui.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "192.168.58.109:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Following\"]/android.widget.FrameLayout/android.widget.ImageView").click();
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Players\"]/android.widget.TextView").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.GridView/androidx.cardview.widget.CardView[1]/android.widget.ImageView").click();
        driver.findElementById("com.mobilefootie.wc2010:id/imageView_back").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.GridView/androidx.cardview.widget.CardView[2]/android.widget.LinearLayout/android.widget.CompoundButton").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.ScrollView/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/android.widget.GridView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Lionel Messi\"]/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.LinearLayout/android.widget.Button").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
