package BBCNews;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class AddTopicTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "8");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "bbc.mobile.news.uk");
        desiredCapabilities.setCapability("appActivity", "bbc.mobile.news.v3.app.TopLevelActivity");
        desiredCapabilities.setCapability("noReset", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        driver.findElementByXPath("//android.widget.TextView[@text=\"My News\"]").click();
        driver.findElementByAccessibilityId("Edit My News").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Add Topics\"]").click();
        driver.findElementByXPath("//android.support.v7.widget.RecyclerView[@resource-id='bbc.mobile.news.uk:id/recyclerview']/android.widget.LinearLayout[1]/android.widget.ImageView[1]//android.widget.FrameLayout[@content-desc="Button: Add China to My News"]/android.widget.LinearLayout[1]/android.widget.ImageView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
