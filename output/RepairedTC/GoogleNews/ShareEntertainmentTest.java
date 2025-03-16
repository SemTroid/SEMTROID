package GoogleNews;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class ShareEntertainmentTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.google.android.apps.magazines");
        desiredCapabilities.setCapability("appActivity", "com.google.apps.dots.android.newsstand.home.HomeActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.google.android.apps.magazines:id/tab_store_text").click();
        driver.findElementById("com.google.android.apps.magazines:id/text").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='com.google.android.apps.magazines:id/recycler_view']/android.widget.LinearLayout[6]/android.widget.FrameLayout[2]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Maxim\"]").click();
        driver.findElementByXPath("//android.view.ViewGroup[@resource-id='com.google.android.apps.magazines:id/toolbar']/android.support.v7.widget.LinearLayoutCompat[1]/android.widget.ImageView[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Share\"]").click();
        //;
        driver.findElementByXPath("//android.widget.TextView[@text=\"Messaging\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
