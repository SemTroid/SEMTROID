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
    steps: 点击me文字 -> 点击settings图标 -> 点击playback simultaneously with other apps文字 -> 点击New media notification文字
    breakage: 39 me文本 定位错   41 playback 定位不到    42 New 定位错
 */
public class AdjustSettingsTest {
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
        desiredCapabilities.setCapability("udid", "emulator-5554");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("(//android.widget.TextView)[14]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[@resource-id=\"com.dywx.larkplayer:id/iv_setting\"]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.TextView[4]").click();
        driver.findElementByXPath("(//android.widget.TextView)[8]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
