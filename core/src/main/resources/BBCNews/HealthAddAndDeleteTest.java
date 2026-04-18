package main.resources.BBCNews;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// From V4.2.0.45
// My News文本 -> More options图标 -> Search文本 -> sendKeys("Health") -> Health文本 -> Add topic图标 -> Edit My News图标 -> Delete图标(Health)
// 39 My News 定位不到（定位错）    40 More options 元素删除    41 Search 定位不到    46 Delete 定位不到
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
        //desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() {
        driver.findElementByXPath("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Search']").click();
        driver.findElementById("bbc.mobile.news.uk:id/search").sendKeys("Health");
        driver.findElementByXPath("//android.widget.TextView[@text='Health']").click();
        driver.findElementByAccessibilityId("Add topic").click();
        driver.findElementById("bbc.mobile.news.uk:id/menu_edit_followed").click();
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc='Button: Unfollow Health']/android.widget.LinearLayout/android.widget.ImageView[1]").click();
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
