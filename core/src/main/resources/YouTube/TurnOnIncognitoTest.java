package main.resources.YouTube;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
    app: YouTube
    version: V 17.49.37
    steps: 点击头像 -- 点击turn on Incognito图标 -- 断言 you're incognito
    breakage: 40 头像 定位不到   41 Incognito 定位不到
 */
public class TurnOnIncognitoTest {
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
        driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Account\"]").click();
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id=\"com.google.android.youtube:id/thumbnail\"])[2]").click();
        Assert.assertEquals("You're incognito", driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.google.android.youtube:id/text\" and @text=\"You're incognito\"]").getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
