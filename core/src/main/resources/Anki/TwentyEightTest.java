package main.resources.Anki;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TwentyEightTest {
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
        new TouchAction(driver).longPress(ElementOption.element(driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[1]"))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).release().perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("5");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        new TouchAction(driver).longPress(ElementOption.element( driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[1]"))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).release().perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[3]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("55");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        new TouchAction(driver).longPress(ElementOption.element( driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[1]"))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).release().perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("2");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.navigate().back();
        new TouchAction(driver).longPress(ElementOption.element( driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[2]"))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10))).release().perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[5]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("2");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.navigate().back();
        new TouchAction(driver).longPress(ElementOption.element( driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[2]"))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10))).release().perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[6]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("10");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.navigate().back();
        new TouchAction(driver).longPress(ElementOption.element( driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[2]"))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10))).release().perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[7]").click();
        driver.findElementByClassName("android.widget.EditText").clear();
        driver.findElementByClassName("android.widget.EditText").sendKeys("2");
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.navigate().back();
        new TouchAction(driver).longPress(ElementOption.element( driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/deckpicker_name'])[2]"))).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(10))).release().perform();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[8]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/studyoptions_start").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:40/41/44/46/47/50/52/53/56/59/60/63/66/67/70/73/74/77/80/81/82