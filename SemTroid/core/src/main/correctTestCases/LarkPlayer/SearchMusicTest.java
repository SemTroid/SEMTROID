package main.correctTestCases.LarkPlayer;

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
    steps: 点击music文字 -> 点击search图标 -> sendkeys("queen") -> 点击×图标
 */

public class SearchMusicTest {
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
        driver.findElementById("com.dywx.larkplayer:id/search_btn").click();
        driver.findElementByXPath("//android.widget.EditText[@text=\"Search My Music & Video\"]").sendKeys("Queen");
        driver.findElementByXPath("//android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageButton").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

