package main.resources.Reddit;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 2021.47.0
// steps：点击搜索图标 --> 输入框sora --> 点击results for sora文本 --> 点击communities文本 --> 点击r/soraAi文本对应的加号图标
// 失效：39-搜索图标-定位不到   41-results for sora文本-定位不到  42-communities文本-定位错  43-加号图标（r/soraAi）-定位不到

public class AddSoraAITest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.reddit.frontpage");
        desiredCapabilities.setCapability("appActivity", "launcher.default");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.reddit.frontpage:id/search_view").click();
        driver.findElementById("com.reddit.frontpage:id/search").sendKeys("sora");
        driver.findElementById("com.reddit.frontpage:id/community_results").click();
        driver.findElementByXPath("(//android.widget.TextView)[5]").click();
        driver.findElementByXPath("//android.widget.RelativeLayout[1]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ImageView").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
