package main.correctTestCase.LarkPlayer;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/*
    app: LarkPlayer
    version: V 5.69.5
    steps: 点击music文字 -> 点击playlists文字 -> 点击new playlists文字 -> 输入TestList -> 点击create文字 -> 点击×图标 -> 点击add songs文字
 */

public class CreateListTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.dywx.larkplayer");
        desiredCapabilities.setCapability("appActivity", "com.dywx.larkplayer.main.MainActivity");
        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("(//android.widget.TextView)[14]").click();
        driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Playlists\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New Playlist\"]").click();
        driver.findElementById("com.dywx.larkplayer:id/edittext_playlist_name").sendKeys("TestList");
        driver.findElementById("com.dywx.larkplayer:id/dialog_playlist_save").click();
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"More Actions\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Add Songs\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
