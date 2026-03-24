package main.correctTestCase.LarkPlayer;

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

/*
    app: LarkPlayer
    version: V 5.69.5
    steps: 点击me文字 ->滑动到底部 -> 点击feedback文字 -> 在my questions输入文字 -> 点击类别下拉框 ->  点击Lyric文本 -> 在email中输入邮箱 -> 点击submit文字
 */

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
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("(//android.widget.TextView)[16]").click();
        new TouchAction(driver).longPress(PointOption.point(370,970)).moveTo(PointOption.point(370,80)).release().perform();
        driver.findElementById("com.dywx.larkplayer:id/tv_feedback").click();
        driver.findElementByXPath("//android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText[1]").sendKeys("Questions");
        // ;
        driver.findElementByXPath("//android.widget.FrameLayout[1]/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.FrameLayout/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText[2]").sendKeys("123@gmail.com");
        new TouchAction(driver).longPress(PointOption.point(370,900)).moveTo(PointOption.point(370,670)).release().perform();
        driver.findElementById("com.dywx.larkplayer:id/submit").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
