package main.resources.MyNetDiary;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class Diet {

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

         //driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[2]").click();
        //  点击coach里的diet
        // driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[2]").click();
        //点击第一个图片的文字控件

        //driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/start_diet_button");
        //点击start diet
        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/next").click();
        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/sw_switch").click();

        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/next").click();//下一个

        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/btn_select_distribution").click();//select

       driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.recyclerview.widget.RecyclerView/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
        //点×
        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/next").click();


        driver.findElementByAccessibilityId("Close").click();
        driver.findElementById("android:id/button2").click();//选择cancel
        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/next").click();
        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/next").click();



    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
