package main.resources.Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class EightTest {
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
        desiredCapabilities.setCapability("udid", "emulator-5554");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementById("com.ichi2.anki:id/fab_expand_menu_button").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[4]").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[3]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[4]").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[4]").click();
        driver.findElementById("com.ichi2.anki:id/multimedia_edit_field_done").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[1]").clear();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[1]").sendKeys("TestFront11");
        driver.findElementById("com.ichi2.anki:id/action_save").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:38