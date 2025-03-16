package LarkPlayer;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

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
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.LinearLayout[@resource-id='com.dywx.larkplayer:id/tab_layout']/android.widget.RelativeLayout[1]/android.widget.TextView[1]").click();
        driver.findElementByAccessibilityId("Playlists").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New Playlist\"]").click();
        driver.findElementById("com.dywx.larkplayer:id/edittext_playlist_name").sendKeys("TestList");
        driver.findElementById("com.dywx.larkplayer:id/dialog_playlist_save").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementById("com.dywx.larkplayer:id/tv_songs").click();// 修复错误
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
