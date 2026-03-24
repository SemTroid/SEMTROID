package main.resources.BikeWale;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

// From V4.2.0.45
// My News -> Edit My News -> Search for topics -> sendKeys("Health") -> Close search
public class RideStyleTest {

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
        driver.findElementById("com.bikewale.app:id/tvRideStyle").click();
        driver.findElementById("com.bikewale.app:id/tv_Street").click();
        driver.findElementById("com.bikewale.app:id/ivMenuRight").click();
        driver.findElementById("android:id/text1").click();
        driver.findElementById("android:id/button1").click();
        new TouchAction(driver).longPress(PointOption.point(370,1870)).moveTo(PointOption.point(370,80)).release().perform();
        driver.findElementById("com.bikewale.app:id/sports_tv").click();
        driver.findElementById("com.bikewale.app:id/rl_disc").click();
        driver.findElementById("com.bikewale.app:id/apply_filter").click();

    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
