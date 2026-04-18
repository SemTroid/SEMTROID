package main.correctTestCase.YouTube;

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
    version: V 19.04.36
    steps: 点击头像 -- 点击turn on Incognito图标 -- 断言 you're incognito
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
        driver.findElementByXPath("//android.widget.ImageView[@resource-id=\"com.google.android.youtube:id/pivot_bar_thumbnail\"]").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Turn on Incognito\"]/android.widget.ImageView").click();
        Assert.assertEquals("You're incognito", driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.google.android.youtube:id/text\" and @text=\"You're incognito\"]").getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
