package main.resources.BloodPressureTracker;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

// From V1.0.4
//TrackerAddTest->HistoryTest->InfoTest->SettingTest
public class SettingTest {
    //private AndroidDriver<AndroidElement> driver;
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "deivce");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.bloodpressurechecker.bpmonitor.bptracker");
        desiredCapabilities.setCapability("appActivity", ".ui.main.MainActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/ll_settings").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/relay_language_options").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/iv_done").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/ll_settings").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/relay_rate_us").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/btnLater").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/relay_feedback").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_cancel").click();

        

    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
