package BBCNews;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class HealthAddAndDeleteTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
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
        //;
        driver.findElementById("bbc.mobile.news.uk:id/action_search").click();
        driver.findElementById("bbc.mobile.news.uk:id/search").sendKeys("Health");
        driver.findElementByXPath("//android.widget.TextView[@resource-id='bbc.mobile.news.uk:id/chip_item' and @text=\"Health\"]").click();
        driver.findElementByAccessibilityId("Add topic").click();
        driver.findElementById("bbc.mobile.news.uk:id/menu_edit_followed").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView[@resource-id='bbc.mobile.news.uk:id/recyclerview']/android.widget.RelativeLayout[3]/android.widget.ImageView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
