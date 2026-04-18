package main.resources.whereismytrain;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class seatsearch {

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
        desiredCapabilities.setCapability("appium:appPackage", "com.whereismytrain.android");
        desiredCapabilities.setCapability("appium:appActivity", "com.whereismytrain.view.activities.HomeActivity");
        desiredCapabilities.setCapability("appium:noReset", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:dontStopAppOnReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void test() throws InterruptedException {

        driver.findElementById("com.whereismytrain.android:id/avail_autocomplete_from").click();//纯色图片修复失败
        //Thread.sleep(500);
        driver.findElementById("com.whereismytrain.android:id/swap_line").click();//纯色图片修复失败 图标变化 综合相似度：0.500110924243927
        //Thread.sleep(500);
        driver.findElementById("com.whereismytrain.android:id/avail_autocomplete_to").click();//纯色图片修复失败
        //Thread.sleep(500);
        driver.findElementById("com.whereismytrain.android:id/journeyDateText").click();//综合相似度：0.5938453674316406 修复失败
        //Thread.sleep(500);
        driver.findElementById("com.whereismytrain.android:id/mdtp_ok").click();//增强后相似度：0.3368566334247589
        //Thread.sleep(500);
        driver.findElementById("com.whereismytrain.android:id/tomorrowText").click();//修复成功，但修复错误，定位到下面城市
        //Thread.sleep(500);
        driver.findElementByAccessibilityId("27 June 2025").click();  // Click Element: ByAccessibilityId("27 六月 2025") *** 修复成功
        Thread.sleep(500);
        driver.findElementById("com.whereismytrain.android:id/class_spinner").click(); //修复成功
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"AC 3-Tier\")").click(); //修复失败
        driver.findElementById("com.whereismytrain.android:id/quota_spinner").click();//修复成功  No Breakage
        Thread.sleep(500);
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.TextView[1]").click();
        //修复成功  No Breakage
        Thread.sleep(500);
        driver.findElementById("com.whereismytrain.android:id/avail_findTrains").click(); //修复失败  字的差距太大


    }
    @After
    public void tearDown() {
        driver.quit();
    }
}