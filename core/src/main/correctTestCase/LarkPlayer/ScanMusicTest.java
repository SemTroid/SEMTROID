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
    steps: 点击music文字 -> 点击manage your scan list文字 -> 点击filter audio files less than 60s文字 -> 点击30s文字 -> return ->点击filter audio files less than 100k文字 -> 点击50k文字
 */

public class ScanMusicTest {
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
        driver.findElementByXPath("//android.widget.TextView[@text=\"Find more audios by folders and filters\"]").click();
        driver.findElementById("com.dywx.larkplayer:id/tv_scan_time_tips").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"30s\"]").click();
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]").click();
        driver.findElementById("com.dywx.larkplayer:id/tv_scan_size_tips").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"50k\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
