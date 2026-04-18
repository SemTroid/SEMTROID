package main.resources.BloodPressureTracker;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;


//TrackerAddTest->HistoryTest->InfoTest->SettingTest
public class TrackerAddTest {
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
        driver.findElementByXPath("//android.widget.LinearLayout[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/ll_add\"]/android.widget.ImageView").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/ll_add_note_option").click();
        Thread.sleep(1000);
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_edit_add").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/ll_add_new").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_tag").sendKeys("test");
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_save").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_save").click();
        driver.findElementByXPath("(//android.view.ViewGroup[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/layout_item\"])[1]").click();
        driver.findElementByXPath("(//android.view.ViewGroup[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/layout_item\"])[2]").click();
        driver.findElementByXPath("(//android.view.ViewGroup[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/layout_item\"])[3]").click();
        driver.findElementByXPath("(//android.view.ViewGroup[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/layout_item\"])[4]").click();
        driver.findElementByXPath("(//android.view.ViewGroup[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/layout_item\"])[5]").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_save").click();
//        new TouchAction(driver).longPress(PointOption.point(370,1870)).moveTo(PointOption.point(370,80)).release().perform();
//        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_date").click();
//        driver.findElementById("android:id/button1").click();
//        driver.findElementByXPath("//android.widget.LinearLayout[@resource-id=\"com.bloodpressurechecker.bpmonitor.bptracker:id/ll_date_time_option\"]/android.widget.LinearLayout/android.widget.LinearLayout[2]").click();
//        driver.findElementById("android:id/button2").click();
        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/tv_save").click();
//        driver.findElementById("com.bloodpressurechecker.bpmonitor.bptracker:id/iv_next").click();







    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

//repaired:41,45,51,58
//fail:49,54
