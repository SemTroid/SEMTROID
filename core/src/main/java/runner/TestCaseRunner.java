package main.java.runner;

import main.java.config.AppEnum;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCaseRunner {
    private static Logger logger = LoggerFactory.getLogger(TestCaseRunner.class);

    public static void main(String[] args) {
        String testSuite = AppEnum.Anki.getTestSuite();
        String testCase = "FourTest";

        long startTime;
        long finishTime;
        long elapsedTime;
        String testCaseToRun = testSuite + "." + testCase;

        Result result = null;
        startTime = 0;
        try {
            logger.info("Running test " + testCaseToRun);
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
            logger.info("Test " + testCaseToRun + " passed!");
        }

        finishTime = System.currentTimeMillis();
        elapsedTime = finishTime - startTime;
        logger.info("Testcase Execute in {} s", String.format("%.3f", elapsedTime / 1000.0f));
    }
}
