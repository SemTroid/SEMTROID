package main.resources.whereismytrain;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


public class findtrains {

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
    public void findtrains() throws InterruptedException {

       // driver.findElementById("com.whereismytrain.android:id/spot_autocompleteFrom").click(); //点空白格
       // driver.findElementById("com.whereismytrain.android:id/swap_line").click(); //找不到
       // driver.findElementById("com.whereismytrain.android:id/spot_autocomplete_to").click();
       // driver.findElementById("com.whereismytrain.android:id/spot_autocomplete_to").click();
       // driver.findElementById("com.whereismytrain.android:id/spot_findTrains").click();//成功 找火车
        //Thread.sleep(1000);

       // driver.findElementById("com.whereismytrain.android:id/dateText").click();//成功 日期
        //driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[2]").click();

        //driver.findElementById("com.whereismytrain.android:id/classText").click();//成功 类别
       // driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RadioGroup/android.widget.RadioButton[2]").click();
        //成功
       // driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[3]/android.widget.RelativeLayout").click();
        // 失败纯色图片 火车车次
        //Thread.sleep(1000);

        driver.findElementById("com.whereismytrain.android:id/bottom_title").click(); //底下的bar栏 纯色图片 失败 0 原本五十多
       // driver.findElementById("com.whereismytrain.android:id/feedbackText").click(); //底下的bar栏 纯色图片 失败 0 原本五十多


    }
    @After
    public void tearDown() {
        driver.quit();
    }
}