package main.resources.Gnucash;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Test8 {
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
        driver.findElementByXPath("//android.widget.TextView[@text=\"Scheduled Actions\"]").click();
        driver.findElementByXPath("//android.widget.HorizontalScrollView[@resource-id=\"org.gnucash.android:id/tab_layout\"]/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]").click();
        driver.findElementById("org.gnucash.android:id/menu_add_scheduled_export").click();
        driver.findElementById("org.gnucash.android:id/radio_xml_format").click();
        driver.findElementById("org.gnucash.android:id/radio_qif_format").click();
        driver.findElementById("org.gnucash.android:id/checkbox_export_all").click();
        driver.findElementById("org.gnucash.android:id/checkbox_export_all").click();
        driver.findElementById("org.gnucash.android:id/checkbox_post_export_delete").click();
        driver.findElementById("org.gnucash.android:id/checkbox_post_export_delete").click();
        driver.findElementById("org.gnucash.android:id/input_recurrence").click();
        driver.findElementById("org.gnucash.android:id/repeat_switch").click();
        driver.findElementById("org.gnucash.android:id/interval").clear();
        driver.findElementById("org.gnucash.android:id/interval").sendKeys("2");
        driver.findElementByXPath("(//android.widget.ToggleButton)[1]").click();
        driver.findElementByXPath("(//android.widget.ToggleButton)[2]").click();
        driver.findElementByXPath("(//android.widget.ToggleButton)[3]").click();
        driver.findElementByXPath("(//android.widget.ToggleButton)[4]").click();
        driver.findElementByXPath("(//android.widget.ToggleButton)[5]").click();
        driver.findElementByXPath("(//android.widget.ToggleButton)[6]").click();
        driver.findElementByXPath("(//android.widget.ToggleButton)[7]").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/spinner_item'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/spinner_item'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/spinner_item'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/spinner_item'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/spinner_item'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/spinner_item'])[1]").click();
        driver.findElementById("org.gnucash.android:id/done").click();
        driver.findElementById("org.gnucash.android:id/menu_save").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 36、41(False)、42(False)、59(有Break未找到)、62、64(False)

// 46(NP)
