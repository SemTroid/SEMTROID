package NetworkMo;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class TwoTest {

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
        driver.findElementByXPath("//android.widget.TextView[@text=\"Advanced options\"]").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView[@resource-id='ca.rmen.android.networkmonitor:id/recycler_view']/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.ImageView[1]").click();
        driver.findElementById("ca.rmen.android.networkmonitor:id/switchWidget").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Fields to monitor\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Speed test\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView[@resource-id='ca.rmen.android.networkmonitor:id/recycler_view']/android.widget.LinearLayout[5]/android.widget.LinearLayout[1]/android.widget.ImageView[1]").click();
        //;
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Notification ringtone\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Default notification sound\"]").click();
        driver.findElementById("android:id/button1").click();
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Import a database\"]").click();
        driver.navigate().back();
        new TouchAction(driver).longPress(PointOption.point(384, 1081)).moveTo(PointOption.point(384, 262)).release().perform();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Send yourself reports by e-mail\"]").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
