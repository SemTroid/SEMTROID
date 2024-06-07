package main.java.runner;

import main.java.config.AppEnum;
import main.java.config.Settings;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @Version 1.0
* @Author anonymous author
*/
public class TraceRunner {

    private static Logger logger = LoggerFactory.getLogger(TraceRunner.class);

    public static void main(String[] args){
        // enable the AspectJ module
        Settings.aspectActive = true;
        RepairRunner.isContextConsidered = true;
        // App
        Settings extractorSetting = new Settings(AppEnum.DaysMatter);
        // TestCase
        runTest(extractorSetting.testSuite, "");
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
        String testCaseToRun = testSuite + "." + testCase;

        // run the test programmatically
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
        elapsedTime = finishTime - startTime;
        logger.info("Trace time: {} s", String.format("%.3f", elapsedTime / 1000.0f));
    }
}
