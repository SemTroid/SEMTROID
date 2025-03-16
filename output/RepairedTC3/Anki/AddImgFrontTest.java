package Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class AddImgFrontTest {

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
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.ichi2.anki:id/fab_expand_menu_button").click();
        driver.findElementById("com.ichi2.anki:id/add_note_action").click();
        driver.findElementByAccessibilityId("Attach multimedia content to the Front field").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Add image\"]").click();
        driver.findElementByXPath("//android.widget.Button[@text=\"GALLERY\"]").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementById("com.ichi2.anki:id/multimedia_edit_field_done").click();
        driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Back\"]").clear();
        driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Back\"]").sendKeys("TestBack5");
        driver.findElementById("com.ichi2.anki:id/action_save").click();
        driver.findElementByAccessibilityId("Attach multimedia content to the Front field").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Add image\"]").click();
        driver.findElementByXPath("//android.widget.Button[@text=\"CAMERA\"]").click();
        driver.findElementById("com.android.camera2:id/shutter_button").click();
        driver.findElementById("com.android.camera2:id/done_button").click();
        driver.findElementById("com.ichi2.anki:id/md_buttonDefaultNegative").click();
        driver.findElementById("com.ichi2.anki:id/multimedia_edit_field_done").click();
        driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Back\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@content-desc=\"Back\"]").sendKeys("TestBack6");
        driver.findElementById("com.ichi2.anki:id/action_save").click();
        driver.findElementByAccessibilityId("Navigate up").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
