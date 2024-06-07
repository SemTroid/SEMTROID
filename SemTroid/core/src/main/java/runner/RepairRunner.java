package main.java.runner;

import com.alibaba.fastjson.JSONObject;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import main.java.config.AppEnum;
import main.java.config.Settings;
import main.java.config.Threshold;

import main.java.dataType.*;
import main.java.model.Model;
import main.java.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author anonymous author
 * @version 1.0
 */
public class RepairRunner {
    private static Map<Integer,List<Integer>> map;
    // 旧测试用例 - 标准答案 行号映射表
    static {
//        map = new HashMap<>();
//        map.put(39,new ArrayList<>(Collections.singletonList(40)));
//        map.put(39,new ArrayList<>(Arrays.asList(39,40)));
//        map.put(40,new ArrayList<>(Collections.singletonList(41)));
//        map.put(41,new ArrayList<>(Collections.singletonList(42)));
//        map.put(42,new ArrayList<>(Arrays.asList(42,43)));
//        map.put(42,new ArrayList<>(Collections.singletonList(43)));
//        map.put(43,new ArrayList<>(Collections.singletonList(44)));
//        map.put(45,new ArrayList<>(Arrays.asList(45,46)));
    }
    private static Logger logger = LoggerFactory.getLogger(RepairRunner.class);

    public static AppEnum appEnum;

    public static String testcaseName;

    private static long startTime;

    private static long stopTime;

    private static long elapsedTime;

    private static final String remoteUrl = "http://127.0.0.1:4723/wd/hub";

    private static AndroidDriver driver;

