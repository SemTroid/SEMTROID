package main.resources.Gnucash;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Test6 {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "org.gnucash.android");
        desiredCapabilities.setCapability("appActivity", "org.gnucash.android.ui.account.AccountsActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementByAccessibilityId("Navigation drawer opened").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Reports\"]").click();
        driver.findElementById("org.gnucash.android:id/btn_line_chart").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementById("org.gnucash.android:id/menu_group_reports_by").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/title'])[1]").click();
        driver.findElementByAccessibilityId("More options").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/checkbox'])[1]").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 36、38