package AntenPod;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class TwentyFiveTest {

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
        driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='de.danoeh.antennapod:id/main_view']/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Mark as played\"]").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='de.danoeh.antennapod:id/main_view']/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Share…\"]").click();
        driver.navigate().back();
        driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='de.danoeh.antennapod:id/main_view']/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Remove from Favorites\"]").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='de.danoeh.antennapod:id/main_view']/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Open Podcast\"]").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView[@resource-id='de.danoeh.antennapod:id/recyclerView']/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout[2]/android.widget.TextView[1]").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='de.danoeh.antennapod:id/main_view']/android.widget.LinearLayout[1]/android.view.ViewGroup[1]/androidx.appcompat.widget.LinearLayoutCompat[1]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Add to Queue\"]").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
