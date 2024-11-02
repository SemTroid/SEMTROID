package main.resources.Soundboard;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class ForthTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "de.meonwax.soundboard");
        desiredCapabilities.setCapability("appActivity", "de.meonwax.soundboard.MainActivity");
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
        driver.findElementByXPath("(//*[@resource-id='de.meonwax.soundboard:id/title'])[1]").click();
        driver.findElementByXPath("(//android.widget.ImageView)[1]").click();
        driver.findElementByXPath("(//*[@resource-id='de.meonwax.soundboard:id/title'])[2]").click();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}


