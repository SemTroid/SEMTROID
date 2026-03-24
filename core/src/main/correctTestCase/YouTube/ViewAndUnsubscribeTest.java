package main.correctTestCase.YouTube;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
    app: YouTube
    version: V 19.04.36
    steps: 点击subscriptions图标 -- 点击all文本 -- 点击下箭头 -- 点击unsubscribe图标
 */

public class ViewAndUnsubscribeTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.google.android.youtube");
        desiredCapabilities.setCapability("appActivity", "com.google.android.apps.youtube.app.watchwhile.WatchWhileActivity");
        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id=\"com.google.android.youtube:id/image\"])[4]").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"All\"]").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Options\"]/android.widget.ImageView[2]").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Unsubscribe from Soccer AM.\"]/android.widget.ImageView").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