    // 是否考虑上下文
    public static boolean isContextConsidered;
    // 是否使用标准答案
    public static boolean isCorrectUsed;
    // 是否涉及路径增加
    public static boolean isAddPath;
    // 增加的语句个数(增加的点击+增加的滑动)
    private static int addedStmNum;
    // 增加的点击语句个数
    private static int addedClickNum;
    // 增加的滑动语句个数
    private static int addedSwipeNum;
    // 当前屏幕的层次布局文件存储路径
    public static String curLayoutXmlFile;
    // 屏幕高度
    public static int Height;
    // 屏幕宽度
    public static int Width;
    // 已执行的测试语句
    public static List<Statement> executedStm;

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        RepairRunner repairRunner = new RepairRunner();
        // App
        appEnum = AppEnum.DaysMatter;
        // TestCase
        testcaseName = "ShareTodayInHistoryTest";
        // 是否涉及路径增加（不包括滑动）
        isAddPath = false;
        // 是否考虑上下文
        isContextConsidered = true;
        // 是否使用标准答案
        isCorrectUsed = false;
        // 例如: output/RepairedTC/TempXmlSaved
        UtilsAspect.createTestFolder(Settings.TEMP_XML_SAVED_FOLDER);
        logger.info("Start repair: " + appEnum.getTestSuite() + "." + testcaseName);
        repairRunner.startRepair(testcaseName);
    }

    private void startRepair(String testcaseName) throws InterruptedException, IOException, ClassNotFoundException {
        // 获取待修复测试用例绝对路径
        String pathToTestBroken = UtilsFileGetter.getTestFile(testcaseName, ("core.src." + appEnum.getTestSuite()).replaceAll("\\.", "\\\\"));
        // parse test case
        logger.info("*** Original test script parsing ***");
        ParseTest pt = null;
        EnhancedTestCase testBroken = null;
        pt = new ParseTest(Settings.extractInfoPath + Settings.sep + appEnum.getAppName());
        // 解析原测试用例，存入EnhancedTestCase类的对象中
        testBroken = pt.parseAndSerialize(pathToTestBroken, "false");
        // 上一步解析后得到配置信息
        DesiredCapabilities capabilities = pt.getCapabilities();
        // 从当前打开界面开始执行
        capabilities.setCapability("dontStopAppOnReset", true);
        // udid of device (updated versions of apps installed)
        capabilities.setCapability("udid", "emulator-5556");
        // if activity changed
//        capabilities.setCapability("appActivity", "com.fotmob.android.ui.MainActivity");
        // 预加载模型
        logger.info("*** Semantic model loading ***");
        Model.getSimilarity("android","Android");
        URL url = null;
        try {
            url = new URL(remoteUrl);
        } catch (MalformedURLException e) {
            logger.error("Url is incorrect, please check!");
        }
        driver = new AndroidDriver(url, capabilities);
        Height = driver.manage().window().getSize().getHeight();
        Width = driver.manage().window().getSize().getWidth();
        executedStm = new ArrayList<>();
        // 解析标准答案
        String pathToCorrect = "core\\src\\main\\correctTestCase\\" + appEnum.getAppName() + "\\" + testcaseName + ".java";
        ParseTest pt2 = null;
        EnhancedTestCase correctTestCase = null;
        pt2 = new ParseTest(Settings.extractInfoPath + Settings.sep + appEnum.getAppName());
        // 解析标准答案测试用例，存入EnhancedTestCase类的对象中
        correctTestCase = pt2.parseAndSerialize(pathToCorrect,"true");
        // 所有正确测试语句集合 Map
        Map<Integer, Statement> correctStmMap = correctTestCase.getStatements();
        // 所有原测试语句集合 Map
        Map<Integer, Statement> originStmMap = testBroken.getStatements();
        // 所有新测试语句集合 Map
        Map<Integer, Statement> repairedStmMap = new LinkedHashMap<>();
        // 开始计时
        startTime = System.currentTimeMillis();

        /* For each statement */
        Statement previousStatement = null;
        for (int statementNum : originStmMap.keySet()) {
            // 通过整个修复过程是否能够找到目标元素
            boolean noSuchElement = true;
            // 原测试语句
            Statement statement = originStmMap.get(statementNum);
            logger.info("Check statement: " + statementNum + ": " + statement.toString());
            // 目标元素
            MobileElement targetElement = null;
            // 修复后的测试语句
            Statement repairedStm = UtilsRepair.deepClone(statement);

            // 返回操作
            if (statement.getAction().equals(AppiumAction.Navigate)) {
                previousStatement = statement;
                repairedStmMap.put(statementNum+addedStmNum, repairedStm);
                // 执行页面状态转移
                if (statement.getValue().equals("back")) {
                    driver.navigate().back();
                } else if (statement.getValue().equals("forward")) {
                    driver.navigate().back();
                } else if (statement.getValue().equals("refresh")) {
                    driver.navigate().refresh();
                }
                // 保存已执行语句，便于之后back操作
                executedStm.add(repairedStm);
                continue;
            }

            // 滑动操作
            if (statement.getAction().equals(AppiumAction.Touch)) {
                previousStatement = statement;
                repairedStmMap.put(statementNum+addedStmNum, repairedStm);
                if (statement.getValue().equals("swipe")) {
                    // 执行页面滑动
                    Point startPoint = ((EnhancedTouchAction)repairedStm).getStartPoint();
                    Point endPoint = ((EnhancedTouchAction)repairedStm).getEndPoint();
                    // new TouchAction(driver).press(PointOption.point(startPoint)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5))).moveTo(PointOption.point(endPoint)).release().perform();
                    new TouchAction(driver).longPress(PointOption.point(startPoint)).moveTo(PointOption.point(endPoint)).release().perform();
                } else if (statement.getValue().equals("tap")) {
                    Point tapPoint = ((EnhancedTouchAction)repairedStm).getStartPoint();
                    new TouchAction(driver).tap(PointOption.point(tapPoint)).perform();
                }
                // 保存已执行语句
                executedStm.add(repairedStm);
                continue;
            }

            // 当前元素捕获时对应的布局文件路径
            String oriLayoutXmlFile = Settings.extractInfoPath + Settings.sep + appEnum.getAppName() +
                    Settings.sep + testcaseName + Settings.sep + statementNum + "-hierarchy" + Settings.XML_EXT;
            // 捕获当前屏幕状态的层次布局文件
            curLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
            UtilsHierarchyXml.takeXmlSnapshot(driver, curLayoutXmlFile);

            // 修复过程
            // 先检查元素是否被修复过，对于相同的操作对象，直接使用上一步的修复结果
            if (identicalWithLastStatement(statement, previousStatement)) {
                // 直接深拷贝上一行语句
                repairedStm = UtilsRepair.deepClone(repairedStmMap.get(statementNum - 1));
                repairedStm.setLine(statementNum);
                repairedStm.setAction(statement.getAction());
                repairedStm.setValue(statement.getValue());
                targetElement = UtilsRepair.getElementFromStatement(driver, ((EnhancedMobileElement) repairedStm).getLocator());
            }
            // 当前界面状态下，如果可以定位到元素，说明可以直接使用上一步的修复结果
            if(targetElement!=null){
                repairedStmMap.put(statementNum+addedStmNum, repairedStm);
                noSuchElement = false;
                logger.info("This element is the same as the previous element, skip repair");
            } else {
                // 通过元素搜索是否能够找到目标元素
                boolean elementFound = false;
                // 1 使用旧元素 locator 搜索目标元素
                logger.info("*** 1 Retrieve Element From Appium Locator ***");
                long start2 = System.currentTimeMillis();
                targetElement = UtilsRepair.retrieveElementFromAppiumLocator(driver, (EnhancedMobileElement) statement,curLayoutXmlFile, isContextConsidered);
                if(targetElement!=null){
                    elementFound = true;
                    noSuchElement = false;
                    // 获取目标元素的定位器
                    AppiumLocator locator = UtilsRepair.getAppropriateLocator(driver, (EnhancedMobileElement) statement, targetElement, curLayoutXmlFile);
                    // 修复语句的元素操作以及操作值与原语句相同，设置新的定位器即可
                    ((EnhancedMobileElement)repairedStm).setLocator(locator);
                    // 修复后的语句加入修复语句列表
                    repairedStmMap.put(statementNum+addedStmNum,repairedStm);
                }else{
                    // 2 当前界面搜索目标元素
                    logger.info("*** 2 Retrieve Element From Current Screen ***");
                    long start = System.currentTimeMillis();
                    targetElement = UtilsRepair.retrieveElementFromCurrentPage(driver, (EnhancedMobileElement) statement,curLayoutXmlFile,isContextConsidered);
                    if(targetElement!=null){
                        elementFound = true;
                        noSuchElement = false;
                        AppiumLocator locator = UtilsRepair.getAppropriateLocator(driver, (EnhancedMobileElement) statement, targetElement, curLayoutXmlFile);
                        ((EnhancedMobileElement)repairedStm).setLocator(locator);
                        repairedStmMap.put(statementNum+addedStmNum,repairedStm);
                    }
                    else {
                        // 3 路径搜索
                        // 3.1 滑动界面后重新搜索
                        logger.info("*** 3.1 Retrieve Element From Swiped Screen ***");
//                        logger.info("***** The target element is not found in the current screen. Whether to perform path search?(0-false/1-true) *****");
//                        Scanner sc = new Scanner(System.in);
//                        int flag = sc.nextInt();
                        int flag = 1;// 开启路径修复
                        int start_x=0, start_y=0, end_x=0, end_y=0;
                        if (flag == 1){
                            List<Integer> coord = UtilsRepair.getSwipedElement(curLayoutXmlFile);
                            if (coord.size()!=0){
                                start_x = coord.get(0);
                                start_y = coord.get(1);
                                end_x = coord.get(2);
                                end_y = coord.get(3);
                                // 滑动搜索
                                long startSwipe = System.currentTimeMillis();
                                targetElement = UtilsRepair.retrieveElementFromSwipedPage(start_x, start_y, end_x, end_y, driver, (EnhancedMobileElement) statement,isContextConsidered);
                            }
                            if(coord.size()!=0 && targetElement!=null){
                                EnhancedTouchAction swipeAction = new EnhancedTouchAction("swipe");
                                // 滑动的起始坐标和终止坐标
                                swipeAction.setStartPoint(new Point(start_x, start_y));
                                swipeAction.setEndPoint(new Point(end_x, end_y));
                                noSuchElement = false;
                                // 捕获当前界面信息（滑动后）
                                String swipeLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
                                UtilsHierarchyXml.takeXmlSnapshot(driver, swipeLayoutXmlFile);
                                AppiumLocator locator = UtilsRepair.getAppropriateLocator(driver, (EnhancedMobileElement) statement, targetElement, swipeLayoutXmlFile);
                                // 添加新增的滑动语句到修复语句列表
                                repairedStmMap.put(statementNum+addedStmNum,swipeAction);
                                // 保存已执行语句，便于之后back操作
                                executedStm.add(swipeAction);
                                // 统计增加的语句数量，用于对齐新旧测试用例的行号
                                addedStmNum++;
                                // 统计增加的点击语句数量
                                addedSwipeNum++;
                                ((EnhancedMobileElement)repairedStm).setLocator(locator);
                                repairedStmMap.put(statementNum+addedStmNum,repairedStm);
                            }else {
                                if (coord.size()!=0){
                                    // 恢复滑动前界面
                                    logger.info("***** Restore the screen before swiping *****");
                                    new TouchAction(driver).longPress(PointOption.point(end_x, end_y)).moveTo(PointOption.point(start_x, start_y)).release().perform();
                                    Thread.sleep(5000);
                                }
                                // 3.2 触发界面跳转后重新搜索
//                                logger.info("***** The target element is not found after swiping, whether to perform path search (click)?(0-false/1-true) *****");
//                                Scanner sc2 = new Scanner(System.in);
//                                int flag2 = sc2.nextInt();
                                logger.info("*** 3.2 Retrieve Element From Neighbor Screen ***");
                                int flag2 = 1;
                                if(flag2 == 1){
                                    List<EnhancedMobileElement> clickElement = new ArrayList<>();
                                    long startNeighbor = System.currentTimeMillis();
                                    targetElement = UtilsRepair.retrieveElementFromNeighborPage(driver,(EnhancedMobileElement) statement,curLayoutXmlFile,clickElement,isContextConsidered);
                                    if(targetElement!=null){
                                        noSuchElement = false;
                                        // 捕获当前界面信息（点击后）
                                        String clickLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
                                        UtilsHierarchyXml.takeXmlSnapshot(driver, clickLayoutXmlFile);
                                        AppiumLocator locator = UtilsRepair.getAppropriateLocator(driver, (EnhancedMobileElement) statement, targetElement, clickLayoutXmlFile);
                                        // 添加新增的语句到修复语句列表
                                        repairedStmMap.put(statementNum+addedStmNum,clickElement.get(0));
                                        // 统计增加的语句数量，用于对齐新旧测试用例的行号
                                        addedStmNum++;
                                        // 统计增加的点击语句数量
                                        addedClickNum++;
                                        ((EnhancedMobileElement)repairedStm).setLocator(locator);
                                        repairedStmMap.put(statementNum+addedStmNum,repairedStm);
                                        // 保存已执行语句，便于之后back操作
                                        executedStm.add(clickElement.get(0));
                                    }else{
                                        // 删除元素不会删除 repairedStmMap 中的对应项，而是直接将对应value置为null
                                        repairedStmMap.put(statementNum+addedStmNum, null);
                                    }
                                } else{
                                    repairedStmMap.put(statementNum+addedStmNum, null);
                                }
                            }
                        } else{
                             repairedStmMap.put(statementNum+addedStmNum, null);
                        }
                    }
                }
            }
            // 如果找到目标元素，执行修复后的语句
            // isCorrectUsed: 执行修复后的语句 或 执行标准答案
            if(isCorrectUsed){
                if(isAddPath){
                    if(map.containsKey(statementNum)){
                        List<Integer> lines = map.get(statementNum);
                        if(lines.size()>0){
                            for (Integer line : lines) {
                                Statement correctStatement = correctStmMap.get(line);
                                if (correctStatement != null) {
                                    MobileElement correctElement = UtilsRepair.getElementFromStatement(driver, ((EnhancedMobileElement) correctStatement).getLocator());
                                    fireEvent(correctElement, correctStatement);
                                    // 保存已执行语句，便于之后back操作
                                    executedStm.add(correctStatement);
                                }
                            }
                        }
                    }
                }else {
                    Statement correctStatement = correctStmMap.get(statementNum);
                    if(correctStatement!=null){
                        MobileElement correctElement = UtilsRepair.getElementFromStatement(driver, ((EnhancedMobileElement)correctStatement).getLocator());
                        fireEvent(correctElement,correctStatement);
                        // 保存已执行语句
                        executedStm.add(correctStatement);
                    }
                }
            } else {
                if(!noSuchElement){
                    fireEvent(targetElement,repairedStm);
                    executedStm.add(repairedStm);
                }
            }
            previousStatement = statement;
        }

        // 修复时间结算
        stopTime = System.currentTimeMillis();
        // 需要刨除点击等待和滑动等待的时间
        elapsedTime = stopTime - startTime - (5000L * repairedStmMap.size());
        // 生成新的EnhancedTestCase测试用例
        logger.info("*** Print repair result ***");
        EnhancedTestCase testRepaired = generateNewTest(testBroken, repairedStmMap);
        Thread.sleep(2000);
        // 保存测试用例
        logger.info("*** Generate and Save new test script ***");
        UtilsRepair.saveTest(pt, testRepaired, testBroken.getPath());
        // 打印修复结果
        shutDown(addedClickNum,addedSwipeNum);
    }

    // 判断当前语句与上一条语句所操作的对象元素是否相同
    private boolean identicalWithLastStatement(Statement statement, Statement previousStatement) {
        if (previousStatement == null) {
            return false;
        }
        if(!(statement instanceof EnhancedMobileElement) || !(previousStatement instanceof EnhancedMobileElement)) {
            return false;
        }

        AppiumLocator target = ((EnhancedMobileElement) statement).getLocator();
        AppiumLocator src = ((EnhancedMobileElement) previousStatement).getLocator();
        if (target == null || src == null) {
            return false;
        }
        if (target.getStrategy().equalsIgnoreCase(src.getStrategy()) && target.getValue().equalsIgnoreCase(src.getValue())) {
            return true;
        }
        return false;
    }

    // 根据修复语句 statement 决定对该元素的操作方式：clear、click、sendKeys
    public static void fireEvent(MobileElement element, Statement statement){
        if (statement.getAction().equals(AppiumAction.Clear)) {
            logger.info("Clear Element: " + ((EnhancedMobileElement)statement).getLocator().toString());
            element.clear();
        } else if (statement.getAction().equals(AppiumAction.Click)) {
            logger.info("*** Click Element: " + ((EnhancedMobileElement)statement).getLocator().toString() + " ***");
            element.click();
            // 元素点击后需要等待 5s 以待新界面加载完成
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.info("Driver wait has been interrupted");
            }
        } else if (statement.getAction().equals(AppiumAction.SendKeys)) {
            logger.info("SendKeys Element: " + ((EnhancedMobileElement)statement).getLocator().toString());
            element.sendKeys(statement.getValue());
            // 元素输入后需要等待 5s 以待新界面加载完成
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.info("Driver wait has been interrupted");
            }
        } else if (statement.getAction().equals(AppiumAction.getText)) {
            logger.info("getText Element: " + ((EnhancedMobileElement)statement).getLocator().toString());
            Assert.assertEquals(statement.getValue(),element.getText());
            // 元素输入后需要等待 5s 以待新界面加载完成
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.info("Driver wait has been interrupted");
            }
        } else {
            logger.info("UnKnown operation! Cannot fire event");
        }
    }

    // 根据崩溃测试用例和修复语句生成修复后的测试用例
    private EnhancedTestCase generateNewTest(EnhancedTestCase testBroken, Map<Integer, Statement> repairedStmMap) {
        // 打印失效测试用例
        System.out.println("\n Original Statements:");
        UtilsRepair.printTestCase(testBroken);

        // 示例：output/RepairedTC/LarkPlayer/TC1.java
        String repairedTCSavedPath = Settings.repairedTCPath + Settings.sep + appEnum.getAppName()
                + Settings.sep + testBroken.getName() + Settings.JAVA_EXT;
        EnhancedTestCase repairedTest = new EnhancedTestCase(testBroken.getName(), repairedTCSavedPath);
        repairedTest.setStatements(repairedStmMap);
        // 打印修复完成测试用例
        System.out.println("\n Updated Statements:");
        UtilsRepair.printTestCase(repairedTest);
        System.out.println();
        return repairedTest;
    }

    // 处理退出信息
    private void shutDown(int pathAddNum, int swipeAddNum){
        // 打印修复信息
        logger.info("Repair time: {} s。", String.format("%.3f", (elapsedTime-5000) / 1000.0f));
        logger.info("Cache clearing");
        // 清理临时层次布局文件存储目录
        File tempFolder = new File(Settings.TEMP_XML_SAVED_FOLDER);
        String[] content = tempFolder.list();
        for(String name : content) {
            File temp = new File(Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + name);
            if(!temp.delete()){
                logger.error("Failed to delete " + name);
            }
        }
        driver.quit();
        logger.info("Quit successfully");
    }
}
