package main.correctTestCase.GoogleTranslate;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/*
    app: GoogleTranslate
    version: V 6.52.0.511814410.5
    steps: 点击menu图标 -> 点击settings文字 -> 点击tap to translate文字 -> 点击enable文字 -> 点击返回图标 -> 点击clear history文字
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

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.google.android.apps.translate:id/og_apd_internal_image_view").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("(//android.widget.TextView)[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Use Tap to Translate\"]").click();
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]").click();
        // ;
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
