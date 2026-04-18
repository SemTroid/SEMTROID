package main.java.aspects;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import main.java.config.Settings;
import main.java.dataType.Context;
import main.java.runner.RepairRunner;
import main.java.util.FileUtils;
import main.java.util.UtilsAspect;
import main.java.util.UtilsHierarchyXml;
import main.java.util.UtilsParser;
import org.openqa.selenium.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author QS
 * @date 2024/2/4 10:29
 * @version 1.0
 */
public aspect InfoExtractor {
    private static Logger logger = LoggerFactory.getLogger("InfoExtractor.aj");

    static AndroidDriver driver;
    static String testFolder;
    static String testCaseName;

    // Statement Info
    static String statement;
    static int statementLine;

    // Hierarchy Layout
    static String hierarchyLayoutXmlFile;

    // ViewTree Info
    static String viewTreeInfoJsonFile;

    // Add: Context Info
    static String contextInfoFile;
    // Add: sleepTime
//    static long sleepTime;

    // Intercept the calls to findElement methods
    pointcut logFindElementCalls() : call(* io.appium.java_client.MobileDriver.findElementByAccessibilityId(..))||
                                    call(* io.appium.java_client.MobileDriver.findElementById(..)) ||
                                    call(* io.appium.java_client.MobileDriver.findElementByClassName(..)) ||
                                    call(* io.appium.java_client.MobileDriver.findElementByCssSelector(..)) ||
                                    call(* io.appium.java_client.MobileDriver.findElementByLinkText(..)) ||
                                    call(* io.appium.java_client.MobileDriver.findElementByName(..)) ||
                                    call(* io.appium.java_client.MobileDriver.findElementByXPath(..));
                                    //call(* io.appium.java_client.FindsByAndroidUIAutomator.findElementByAndroidUIAutomator());

    // Intercept the calls to MobileElement methods
    pointcut logMobileActionCommands() : call(* org.openqa.selenium.WebElement.click()) ||
                                        call(* org.openqa.selenium.WebElement.sendKeys(..)) ||
                                        call(* org.openqa.selenium.WebElement.getText()) ||
                                        call(* org.openqa.selenium.WebElement.clear()) ||
                                        call(* org.openqa.selenium.Alert.accept());

    // Intercept tearDown() method
    pointcut logTearDownCommand() : call(* org.openqa.selenium.remote.RemoteWebDriver.quit());

    // Add: Intercept Thread.sleep() method


    // Create output folder of current test case before calling the methods
    // eg: output/AppInfo/ADDR/TC1
    before() : logFindElementCalls() {
        // 仅拦截第一个findElement语句。若driver不为空，说明之前已经拦截了findElement语句，不会再拦截。
        if(Settings.aspectActive && driver == null) {
            // Add: 避免嵌套拦截
            Settings.aspectActive = false;
            try {
                Thread.sleep(5000);
                logger.info("--- wait for 5s ---");
            } catch (InterruptedException e) {
                logger.info("Driver wait has been interrupted");
            }
            logger.info("*** Before execute statements ***");

            driver = (AndroidDriver) thisJoinPoint.getTarget();
            // 用于提取上下文时过滤过大元素
            RepairRunner.Height = driver.manage().window().getSize().getHeight();
            RepairRunner.Width = driver.manage().window().getSize().getWidth();

            String withinType = thisJoinPoint.getSourceLocation().getWithinType().toString();
            // 此测试用例类所在的文件夹
            String testSuiteName = UtilsParser.getTestSuiteNameFromWithinType(withinType);

            // 测试用例类名,如：TC1
            testCaseName = thisJoinPoint.getSourceLocation().getFileName().replace(Settings.JAVA_EXT, "");
            testFolder = Settings.extractInfoPath + Settings.sep + testSuiteName + Settings.sep + testCaseName;
            // 例如: output/AppInfo/ADDR/TC1
            UtilsAspect.createTestFolder(testFolder);
            logger.info("*** Folder has been created ***");
            // Add: 避免嵌套拦截
            Settings.aspectActive = true;
        }
    }

    // Save hierarchy layout and view tree information before executing the method
    before() : logMobileActionCommands() {
        if(Settings.aspectActive) {
            // Add: 避免嵌套拦截
            Settings.aspectActive = false;
            logger.info("*** Before Appium Action ***");
            // 获取MobileElement对象
            MobileElement me = null;
            if (thisJoinPoint.getTarget() instanceof MobileElement) {
                me = (MobileElement) thisJoinPoint.getTarget();
            } else if (thisJoinPoint.getTarget() instanceof Alert) {
                me = null;
            }

            // 获取当前测试语句及其所在行号
            statement = thisJoinPoint.getStaticPart().toString();
            statementLine = thisJoinPoint.getSourceLocation().getLine();
            // 获取当前界面视图层次布局信息
            hierarchyLayoutXmlFile = testFolder + Settings.sep + statementLine + "-hierarchy" + Settings.XML_EXT;
            UtilsHierarchyXml.takeXmlSnapshot(driver, System.getProperty("user.dir") + Settings.sep + hierarchyLayoutXmlFile);
            logger.info("*** HierarchyXmlFile has been saved ***");
            // 获取并保存目标元素信息
            viewTreeInfoJsonFile = testFolder + Settings.sep + statementLine + "-viewTreeInfo" + Settings.JSON_EXT;
            UtilsAspect.saveViewTreeInformation(me, hierarchyLayoutXmlFile, viewTreeInfoJsonFile);
            logger.info("*** Element Info has been saved ***");
            // Add：获取并保存上下文信息，新建Context类处理上下文（提取和保存）
            contextInfoFile = testFolder + Settings.sep + statementLine + "-contextInfo";
            Context context = new Context(driver, me, hierarchyLayoutXmlFile);
            try {
                FileUtils.writeObject(context,contextInfoFile);
                logger.info("*** Context Info has been saved ***");
            } catch (Exception e) {
                e.printStackTrace();
            }

            logger.info("*** Trace Finished: " + statement + " ***");
            // Add: 避免嵌套拦截
            Settings.aspectActive = true;
        }
    }
    after() : logMobileActionCommands() {
        if(Settings.aspectActive) {
            Settings.aspectActive = false;
            try {
                Thread.sleep(5000);
                logger.info("--- wait for 5s ---");
            } catch (InterruptedException e) {
                logger.info("Driver wait has been interrupted");
            }
            Settings.aspectActive = true;
        }
    }

    before() : logTearDownCommand() {
        if(Settings.aspectActive){
            Settings.aspectActive = false;
            logger.info("*** Before quit ***");
            // 获取最后语句的行号，保存结束时的界面布局xml文件
            hierarchyLayoutXmlFile = testFolder + Settings.sep + (statementLine+1) + "-hierarchy" + Settings.XML_EXT;
            UtilsHierarchyXml.takeXmlSnapshot(driver, System.getProperty("user.dir") + Settings.sep + hierarchyLayoutXmlFile);
            logger.info("*** HierarchyXmlFile has been saved ***");
            Settings.aspectActive = true;
        }
    }
}