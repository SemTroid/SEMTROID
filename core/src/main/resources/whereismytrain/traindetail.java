package main.resources.whereismytrain;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class traindetail {

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

        driver.findElementById("com.whereismytrain.android:id/live_station_search_btn").click();//Element not found (ENF)修复成功0.9993358254432678
        Thread.sleep(500);// 上下两个搜索可能会搞混，下面那个是对的

        driver.findElementById("com.whereismytrain.android:id/btn_scheduled").click();//No Breakage ***
        driver.findElementById("com.whereismytrain.android:id/btn_scheduled").click();//No Breakage ***
        driver.findElementById("com.whereismytrain.android:id/btn_expected").click();//*** No Breakage ***
        Thread.sleep(500);

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.widget.RelativeLayout").click();
        Thread.sleep(500);//Breakage Type: Element not found (ENF)

        driver.findElementByAccessibilityId("Train Name Okha - Banaras SF Express").click();//No Breakage

        driver.findElementById("com.whereismytrain.android:id/date_chip").click();//Breakage Type: Breakage Type: Element not found (ENF)
        //当前界面满足阈值的最大相似度：0.6535117053985595
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[3]").click();
        //当前界面满足阈值的最大相似度：0.9999998807907104 Breakage Type: Element not found (ENF)
        //driver.findElementById("com.whereismytrain.android:id/cancel").click(); //No Breakage
        driver.findElementById("com.whereismytrain.android:id/alarm").click();// Breakage Type: Element not found (ENF) ***当前界面满足阈值的最大相似度：0.6535117053985595
        Thread.sleep(500);

        driver.findElementById("com.whereismytrain.android:id/alarm_where_text").click();//No Breakage
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView[2]").click();
        //当前界面满足阈值的最大相似度：0.9999998807907104 Breakage Type: Element not found (ENF)
        driver.findElementById("com.whereismytrain.android:id/alarm_set_alarm").click();//No Breakage
        driver.findElementById("com.whereismytrain.android:id/cancel").click();//No Breakage


    }
    @After
    public void tearDown() {
        driver.quit();
    }
}