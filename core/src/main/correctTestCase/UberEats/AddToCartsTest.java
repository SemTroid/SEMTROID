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
    steps: 点击搜索 -- 输入Papa Johns Pizza-- 点击Papa Johns Pizza -- 点击Search图标 -- 输入Pepsi -- 点击Pepsi -- 点击加入购物车
 */

public class AddToCartsTest {
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
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_query\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_query\"]").sendKeys("Papa Johns Pizza");
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.ubercab.eats:id/ub__store_slim_row_title\"]").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Search this store\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_query\" and @text=\"Search the menu\"]").sendKeys("Pepsi");
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"20 Ounce Pepsi\"]").click();
        driver.findElementByXPath("//android.widget.Button[@resource-id=\"com.ubercab.eats:id/ub__storefront_menu_item_cart_button\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
