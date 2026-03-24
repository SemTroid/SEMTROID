package main.resources.WifiAnalyzer;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class NinthTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.vrem.wifianalyzer");
        desiredCapabilities.setCapability("appActivity", "com.vrem.wifianalyzer.SplashActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5554");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementByAccessibilityId("Open navigation drawer").click();
        driver.findElementByXPath("(//*[@resource-id='com.vrem.wifianalyzer:id/design_menu_item_text'])[8]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementByXPath("(//android.widget.Button)[1]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[8]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        new TouchAction(driver).press(PointOption.point(540,1800)).moveTo(PointOption.point(540,100)).release().perform();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[10]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[11]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[12]").click();
        driver.navigate().back();
        driver.navigate().back();

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}


