package main.java.util;

import main.java.config.Settings;
import main.java.dataType.ViewTreeInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/13 11:51
 */
public class UtilsFileGetter {
    /**
     * @param name 测试用例名称
     * @param pathToTestSuite 通向测试用例集的路径
     * @return 测试用例的绝对路径
     */
    public static String getTestFile(String name, String pathToTestSuite) {
        File[] files = new File(pathToTestSuite).listFiles(FileFilter.javaFileFilter);
        for (File file: files) {
            if(file.getName().contains(name)) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }

    public static ViewTreeInfo getViewTreeInfoFromJsonFile(String tcName, int beginLine, String type, String folder) throws Exception{
        String p = folder.replaceAll("\\.", "\\\\") + Settings.sep + tcName + Settings.sep;

        File dir = new File(p);
        File[] listOfFiles = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String n) {
                return (n.startsWith(Integer.toString(beginLine)) && n.endsWith(Settings.JSON_EXT) && n.contains(type));
            }
        });

        if (listOfFiles == null) {
            System.out.printf("\tbeginLine: %d, testCaseName: %s, type: %s\n", beginLine, tcName, type);

            throw new Exception("[LOG]\tNo JSON file retrieved");

        } else if (listOfFiles.length == 1) {

            ViewTreeInfo obj = UtilsParser.gson.fromJson(new BufferedReader(new FileReader(listOfFiles[0])),
                    ViewTreeInfo.class);

            return obj;

        } else {
            throw new Exception("[LOG]\tToo many files retrieved");
        }
    }
}
