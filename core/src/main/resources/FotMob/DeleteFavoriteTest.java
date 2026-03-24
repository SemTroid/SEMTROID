package main.resources.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;


// Version：V 136-0-9401-20211103
// steps：38点击星星图标（Favorite）--> 39点击EDIT文本 --> 40点击删除图标（Real Madrid）--> 41点击DONE文本
// 失效：38-定位不到 40-定位不到  41-定位不到

public class DeleteFavoriteTest {

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
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc='Favorites']/android.widget.ImageView").click();
        driver.findElementById("com.mobilefootie.wc2010:id/menu_edit").click();
        driver.findElementByXPath("//android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text='DONE']").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
