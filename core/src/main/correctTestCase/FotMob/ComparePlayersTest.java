package main.correctTestCase.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


// Version：V 186-11404-20240318
// steps：点击搜索图标 --> 输入Lionel Messi --> 点击Lionel Messi --> 点击球员对比图标 --> 点击Select Player文本 --> 点击C Ronaldo文本 --> 点击Major League文本 --> 点击Ligue 1文本（2021/2022） --> 点击Saudi文本 --> 点击Serie A文本（2021/2022）


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
        driver.findElementById("com.mobilefootie.wc2010:id/editText_search").sendKeys("Lionel Messi");
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.TextView").click();
        driver.findElementByXPath("//android.widget.Button[@content-desc=\"Player vs Player\"]").click();
        driver.findElementById("com.mobilefootie.wc2010:id/textView_player2").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Cristiano Ronaldo']").click();
        driver.findElementByXPath("//androidx.cardview.widget.CardView[1]/android.widget.Spinner/android.widget.RelativeLayout/android.widget.TextView[1]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
        driver.findElementByXPath("//androidx.cardview.widget.CardView[2]/android.widget.Spinner/android.widget.RelativeLayout/android.widget.TextView[1]").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.RelativeLayout[4]/android.widget.TextView[1]").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
