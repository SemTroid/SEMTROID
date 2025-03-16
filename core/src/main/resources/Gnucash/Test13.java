package main.resources.Gnucash;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class Test13 {
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
        driver.findElementByXPath("//android.widget.TextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByAccessibilityId("Navigation drawer opened").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Settings\"]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[8]").click();
        driver.findElementById("android:id/edit").sendKeys("test");
        driver.findElementById("android:id/button1").click();
        new TouchAction(driver).press(PointOption.point(540, 1800)).moveTo(PointOption.point(540, 0)).release().perform();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[5]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[5]").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 38、39、42(False)、43(False)、44、45、48、49、50、51、52、53、54、55、56
// 60(False)、61(False)、62(False)、63(False)、64(False)、65(False)