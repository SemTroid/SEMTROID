package NetworkMo;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class OneTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "ca.rmen.android.networkmonitor");
        desiredCapabilities.setCapability("appActivity", "ca.rmen.android.networkmonitor.app.main.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@text=\"Fields to monitor\"]").click();
        //;
        //;
        driver.findElementByXPath("//android.widget.TextView[@text=\"WiFi profile\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Mobile profile (GSM)\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Mobile profile (CDMA)\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"WiFi profile\"]").click();
        driver.findElementById("ca.rmen.android.networkmonitor:id/ok").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
