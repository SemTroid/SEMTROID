package main.resources.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


// Version：V 136-0-9401-20211103
// steps：点击搜索图标 --> 输入Lionel Messi --> 点击Lionel Messi --> 点击球员对比图标 --> 点击Select Player文本 --> 点击C Ronaldo文本 --> 点击Major League文本 --> 点击Ligue 1文本（2021/2022） --> 点击Saudi文本 --> 点击Serie A文本（2021/2022）
// 失效：39-搜索图标-定位不到    42-对比图标-定位不到    43-Select Player文本-定位不到   45-Major League文本-定位不到   47-Saudi文本-定位不到

public class ComparePlayersTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.mobilefootie.wc2010");
        desiredCapabilities.setCapability("appActivity", "com.mobilefootie.fotmob.gui.v2.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Search\"]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/editText_search").sendKeys("Lionel Messi");
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.TextView[@content-desc=\"Player vs Player\"]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.TextView[3]").click();//com.mobilefootie.wc2010:id/textView_player2
        driver.findElementByXPath("//android.widget.TextView[@text='Cristiano Ronaldo']").click();
        driver.findElementByXPath("//android.widget.LinearLayout/android.widget.Spinner/android.widget.RelativeLayout/android.widget.TextView[1]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
        driver.findElementByXPath("//android.widget.FrameLayout/android.widget.Spinner/android.widget.RelativeLayout/android.widget.TextView[1]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
