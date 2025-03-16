package AntenPod;

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
        desiredCapabilities.setCapability("appPackage", "de.danoeh.antennapod");
        desiredCapabilities.setCapability("appActivity", "de.danoeh.antennapod.activity.OnlineFeedViewActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByAccessibilityId("Open menu").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Queue\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Lock Queue\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Lock Queue\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementById("de.danoeh.antennapod:id/refresh_item").click();
        driver.findElementByAccessibilityId("More options").click();
        //;
        //;
        //;
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Date\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Old → New\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Date\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New → Old\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Duration\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Short → Long\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Duration\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Long → Short\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Episode title\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"A → Z\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Episode title\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Z → A\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Podcast title\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"A → Z\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Podcast title\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Z → A\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Random\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Smart Shuffle\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Old → New\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Smart Shuffle\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"New → Old\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Keep sorted\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Sort\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Keep sorted\"]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Clear Queue\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Clear Queue\"]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByAccessibilityId("More options").click();
        //;
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
