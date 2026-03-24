package main.correctTestCase.UberEats;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/*
    app: Uber Eats
    version: V 6.210.10000
    steps: 点击Account文本 -- 点击Favorite -- 点击7-Eleven -- 点击see all文本
 */

public class ViewMyFavoritesTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.ubercab.eats");
        desiredCapabilities.setCapability("appActivity", "com.ubercab.eats.core.activity.RootActivity");
        desiredCapabilities.setCapability("noReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@text=\"Account\"]").click();
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id=\"com.ubercab.eats:id/component_grid_card_item_start_image\"])[1]").click();
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.ubercab.eats:id/ub__mini_store_title\"]").click();
        driver.findElementById("com.ubercab.eats:id/ub__storefront_subsection_cta_icon").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
