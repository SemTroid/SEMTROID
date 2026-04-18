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
    steps: 点击conversation图标 -> 点击English话筒图标 -> 点击espanol话筒图标 -> 点击Auto话筒图标 -> 点击手掌图标
    breakage: 39 40 41 43 定位不到
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
        desiredCapabilities.setCapability("udid", "emulator-5556");
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
//         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.google.android.apps.translate:id/btn_speech_icon").click(); //android.widget.Button[@content-desc="Conversation"]/android.widget.LinearLayout/android.widget.ImageView
        driver.findElementByXPath("(//android.widget.Button[@content-desc=\"Tap to speak button\"])[1]").click();
        driver.findElementByXPath("(//android.widget.Button[@content-desc=\"Tap to speak button\"])[3]").click();
        driver.findElementById("com.google.android.apps.translate:id/auto_voice_button").click();
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Introduction\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
