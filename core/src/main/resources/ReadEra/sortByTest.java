package main.resources.ReadEra;

import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * ReadEra App 自动化测试模板
 * 此模板提供了基本的测试框架结构，包括：
 * 1. 应用初始化设置
 * 2. 基本的测试用例结构
 * 3. 资源清理
 */
public class sortByTest {
    private AndroidDriver driver;
    private static final int IMPLICIT_WAIT = 10; // 隐式等待时间（秒）

    @Before
    public void setUp() throws Exception {
        // 设置 DesiredCapabilities
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "9");
        desiredCapabilities.setCapability("deviceName", "device");
        desiredCapabilities.setCapability("automationName", "UiAutomator2");
        desiredCapabilities.setCapability("appPackage", "org.readera");
        desiredCapabilities.setCapability("appActivity", "com.readera.MainActivity");
        desiredCapabilities.setCapability("noReset", true);

        // 初始化 driver
        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
//        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS); // 添加隐式等待

    }


    @Test
    public void sortByFunction() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElementByClassName("android.widget.ImageButton").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//android.widget.CheckedTextView[@resource-id='org.readera:id/design_menu_item_text' and @text='All Documents']").click();
        Thread.sleep(1000);
        driver.findElementByAccessibilityId("Sort by").click();
        Thread.sleep(1000);
        driver.findElementByXPath("//android.widget.TextView[@resource-id='org.readera:id/title' and @text='Read time']").click();
        Thread.sleep(1000);
    }

    /**
     * 清理测试环境
     * 关闭应用并释放资源
     */
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}