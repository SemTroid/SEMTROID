package UberEats;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

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
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.ubercab.eats:id/ub__search_query").click();
        driver.findElementById("com.ubercab.eats:id/ub__search_query").sendKeys("Papa Johns Pizza");
        driver.findElementById("com.ubercab.eats:id/ub__store_slim_row_title").click();
        driver.findElementById("com.ubercab.eats:id/ub__storefront_search_action_button").click();
        driver.findElementByXPath("//android.widget.EditText[@text=\"Search the menu\"]").sendKeys("Pepsi");
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"20 Ounce Pepsi\"]").click();
        driver.findElementById("com.ubercab.eats:id/ub__storefront_menu_item_cart_button").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
