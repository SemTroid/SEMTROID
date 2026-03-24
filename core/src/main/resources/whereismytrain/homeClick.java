package main.resources.whereismytrain;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class homeClick {

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
    public void HomeClick() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@text=\"SPOT\"]").click();//break
        driver.findElementByXPath("//android.widget.TextView[@text=\"PNR\"]").click(); //修复成功
        driver.findElementByXPath("//android.widget.TextView[@text=\"SEATS\"]").click();//break修复成功 当前界面满足阈值的最大相似度：0.6570977473258972 tickect
        Thread.sleep(500);


        driver.findElementByXPath("//android.widget.ImageView[@content-desc=\"More options\"]").click();

        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView").click();
        //失败// Element located incorrectly (ELI) 失败 0.33
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView").click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[4]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView").click();
        //当前界面满足阈值的最大相似度：1.4799999523162841 但修复错误，错误识别成language

        driver.findElementById("com.whereismytrain.android:id/submit_language").click();//当前界面满足阈值的最大相似度：0.7880601286888123 修复成功
        driver.findElementById("com.whereismytrain.android:id/spot_autocompleteFrom").sendKeys("D");



    }
    @After
    public void tearDown() {
        driver.quit();
    }
}