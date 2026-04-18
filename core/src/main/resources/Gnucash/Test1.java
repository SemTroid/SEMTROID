package main.resources.Gnucash;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Test1 {
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
        driver.findElementById("org.gnucash.android:id/fab_create_account").click();
        driver.findElementById("org.gnucash.android:id/input_account_name").sendKeys("test");
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementById("org.gnucash.android:id/input_account_description").sendKeys("test");
        driver.findElementById("org.gnucash.android:id/checkbox_placeholder_account").click();
        driver.findElementById("org.gnucash.android:id/checkbox_placeholder_account").click();
        driver.findElementById("org.gnucash.android:id/checkbox_default_transfer_account").click();
        driver.findElementById("org.gnucash.android:id/checkbox_default_transfer_account").click();
        driver.findElementById("org.gnucash.android:id/menu_save").click();
        driver.findElementByXPath("(//*[@resource-id='org.gnucash.android:id/primary_text'])[1]").click();
        driver.findElementById("org.gnucash.android:id/menu_favorite_account").click();
        driver.findElementById("org.gnucash.android:id/fab_create_transaction").click();
        driver.findElementById("org.gnucash.android:id/input_transaction_name").sendKeys("test");
        driver.findElementById("org.gnucash.android:id/input_transaction_amount").sendKeys("520");
        driver.findElementById("org.gnucash.android:id/input_transaction_type").click();
        driver.findElementById("org.gnucash.android:id/input_transaction_type").click();
        driver.findElementById("org.gnucash.android:id/input_date").click();
        driver.findElementById("org.gnucash.android:id/done").click();
        driver.findElementById("org.gnucash.android:id/input_time").click();
        driver.findElementById("org.gnucash.android:id/done_button").click();
        driver.findElementById("org.gnucash.android:id/input_description").sendKeys("test");
        driver.findElementById("org.gnucash.android:id/input_recurrence").click();
        driver.findElementById("org.gnucash.android:id/done").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 55(False)、60(False)

// 57(NP,修复后的定位语句与原来一致)