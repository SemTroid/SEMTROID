package main.correctTestCase.FotMob;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;


// Version：V 186-11404-20240318
// steps：38点击星星图标（Favorite）--> 39点击EDIT文本 --> 40点击删除图标（Real Madrid）--> 41点击DONE文本


public class DeleteFavoriteTest {

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
        driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Following\"]/android.widget.FrameLayout/android.widget.ImageView").click();
        driver.findElementById("com.mobilefootie.wc2010:id/menu_edit").click();
        driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"Remove from favorites\"])[3]").click();
        driver.findElementByXPath("//android.widget.Button[@text='Done']").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
