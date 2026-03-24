package main.correctTestCase.YouTube;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
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
    steps: 点击搜索按钮 -- 输入soccer am -- 点击soccer am -- (下滑) -- 点击订阅 -- 点击More Options按钮 -- 点击share -- 点击copy link图标
 */

public class SubscribeAndShareTest {
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
    public void sampleTest() {
        driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Search\"]").click();
        driver.findElementById("com.google.android.youtube:id/search_edit_text").sendKeys("soccer am");
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.google.android.youtube:id/text\" and @text=\"soccer am\"]").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Subscribe to Soccer AM.\"]").click();
        driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Action menu\"]").click();
        driver.findElementById("com.google.android.youtube:id/list_item_text").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Copy link\"]/android.widget.ImageView").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
