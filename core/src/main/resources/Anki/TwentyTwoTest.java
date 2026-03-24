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

public class TwentyTwoTest {    private AndroidDriver driver;

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
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[5]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[8]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[8]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[8]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[8]").click();
        driver.findElementById("android:id/button2").click();
        new TouchAction(driver).press(PointOption.point(400, 750)).moveTo(PointOption.point(400, 300)).release().perform();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementByXPath("(//android.widget.CheckedTextView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("(//android.widget.ImageButton)[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Break:42/44/46/48/50/52/54/56/58/61/63