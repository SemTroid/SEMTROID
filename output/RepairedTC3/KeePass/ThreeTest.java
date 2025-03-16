package KeePass;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class ThreeTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.android.keepass");
        desiredCapabilities.setCapability("appActivity", "com.keepassdroid.PasswordActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementById("com.android.keepass:id/menu_app_settings").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Database\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Application\"]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[2]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[5]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        //;
        driver.findElementByXPath("//android.widget.TextView[@text=\"Clipboard timeout\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Clipboard timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"30 seconds\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Clipboard timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"1 minute\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Clipboard timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"5 minutes\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Clipboard timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Never\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Application timeout\"]").click();
        driver.findElementById("android:id/button2").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Application timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"30 seconds\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Application timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"1 minute\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Application timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"5 minutes\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Application timeout\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Never\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Group list size\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Small\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Group list size\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Medium\"]").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Group list size\"]").click();
        driver.findElementByXPath("//android.widget.CheckedTextView[@text=\"Large\"]").click();
        driver.findElementByXPath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[5]/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.navigate().back();
        driver.navigate().back();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
