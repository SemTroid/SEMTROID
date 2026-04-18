package main.resources.NetworkMonitor;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TwoTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "ca.rmen.android.networkmonitor");
        desiredCapabilities.setCapability("appActivity", "ca.rmen.android.networkmonitor.app.main.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[9]").click();
        driver.findElementByXPath("(//*[@resource-id='ca.rmen.android.networkmonitor:id/switchWidget'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='ca.rmen.android.networkmonitor:id/switchWidget'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.navigate().back();
        Thread.sleep(1000);
        new TouchAction(driver).press(PointOption.point(660, 1023)).moveTo(PointOption.point(660, 100)).release().perform();
        new TouchAction(driver).press(PointOption.point(660, 1023)).moveTo(PointOption.point(660, 100)).release().perform();
        driver.findElementByXPath("(//*[@resource-id='ca.rmen.android.networkmonitor:id/switchWidget'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='ca.rmen.android.networkmonitor:id/switchWidget'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[9]").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
//Advanced、Enable data to...off、on、Files to monitor、返回、speed test、返回、滑动、Enable notification....on、off、Notification ring、第一个、ok、Import、返回、send email、返回、返回
//Break:37?/38?/46√/47/48√/51√/53√
//manually check Break:48√/49√/50√/51√/52√/53√/54√
//unexpected Break:46/47
//COSER action: 10 (36~42,46,47,55)