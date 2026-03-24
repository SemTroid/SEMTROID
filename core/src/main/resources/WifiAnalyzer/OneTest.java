package main.resources.WifiAnalyzer;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class OneTest {
    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "com.vrem.wifianalyzer");
        desiredCapabilities.setCapability("appActivity", "com.vrem.wifianalyzer.SplashActivity");
        desiredCapabilities.setCapability("noReset", true);
        desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("dontStopAppOnReset", true);
        desiredCapabilities.setCapability("udid", "emulator-5556");
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() throws InterruptedException{
        driver.findElementById("com.vrem.wifianalyzer:id/action_scanner").click();
        driver.findElementById("com.vrem.wifianalyzer:id/action_scanner").click();
        driver.findElementById("com.vrem.wifianalyzer:id/action_filter").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSSIDtext").sendKeys("111111111111111");
        driver.findElementById("com.vrem.wifianalyzer:id/filterWifiBand2").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterWifiBand2").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength0").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength1").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength2").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength3").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength4").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength0").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength1").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength2").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength3").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterStrength4").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityNone").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWPS").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWEP").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWPA").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWPA2").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityNone").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWPS").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWEP").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWPA").click();
        driver.findElementById("com.vrem.wifianalyzer:id/filterSecurityWPA2").click();
        driver.findElementById("android:id/button1").click();


    }

    @After
    public void tearDown() {
        driver.quit();
    }

}



