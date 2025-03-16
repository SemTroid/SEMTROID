package FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class FollowAndUnfollowPlayerTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.mobilefootie.wc2010");
        desiredCapabilities.setCapability("appActivity", "com.mobilefootie.fotmob.gui.v2.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Following\"]/android.widget.FrameLayout[1]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Players\"]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/no_favorites_added").click();
        driver.findElementById("com.mobilefootie.wc2010:id/imageView_back").click();
        driver.findElementById("com.mobilefootie.wc2010:id/menu_add_favorites").click();
        driver.findElementById("com.mobilefootie.wc2010:id/textView_title").click();
        driver.findElementByXPath("//android.widget.Button[@text=\"Following\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
