package main.resources.whereismytrain;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;


public class setting {

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

        driver.findElementByAccessibilityId("Opening navigation drawer").click();//Element not found (ENF) 修复成功
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Share App\")").click();//Breakage Type: Element located incorrectly (ELI) ***修复失败


        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.RelativeLayout/android.widget.ListView/android.widget.RelativeLayout[4]/android.widget.LinearLayout/android.widget.TextView").click();//Element not found (ENF) ***
        //Thread.sleep(1000);//个体相似度: 1.0Breakage, start repair *** Breakage Type: Element not found (ENF) *** 修复成功
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.LinearLayout/android.widget.Switch").click();
        //Thread.sleep(1000);//滑动条图案 Breakage Type: Element not found (ENF) 修复成功

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[8]/android.widget.RelativeLayout/android.widget.TextView[1]").click();
        Thread.sleep(1000);//滑动条图案 Breakage Type: Element not found (ENF) 修复成功
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[10]/android.widget.RelativeLayout/android.widget.TextView[2]").click();
       // Thread.sleep(1000);//滑动条图案 Breakage Type: Element not found (ENF) 修复成功



        driver.findElementByAccessibilityId("Navigate up").click();



    }
    @After
    public void tearDown() {
        driver.quit();
    }
}