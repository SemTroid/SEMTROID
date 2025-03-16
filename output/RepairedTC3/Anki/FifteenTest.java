package Anki;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class FifteenTest {

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
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@text=\"Default\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Options\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Options group\"]").click();
        driver.findElementById("android:id/text1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Group management\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Add\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("Group1");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Rename\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("Group2");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Restore defaults\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Restore defaults\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Group management\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Set for all subdecks\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Set for all subdecks\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Group management\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Remove\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Remove\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New cards\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Steps (in minutes)\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("1 11");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Order\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"New cards in random order\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New cards/day\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("15");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Graduating interval\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("2");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Easy interval\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("5");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Starting ease\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("100");
        driver.findElementById("android:id/button1").click();
        driver.findElementById("android:id/checkbox").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Reviews\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Maximum reviews/day\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("90");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Easy bonus\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("150");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Interval modifier\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("90");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Maximum interval\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("36502");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[5]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Lapses\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Steps (in minutes)\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("11");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New interval\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("10");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Minimum interval\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("2");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Leech threshold\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("9");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Leech action\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Tag only\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"General\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Maximal answer time\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("70");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[3]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[4]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.navigate().back();
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Deck description (affects current deck only)\"]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("note");
        driver.findElementById("android:id/button1").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
