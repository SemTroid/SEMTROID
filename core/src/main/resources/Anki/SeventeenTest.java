package main.resources.Anki;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class SeventeenTest {
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
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[3]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[4]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[8]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/control'])[2]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultNegative").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='com.ichi2.anki:id/title'])[7]").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/buttonDefaultNegative").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//
//Break:36/37/40/41/44/47/48/49/52x/53x(元素删除，没有下面两个操作)