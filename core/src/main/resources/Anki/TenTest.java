package main.resources.Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TenTest {
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
        driver.findElementById("com.ichi2.anki:id/fab_expand_menu_button").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[1]").clear();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[1]").sendKeys("TestFront13");
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[2]").clear();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[2]").sendKeys("TestBack13");
        driver.findElementById("com.ichi2.anki:id/CardEditorTagText").click();
        driver.findElementByXPath("(//android.widget.TextView)[3]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultNegative").click();
        driver.findElementByXPath("(//android.widget.TextView)[3]").click();
        driver.findElementById("android:id/input").clear();
        driver.findElementById("android:id/input").sendKeys("Tag1");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementByXPath("(//android.widget.TextView)[2]").click();
        driver.findElementById("com.ichi2.anki:id/search_src_text").clear();
        driver.findElementById("com.ichi2.anki:id/search_src_text").sendKeys("ffff");
        driver.findElementById("com.ichi2.anki:id/search_close_btn").click();
        driver.findElementById("com.ichi2.anki:id/search_src_text").clear();
        driver.findElementById("com.ichi2.anki:id/search_src_text").sendKeys("Tag");
        driver.findElementByXPath("(//android.widget.ImageView)[2]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementByClassName("android.widget.ImageButton").click();
        driver.findElementByXPath("(//android.widget.TextView)[4]").click();
        driver.findElementByXPath("(//android.widget.TextView)[4]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/action_save").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:42√/46√/53/54/55/56/57/58/62√/65√