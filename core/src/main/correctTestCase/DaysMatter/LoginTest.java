package main.correctTestCase.DaysMatter;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

// Version：V 1.18.19
// steps：点击用户图标 --> 点击sign in文本 --> 输入用户名 --> 输入密码 --> 点击sign in文本


public class LoginTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.clover.daysmatter");
        desiredCapabilities.setCapability("appActivity", "com.clover.daysmatter.ui.activity.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "192.168.58.109:5555");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("(//android.widget.ImageView)[6]").click();
        driver.findElementById("com.clover.daysmatter:id/cs_button_sign_in").click();
        driver.findElementById("com.clover.daysmatter:id/edit_signin_name").sendKeys("test");
        driver.findElementById("com.clover.daysmatter:id/edit_signin_password").sendKeys("test");
        driver.findElementById("com.clover.daysmatter:id/cs_button_signin").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
