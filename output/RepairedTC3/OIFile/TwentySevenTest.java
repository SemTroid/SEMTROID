package OIFile;

import io.appium.java_client.android.AndroidDriver;
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
        desiredCapabilities.setCapability("appPackage", "org.openintents.filemanager");
        desiredCapabilities.setCapability("appActivity", "org.openintents.filemanager.FileManagerActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@text=\"AATest\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[6]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.navigate().back();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[6]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
