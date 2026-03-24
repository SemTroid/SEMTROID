package main.resources.BikeWale;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

// From V4.2.0.45
// My News -> Edit My News -> Search for topics -> sendKeys("Health") -> Close search
public class MileageTest {

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
        //desiredCapabilities.setCapability("autoGrantPermissions", true); // 自动授予权限

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() {
        driver.findElementById("com.bikewale.app:id/tvMileage").click();
        driver.findElementById("com.bikewale.app:id/tvUp_To").click();
        driver.findElementById("com.bikewale.app:id/bike_name").click();
        driver.findElementById("com.bikewale.app:id/iv_arrow").click();

    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
