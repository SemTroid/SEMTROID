package main.resources.MyNetDiary;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class sports {

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
        desiredCapabilities.setCapability("appium:appPackage", "com.fourtechnologies.mynetdiary.ad");
        desiredCapabilities.setCapability("appium:appActivity", "com.mynetdiary.ui.MainActivity");
        desiredCapabilities.setCapability("appium:noReset", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:dontStopAppOnReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void test() throws InterruptedException {
          driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/exercise_view").click();//主页上面execrise 按钮
          driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/ad_cell_lightGrayTextView").click();
        //下find and log exe
          driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.TextView[1]").click();
        //walking 第一个

        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"calories\")").click();       //选择下面的卡路里，上面是分钟小时
        driver.findElementById("android:id/text1").click(); //上面的复选框
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Less Than 2.0 mph (3.2 km/h)\")").click();
        //选第一个



        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/text").click();
        //save






    }
    @After
    public void tearDown() {
        driver.quit();
    }
}