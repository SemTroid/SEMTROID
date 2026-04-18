package main.resources.MyNetDiary;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class add {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        // W3C标准的capabilities
        desiredCapabilities.setCapability("platformName", "Android");

        // Appium特定的options
        desiredCapabilities.setCapability("appium:platformVersion", "11.0");
        desiredCapabilities.setCapability("appium:deviceName", "device");
        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
        desiredCapabilities.setCapability("appium:appPackage", "com.fourtechnologies.mynetdiary.ad");
        desiredCapabilities.setCapability("appium:appActivity", "com.mynetdiary.ui.MainActivity");
        desiredCapabilities.setCapability("appium:noReset", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:dontStopAppOnReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void test() throws InterruptedException {

        driver.findElementByAccessibilityId("Add").click();//主页下面的加号
        driver.findElementByAccessibilityId("Breakfast").click();//breakfast
        driver.findElementByAccessibilityId("Navigate up").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"com.fourtechnologies.mynetdiary.ad:id/design_menu_item_text\" and @text=\"Diet Dashboard\"]").click();
        driver.findElementByAccessibilityId("Add").click();
        driver.findElementByAccessibilityId("Lunch").click();





    }
    @After
    public void tearDown() {
        driver.quit();
    }
}