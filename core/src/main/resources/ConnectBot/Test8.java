package main.resources.ConnectBot;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Test8 {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "org.connectbot");
        desiredCapabilities.setCapability("appActivity", "org.connectbot.HostListActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void sampleTest() throws InterruptedException{
        new TouchAction(driver).longPress(ElementOption.element(driver.findElementByXPath("//android.widget.RelativeLayout[@resource-id=\"android:id/content\"]"))).perform();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[1]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("vallxy");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[3]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("11");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[6]").click();
        driver.findElementById("android:id/edit").clear();
        driver.findElementById("android:id/edit").sendKeys("a");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        new TouchAction(driver).press(PointOption.point(540, 1600)).moveTo(PointOption.point(540, 0)).release().perform();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[2]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/checkbox'])[3]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[4]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/text1'])[1]").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[5]").click();
        driver.navigate().back();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[7]").click();
        driver.findElementById("android:id/edit").sendKeys("vallxy");
        driver.findElementById("android:id/button1").click();
        driver.findElementByXPath("(//*[@resource-id='android:id/title'])[8]").click();
        driver.findElementById("android:id/edit").sendKeys("192.168.1.1");
        driver.findElementById("android:id/button1").click();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//Break: 45(False)、46、47(False)、48、49、50、51(False)、52(False)、53(False)、54
// 55、56、57(False)、58、59(False)、60(False)、61、62
// 63(False)、64(False)、65(False)、66(False)、68(False)、69(False)、70(False)、71(False)
// 72(False)、73、74、76、77、78、79、80、81