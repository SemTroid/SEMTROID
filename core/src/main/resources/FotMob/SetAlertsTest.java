package main.resources.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 136-0-9401-20211103
// steps：点击搜索图标 --> 点击Barcelona --> 点击提醒图标（铃铛） --> 点击Goals对应对勾图标 --> 点击OK文本
// 失效：39-搜索图标-定位不到    41-提醒图标（铃铛）-定位不到    42-Goals对应对勾图标-定位不到    43-OK文本-定位不到

public class SetAlertsTest {

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
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Search\"]").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Toggle notifications\"]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/chkGoals").click();
        driver.findElementById("com.mobilefootie.wc2010:id/btnOK").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
