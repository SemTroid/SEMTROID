package main.resources.Anki;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class EighteenTest {
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
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[6]").click();
        driver.findElementById("com.ichi2.anki:id/action_add_new_note_type").click();
        driver.findElementById("com.ichi2.anki:id/dropdown_deck_name").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/dropdown_deck_name'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        new TouchAction(driver).longPress(ElementOption.element(driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/model_list_item_1'])[1]"))).perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/action_confirm").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/model_list_item_1'])[1]").click();
        driver.findElementById("com.ichi2.anki:id/action_add_new_model").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("string");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementByClassName("android.widget.ImageButton").click();
        new TouchAction(driver).longPress(ElementOption.element(driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/model_list_item_1'])[4]"))).perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[3]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("test");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        new TouchAction(driver).longPress(ElementOption.element(driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/model_list_item_1'])[4]"))).perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementByClassName("android.widget.ImageButton").click();
        driver.findElementById("com.ichi2.anki:id/fab_expand_menu_button").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[1]").clear();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[1]").sendKeys("TestFront16");
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[2]").clear();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[2]").sendKeys("TestBack16");
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[3]").clear();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/id_note_editText'])[3]").sendKeys("string16");
        driver.findElementById("com.ichi2.anki:id/action_save").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:41/42/44/50/53/56/58/59