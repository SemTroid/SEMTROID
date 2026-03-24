package main.java.util;

/**
 * @author QS
 * @version 1.0
 * @date 2021/6/30 10:36
 */

import io.appium.java_client.MobileElement;
import main.java.dataType.ViewTreeInfo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static main.java.util.UtilsParser.gson;

/**
 * This class refers to the class in Vista
 */
public class UtilsAspect {

    private static Logger logger = LoggerFactory.getLogger(UtilsAspect.class);

    /**
     * 创建文件夹
     * @param path
     */
    public static void createTestFolder(String path) {
        File theDir = new File(path);
        if(!theDir.exists()){
            boolean result = theDir.mkdirs();
            if(result){
//                logger.info("Directory is created!");
            } else {
                logger.info("Directory failed to create!");
            }
        }
    }

    /**
     * 保存目标元素的视图树信息
     * @param me
     * @param viewTreeInfoJsonFile
     */
    public static void saveViewTreeInformation(MobileElement me, String hierarchyLayoutXmlFile, String viewTreeInfoJsonFile) {
        ViewTreeInfo mobileEleWithViewTreeInfo = new ViewTreeInfo(me, hierarchyLayoutXmlFile);

        try {
            FileUtils.writeStringToFile(new File(viewTreeInfoJsonFile), gson.toJson(mobileEleWithViewTreeInfo, ViewTreeInfo.class), Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
