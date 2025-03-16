package YouTube;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ViewAndUnsubscribeTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.google.android.youtube");
        desiredCapabilities.setCapability("appActivity", "com.google.android.apps.youtube.app.watchwhile.WatchWhileActivity");
        desiredCapabilities.setCapability("noReset", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Subscriptions\"]/android.widget.FrameLayout[1]/android.widget.ImageView[1]").click();
        driver.findElementById("com.google.android.youtube:id/channels_button").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Options\"]/android.widget.ImageView[2]").click();
        driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"Unsubscribe from Soccer AM.\"]/android.widget.ImageView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
