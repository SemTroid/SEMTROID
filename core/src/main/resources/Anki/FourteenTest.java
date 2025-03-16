package main.resources.Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FourteenTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.ichi2.anki");
        desiredCapabilities.setCapability("appActivity", "com.ichi2.anki.IntentHandler");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[4]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[3]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[6]").click();
        driver.findElementById("com.ichi2.anki:id/action_hide_whiteboard").click();
        driver.findElementById("com.ichi2.anki:id/action_hide_whiteboard").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[7]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[5]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultNegative").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[5]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//点击牌组、右上角、Replay()、右上角、Bury()、Bury Card、右上角、Bury()、右上角、Suspend()、Suspend card、右上角、Suspend()、右上角、白板()、hide、hide、右上角、clear、关闭白板()、右上角、deletenote()、取消()、右上角、delete()、确定()、返回
//Break(√):36/38/41/43/46/48/54/56/57/59/60