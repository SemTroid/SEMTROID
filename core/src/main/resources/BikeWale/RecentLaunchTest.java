package main.resources.BikeWale;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

// From V4.2.0.45
// My News -> Edit My News -> Search for topics -> sendKeys("Health") -> Close search
public class RecentLaunchTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "deivce");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appWaitActivity", "com.bikewale.app.ui.HomeActivityBikewale.BikewaleHomeActivity");
        desiredCapabilities.setCapability("appActivity", ".ui.HomeActivityBikewale.BikewaleHomeActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() {

        driver.findElementByAccessibilityId("Open navigation drawer").click();
        driver.findElementById("com.bikewale.app:id/tv_recent").click();
        driver.findElementById("com.bikewale.app:id/bike_name").click();
        driver.findElementById("com.bikewale.app:id/cb_add_to_compare").click();
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByAccessibilityId("Open navigation drawer").click();
        driver.findElementById("com.bikewale.app:id/home_icon").click();


    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
