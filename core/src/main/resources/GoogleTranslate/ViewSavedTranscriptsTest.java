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
    steps: 查看保存的翻译：点击menu图标 -> 点击Saved transcripts文本 -> 点击排序图标 -> 点击sort alphabetically文本
    breakage: 39 menu图标 定位不到   40 Saved transcripts 定位不到    42 sort alphabetically 定位不到
 */
public class ViewSavedTranscriptsTest {
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
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
//         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Open navigation drawer\"]").click();
//        driver.findElementByXPath("(//android.widget.CheckedTextView)[3]").click();
//        driver.findElementById("com.google.android.apps.translate:id/listen_transcripts_sort").click();
//        driver.findElementById("com.google.android.apps.translate:id/sort_by_alphabetically").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
