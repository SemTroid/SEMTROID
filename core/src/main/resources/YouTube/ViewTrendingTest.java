package main.resources.YouTube;

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
    version: V 17.49.37
    steps: 点击指南针图标 -- 点击Trending图标 -- 点击music
    breakage: 40-Trending-定位错  41 点击music 定位错
 */
public class ViewTrendingTest {
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
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Explore Menu\"]/android.widget.ImageView").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Trending\"]/android.widget.ImageView").click();
        driver.findElementByXPath("//android.widget.HorizontalScrollView[@resource-id=\"com.google.android.youtube:id/tabs_bar\"]/android.widget.LinearLayout/android.widget.Button[2]").click();
        }

    @After
    public void tearDown() {
        driver.quit();
    }
}
