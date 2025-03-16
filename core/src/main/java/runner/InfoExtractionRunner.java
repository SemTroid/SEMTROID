package main.java.runner;

import main.java.config.AppEnum;
import main.java.config.Settings;
import main.resources.FotMob.DeleteFavoriteTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @Version 1.0
* @Author QS
* @Time 2024/2/14 11:08
* @Desc
*/
public class InfoExtractionRunner {

    private static Logger logger = LoggerFactory.getLogger(InfoExtractionRunner.class);

    public static void main(String[] args){
        // enable the AspectJ module
        Settings.aspectActive = true;
        RepairRunner.isContextConsidered = true;
        // 配置：应用、测试用例、信息提取存储地址
        Settings extractorSetting = new Settings(AppEnum.Anki);
        // 执行测试用例
        runTest(extractorSetting.testSuite, "TwentyNineTest");
    }

    /**
     * Run a single test case when a runner class is specified.
     * @param testSuite
     * @param testCase
     */
    public static void runTest(String testSuite, String testCase){

        long startTime = 0;
        long finishTime;
        long elapsedTime;

        // build the class runner
        // 要执行的测试用例类名
        String testCaseToRun = testSuite + "." + testCase;

        // run the test programmatically
        // 执行测试用例
        Result result = null;
        try {
            logger.info("Start trace: " + testCaseToRun);
            startTime = System.currentTimeMillis();

            result = JUnitCore.runClasses(Class.forName(testCaseToRun));
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        // If the test failed, save the exception.
        if(!result.wasSuccessful()){
            logger.info("Test " + testCaseToRun + " failed, saving the exception.");

            // For each breakage, record it.
            for (Failure failure : result.getFailures()) {
                logger.error(String.valueOf(failure));
            }
        } else {
            logger.info("Test " + testCaseToRun + " passed");
        }

        finishTime = System.currentTimeMillis();
        elapsedTime = finishTime - startTime - (5000L * 4);// 减掉等待时间
        logger.info("Trace time: {} s", String.format("%.3f", elapsedTime / 1000.0f));

    }
}
