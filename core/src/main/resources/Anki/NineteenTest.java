package main.resources.Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class NineteenTest {
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
//        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
//        driver.findElementByXPath("(//android.widget.CheckedTextView)[2]").click();
//        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
//        driver.findElementByXPath("(//android.widget.CheckedTextView)[2]").click();
//        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
//        driver.findElementByXPath("(//android.widget.CheckedTextView)[3]").click();
//        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
//        driver.findElementByXPath("(//android.widget.CheckedTextView)[4]").click();
//        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
//        driver.findElementByXPath("(//android.widget.CheckedTextView)[1]").click();
//        driver.findElementById("com.ichi2.anki:id/action_add_card_from_card_browser").click();
//        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
//        driver.findElementById("com.ichi2.anki:id/action_search").click();
//        driver.findElementById("com.ichi2.anki:id/search_src_text").clear();
//        driver.findElementById("com.ichi2.anki:id/search_src_text").sendKeys("fff");
//        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
//        driver.findElementById("com.ichi2.anki:id/search_src_text").clear();
//        driver.findElementById("com.ichi2.anki:id/search_src_text").sendKeys("test");
//        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[3]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[4]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[5]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[6]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[7]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[8]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[2]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[3]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/tags_dialog_tag_item'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:44/（55/58/61/64x/67/70/73x/76）/80/82/84