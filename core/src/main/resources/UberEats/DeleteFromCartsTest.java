package main.resources.UberEats;

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
    version: V 6.170.10002
    steps: 点击购物车 -- 点击Papa Johns Pizza详情图标 -- (下滑) -- 点击删除图标
    breakage: 41 点击详情图标 定位不到
 */
public class DeleteFromCartsTest {
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
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id=\"com.ubercab.eats:id/navigation_bar_item_icon_view\"])[4]").click();
        driver.findElementByXPath("//android.widget.ImageView[@resource-id=\"com.ubercab.eats:id/ub__cart_cta_arrow\"]").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Remove item\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
