package GoogleNews;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class ManageAccountTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.google.android.apps.magazines");
        desiredCapabilities.setCapability("appActivity", "com.google.apps.dots.android.newsstand.activity.DeepLinkActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5554");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.google.android.apps.magazines:id/og_apd_internal_image_view").click();
        driver.findElementById("com.google.android.apps.magazines:id/og_apd_internal_image_view").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"News settings\"]").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementById("com.google.android.apps.magazines:id/og_apd_internal_image_view").click();
        driver.findElementById("com.google.android.apps.magazines:id/my_account_chip").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
