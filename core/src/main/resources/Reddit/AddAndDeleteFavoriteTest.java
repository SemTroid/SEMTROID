package main.resources.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2021.47.0
// steps：点击底栏第二个图标 --> 点击五角星图标（收藏ML）-->点击五角星图标（取消收藏ML）
// 失效： 39-底栏第二个图标-定位不到 40-五角星图标（收藏ML）-定位不到 41-五角星图标（取消收藏ML）-定位不到

public class AddAndDeleteFavoriteTest {

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
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Browse\"]/android.widget.ImageView").click();
        driver.findElementByXPath("//android.view.ViewGroup[5]/android.widget.ImageView[2]").click();
        driver.findElementByXPath("//android.view.ViewGroup[2]/android.widget.ImageView[2]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
