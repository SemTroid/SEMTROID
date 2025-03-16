package Anki;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class TwentySevenTest {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.ichi2.anki");
        desiredCapabilities.setCapability("appActivity", "com.ichi2.anki.IntentHandler");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException {
        driver.findElementByXPath("//android.widget.TextView[@text=\"Rename deck\"]").click();
        driver.findElementByXPath("//android.widget.EditText[@text=\"A\"]").clear();
        driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='com.ichi2.anki:id/md_customViewFrame']/android.widget.ScrollView[1]/android.widget.EditText[1]").clear();
        driver.findElementByXPath("//android.widget.FrameLayout[@resource-id='com.ichi2.anki:id/md_customViewFrame']/android.widget.ScrollView[1]/android.widget.EditText[1]").sendKeys("A");
        driver.findElementById("com.ichi2.anki:id/md_buttonDefaultPositive").click();
        driver.findElementByXPath("//android.widget.TextView[@text=\"Export deck\"]").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ichi2.anki:id/md_contentRecyclerView']/android.widget.LinearLayout[1]/android.widget.CheckBox[1]").click();
        driver.findElementByXPath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.ichi2.anki:id/md_contentRecyclerView']/android.widget.LinearLayout[2]/android.widget.CheckBox[1]").click();
        driver.findElementById("com.ichi2.anki:id/md_buttonDefaultPositive").click();
        driver.findElementById("com.ichi2.anki:id/md_buttonDefaultNeutral").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
