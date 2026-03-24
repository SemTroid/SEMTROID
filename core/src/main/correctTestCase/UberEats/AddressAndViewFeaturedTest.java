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
    steps: 点击New York Life Insurance文本 -- 点击搜索框 -- 输入New York Life Insurance -- 点击New York Life Insurance -- 点击Save and Continue -- 点击see all
 */

public class AddressAndViewFeaturedTest {
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
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.ubercab.eats:id/ub__address_description\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_query\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@resource-id=\"com.ubercab.eats:id/ub__search_query\"]").sendKeys("New York Life Insurance");
        driver.findElementByXPath("//android.widget.TextView[@resource-id=\"com.ubercab.eats:id/ub__delivery_location_list_title\" and @text=\"New York Life Insurance\"]").click();
        driver.findElementByAccessibilityId("Save and continue").click();
        driver.findElementByXPath("(//android.view.View[@text=\"See all\"])[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
