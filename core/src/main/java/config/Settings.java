package main.java.config;

import java.io.File;

/**
 * @author QS
 * @version 1.0
 * @date 2023/12/24 17:02
 */
public class Settings {

    public String appName;
    public String testSuite;
    // 旧版应用元素信息抽取存储文件夹
    public String referenceInfoExtractionFolder;

    public Settings(AppEnum app) {
        this.appName = app.getAppName();
        this.testSuite = app.getTestSuite();
        this.referenceInfoExtractionFolder = extractInfoPath + sep + testSuite.substring(testSuite.lastIndexOf(".") + 1) + sep;
    }

    public static String sep = File.separator;

    public static String extractInfoPath = "output" + sep + "AppInfo3";
    public static String repairedTCPath = "output" + sep + "RepairedTC3";

    // 修复过程需要获取层次布局信息，此文件夹用于临时存储，修复结束需要清理
    public static final String TEMP_XML_SAVED_FOLDER = repairedTCPath + Settings.sep + "TempXmlSaved";

    public static final String XML_EXT = ".xml";
    public static final String JSON_EXT = ".json";
    public static final String JAVA_EXT = ".java";

    public static boolean aspectActive = false;
}
