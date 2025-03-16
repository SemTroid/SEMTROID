package main.resources.GoogleTranslate;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
/*
    app: GoogleTranslate
    version: V 6.16.0.03.352678460
    steps: 点击menu图标 -> 点击settings文字 -> 点击tap to translate文字 -> 点击enable文字 -> 点击返回图标 -> 点击clear history文字
    breakage: 39 menu图标 定位不到  40 settings文本 定位不到  41 tap to translate文本 定位错  42 enable文本 定位不到  44 clear history 元素删除
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
        desiredCapabilities.setCapability("appPackage", "com.google.android.apps.translate");
        desiredCapabilities.setCapability("appActivity", "com.google.android.apps.translate.TranslateActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("(//android.widget.TextView)[2]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Enable\"]").click();
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]").click();
        driver.findElementByXPath("//android.widget.Button[@text=\"CLEAR HISTORY\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
