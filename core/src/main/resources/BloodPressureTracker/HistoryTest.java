package main.resources.BloodPressureTracker;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

//TrackerAddTest->HistoryTest->InfoTest->SettingTest
public class HistoryTest {
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
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_history").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/iv_edit").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/ll_add_note_option").click();
        driver.findElementByXPath("(//android.view.ViewGroup[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/layout_item\"])[1]").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_edit_add").click();
        driver.findElementByXPath("(//android.widget.ImageView[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/ac_iv_delete\"])[1]").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_delete").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/iv_close").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_delete").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_delete").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/iv_back").click();




    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//repaired:42
//failed:43,57