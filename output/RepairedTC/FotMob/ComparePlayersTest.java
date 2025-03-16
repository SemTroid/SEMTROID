package FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class ComparePlayersTest {

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
        driver.findElementById("com.mobilefootie.wc2010:id/menu_search").click();
        driver.findElementById("com.mobilefootie.wc2010:id/editText_search").sendKeys("Lionel Messi");
        driver.findElementByXPath("//android.widget.TextView[@resource-id='com.mobilefootie.wc2010:id/textView' and @text=\"Lionel Messi\"]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/menu_enter_pvp_mode").click();
        driver.findElementById("com.mobilefootie.wc2010:id/textView_player2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Cristiano Ronaldo\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Major League Soccer\"]").click();
        driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Saudi Pro League\"]").click();
        driver.findElementByXPath("//hierarchy/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
