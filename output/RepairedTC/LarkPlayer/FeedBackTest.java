package LarkPlayer;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class FeedBackTest {

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
        driver.findElementByXPath("//android.widget.TextView[@text=\"Me\"]").click();
        new TouchAction(driver).longPress(PointOption.point(370, 970)).moveTo(PointOption.point(370, 80)).release().perform();
        driver.findElementById("com.dywx.larkplayer:id/tv_feedback").click();
        driver.findElementById("com.dywx.larkplayer:id/question_edit").sendKeys("Questions");
        //;
        driver.findElementByXPath("//android.widget.TextView[@text=\"Lyric\"]").click();
        driver.findElementById("com.dywx.larkplayer:id/email_edit").sendKeys("123@gmail.com");
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementById("com.dywx.larkplayer:id/submit").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
