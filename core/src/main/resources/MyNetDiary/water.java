package main.resources.MyNetDiary;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class water {

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
        //driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/androidx.viewpager.widget.ViewPager/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/androidx.cardview.widget.CardView[1]/android.view.ViewGroup/android.widget.LinearLayout[6]/android.widget.TextView[1]").click();
        //点击主页的water
        //driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView").click();
        //点击1/2杯说
       //driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/btn_edit_entries").click();//entry edit
       // driver.findElementByAccessibilityId("Add").click();//+按钮
       // driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/et_volume").sendKeys("100");//输入100
       // driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/btn_save").click();//保存

        //手动退到water主页
        driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/btn_settings").click();//water setting
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout[2]/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[6]/android.widget.TextView[1]").click();
        //三星健康
         driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/btn_toggle_link").click();//link





    }
    @After
    public void tearDown() {
        driver.quit();
    }
}