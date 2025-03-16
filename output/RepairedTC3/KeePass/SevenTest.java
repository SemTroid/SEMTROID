package KeePass;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class SevenTest {

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
        driver.findElementByXPath("//android.widget.EditText[@text=\"/storage/emulated/0/keepass/keepass.kdbx\"]").clear();
        driver.findElementByXPath("//android.widget.RelativeLayout[@resource-id='com.android.keepass:id/file_select']/android.widget.RelativeLayout[1]/android.widget.EditText[1]").sendKeys("/storage/emulated/0/keepass/k.kdbx");
        driver.findElementById("com.android.keepass:id/open").click();
        driver.findElementById("com.android.keepass:id/default_database").click();
        driver.findElementById("com.android.keepass:id/password").sendKeys("1");
        driver.findElementById("com.android.keepass:id/pass_ok").click();
        driver.findElementById("com.android.keepass:id/password").sendKeys("0");
        driver.findElementById("com.android.keepass:id/pass_ok").click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
