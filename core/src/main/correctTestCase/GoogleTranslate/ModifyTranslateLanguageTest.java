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
    steps: 点击Spanish文字 -> 点击(search)图标 -> sendkeys("chinese") -> 点击chinese文字
 */

public class ModifyTranslateLanguageTest {
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
        driver.findElementById("com.google.android.apps.translate:id/language_button_b").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Search\"]").click();
        driver.findElementById("com.google.android.apps.translate:id/search_src_text").sendKeys("chinese");
        driver.findElementByXPath("//android.widget.TextView[@text=\"Chinese (Simplified)\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
