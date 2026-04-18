package main.resources.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 136-0-9401-20211103
// steps：点击五角星图标（底栏）--> 点击加号图标 --> Arsenal对应五角星图标（收藏）--> 点击Edit文本 --> 点击减号图标（Real Madrid对应）--> 点击Done文本
// 失效： 39-五角星图标（底栏）-定位不到  41-Arsenal对应五角星图标（收藏）-定位不到  43-减号图标（Real Madrid对应）-定位不到   44-Done文本-定位不到

public class FollowAndUnfollowTeamTest {

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
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc='Favorites']/android.widget.ImageView").click();
        driver.findElementById("com.mobilefootie.wc2010:id/menu_add_favorites").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[8]/android.widget.ImageView[2]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/menu_edit").click();
        driver.findElementByXPath("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text='DONE']").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
