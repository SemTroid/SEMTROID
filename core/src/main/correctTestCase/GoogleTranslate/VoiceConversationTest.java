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
    steps: 点击conversation图标 -> 点击English话筒图标 -> 点击espanol话筒图标 -> 点击Auto话筒图标 -> 点击手掌图标
 */

public class VoiceConversationTest {
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
        driver.findElementById("com.google.android.apps.translate:id/start_side_sub_hero_button").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Listens for English .\"]").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Listens for español Spanish.\"]").click();
        driver.findElementById("com.google.android.apps.translate:id/auto_voice_button").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Introduction\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
