package main.resources.MyNetDiary;


import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;


public class setting {

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
        desiredCapabilities.setCapability("appium:appPackage", "com.fourtechnologies.mynetdiary.ad");
        desiredCapabilities.setCapability("appium:appActivity", "com.mynetdiary.ui.MainActivity");
        desiredCapabilities.setCapability("appium:noReset", true);
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:dontStopAppOnReset", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void test() throws InterruptedException {

      //driver.findElementByAccessibilityId("Navigate up").click();//左边导航栏
     //Charts/menu
        driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"com.fourtechnologies.mynetdiary.ad:id/design_menu_item_text\" and @text=\"Before & After\"]").click();    //Premium Recipes & menus
       // driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"com.fourtechnologies.mynetdiary.ad:id/design_menu_item_text\" and @text=\"Charts\"]").click();     //before after  photo
        //driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id=\"com.fourtechnologies.mynetdiary.ad:id/design_menu_item_text\" and @text=\"Settings\"]").click();
        //setting
        //driver.findElementByAndroidUIAutomator("new UiSelector().text(\"BG & Diabetes Tracking\")").click();
     // BG&Diabets Iracking

       // driver.findElementById("com.fourtechnologies.mynetdiary.ad:id/sw_switch").click();//第一个滑动控件，然后进开会员画面







    }
    @After
    public void tearDown() {
        driver.quit();
    }
}