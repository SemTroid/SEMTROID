package main.correctTestCase.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


// Version：V 186-11404-20240318
// steps：点击Leagues奖杯图标 --> 点击五角星图标（Champions League）--> 搜索框输入Champions League文本 --> 点击Champions League文本  --> 点击News文本 --> 点击五角星图标（取消收藏）--> 点击Harry Kane文本


public class FollowAndUnfollowLeagueTest {

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
        desiredCapabilities.setCapability("udid", "192.168.58.109:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Leagues\"]/android.widget.FrameLayout/android.widget.ImageView").click();
        driver.findElementByXPath("(//android.widget.CompoundButton[@content-desc=\"Follow\"])[2]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/search_src_text").sendKeys("Champions League");
        driver.findElementByXPath("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Player stats\"]/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Champions League\"]/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.LinearLayout/android.widget.Button").click();
        driver.findElementByXPath("//android.view.ViewGroup[2]/android.widget.TextView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
