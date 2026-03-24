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

public class TwentySevenTest {
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
        new TouchAction(driver).longPress(ElementOption.element(driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[1]"))).perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("A");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        new TouchAction(driver).longPress(ElementOption.element(driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[1]"))).perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[6]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultNegative").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:37/41/43/44/45/46/47(44~47是break而非被影响的action)