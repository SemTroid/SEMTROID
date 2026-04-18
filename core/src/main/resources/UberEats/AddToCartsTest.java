package main.resources.UberEats;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/*
    app: Uber Eats
    version: V 6.170.10002
    steps: 点击搜索 -- 输入Papa Johns Pizza-- 点击Papa Johns Pizza -- 点击Search图标 -- 输入Pepsi -- 点击Pepsi -- 点击加入购物车
    breakage: 40 搜索 定位不到   43 Search图标 定位不到     44 输入框 定位不到
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
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.ubercab.eats:id/ub__search_bar_text\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_query\"]").sendKeys("Papa Johns Pizza");
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.ubercab.eats:id/ub__store_slim_row_title\"]").click();
        driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"Search this store\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_edittext_search\"]").sendKeys("Pepsi");
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"20 Ounce Pepsi\"]").click();
        driver.findElementByXPath("//android.widget.Button[@resource-id=\"com.ubercab.eats:id/ub__storefront_menu_item_cart_button\"]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
