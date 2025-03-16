package main.resources.LarkPlayer;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/*
    app: LarkPlayer
    version: V 5.39.66
    steps: 点击music文字 -> 点击manage your scan list文字 -> 点击filter audio files less than 60s文字 -> 点击30s文字 -> return ->点击filter audio files less than 100k文字 -> 点击50k文字
    breakage: 39 music文本 定位错    40 manage文本 定位不到    41 less than 60s文本 定位错    44 less than 100k文本 定位错
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
        driver.findElementByXPath("(//android.widget.TextView)[11]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Manage your scan list\"]").click();
        driver.findElementById("com.dywx.larkplayer:id/tv_scan_time").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"30s\"]").click();
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]").click();
        driver.findElementById("com.dywx.larkplayer:id/tv_scan_size").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"50k\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
