package main.resources.Anki;

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
    public void sampleTest() throws InterruptedException{
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[7]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("Group1");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("Group2");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("1 11");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("15");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("2");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("5");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("100");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("90");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("150");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("90");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("36502");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("11");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("10");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("2");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("9");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[2]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("70");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[3]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
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
// Break:38√/137√