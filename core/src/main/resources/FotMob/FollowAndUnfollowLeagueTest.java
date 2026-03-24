package main.resources.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


// Version：V 136-0-9401-20211103
// steps：点击Leagues奖杯图标 --> 点击五角星图标（Champions League）--> 搜索框输入Champions League文本 --> 点击Champions League文本  --> 点击News文本 --> 点击五角星图标（取消收藏）--> 点击Harry Kane文本
// 失效：39-Leagues奖杯图标-定位不到   40-五角星图标-定位不到    42-Champions League文本-定位不到   44-点击五角星图标（取消收藏）-定位不到   45-Harry Kane文本-定位不到

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
        desiredCapabilities.setCapability("appActivity", "com.mobilefootie.fotmob.gui.v2.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Leagues\"]/android.widget.ImageView").click();
        driver.findElementByXPath("//android.widget.RelativeLayout[3]/android.widget.LinearLayout/android.widget.ImageView[2]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/search_src_text").sendKeys("Champions League");
        driver.findElementByXPath("//android.widget.RelativeLayout[2]/android.widget.LinearLayout/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Player stats\"]/android.widget.TextView").click();
        driver.findElementById("com.mobilefootie.wc2010:id/menu_favorite").click();
        driver.findElementByXPath("//android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.TextView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
