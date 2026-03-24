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
    steps: 点击搜索 -- 输入McDonald-- 点击餐厅 -- 点击McDonald's（根据营业的餐厅选择） -- 点击MoreOptions图标 -- 点击Add to Favorites图标
 */

public class AddFavoriteRestaurantTest {
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
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_query\"]").sendKeys("McDonald");
        driver.findElementByXPath("//android.widget.TextView[@text=\"Restaurants\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.ubercab.eats:id/ub__store_slim_row_title\"]").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"More options\"]").click();
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id=\"com.ubercab.eats:id/start_image\"])[4]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
