package main.correctTestCase.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


// Version：V 186-11404-20240318
// steps：点击搜索图标 --> 点击Barcelona --> 点击队伍对比图标 --> 点击Select文本 --> 点击Girona文本 --> 点击Laliga文本（左） --> 点击laliga（2021/2022）文本--> 点击Laliga文本（右） --> 点击laliga2（2021/2022）文本


public class CompareTeamsTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.mobilefootie.wc2010");
        desiredCapabilities.setCapability("appActivity", "com.fotmob.android.ui.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("udid", "192.168.58.109:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Search\"]").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Team vs team\"]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/textView_name2").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.TextView").click();
        driver.findElementByXPath("//androidx.cardview.widget.CardView[1]/android.widget.Spinner/android.widget.RelativeLayout/android.widget.TextView[1]").click();
        driver.findElementByXPath("//android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[3]/android.widget.TextView[1]").click();
        driver.findElementByXPath("//androidx.cardview.widget.CardView[2]/android.widget.Spinner/android.widget.RelativeLayout/android.widget.TextView[1]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[3]/android.widget.TextView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
