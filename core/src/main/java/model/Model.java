package main.java.model;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import main.java.util.UtilsRepair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

// 语义模型
public class Model {

    private static Logger log = LoggerFactory.getLogger(Model.class);
    private static final Interpreter interpreter;
    // 静态代码块，Model类被调用时首先自动执行Python脚本
    static {
        interpreter = new SharedInterpreter();
        interpreter.exec("import sys");
        // sys.argv为一个数组，0为代码文件路径，1开始为传入的参数，此处设置第1个参数为空字符
        interpreter.exec("sys.argv.append('')");
        // sys.path包含Python搜索模块的目录列表，使用append添加搜索路径，再导入模块
        interpreter.exec("sys.path.append('core/src/main/resource/resnet50')");
        interpreter.exec("import semantic_model");
    }
    // 文本转换为编码
    public static void encodeTexts(List<String> texts) {
        try {
            interpreter.invoke("semantic_model.encode_texts", texts);
            // 调用 方法+参数
        } catch (JepException exception) {
            exception.printStackTrace();
        }
    }
    // 图像转换为编码
    public static void encodeImages(List<byte[]> images) {
        try {
            interpreter.invoke("semantic_model.encode_images", images);
        } catch (JepException exception) {
            exception.printStackTrace();
        }
    }

    // 相似度计算
    public static double getSimilarity(String text1, String text2) {

        try {
            return (double) interpreter.invoke("semantic_model.sim_text2text", text1, text2);
        } catch (JepException exception) {
            // exception.printStackTrace();
            log.info("文本异常，返回相似度0。");
            return 0;
        }
    }

    public static double getSimilarity(String text, byte[] bytes) {
        // Add: 若DOM中元素实际已被覆盖，截图得到不正确的图像（不规范无意义），
        // 图像处理时会抛出异常，此时相似度返回0。
        try {
            boolean isPure = (boolean) interpreter.invoke("semantic_model.is_pure", bytes);
            if(isPure){
                log.info("存在纯色图片，返回相似度0。");
                return 0;
            }
            return (double) interpreter.invoke("semantic_model.sim_text2image", text, bytes);
        } catch (JepException exception) {
            // exception.printStackTrace();
            log.info("图像不合法，返回相似度0。");
            return 0;
        }
    }

    public static double getSimilarity(byte[] bytes1, byte[] bytes2) {
        // Add: 若DOM中元素实际已被覆盖，截图得到不正确的图像（不规范无意义），
        // 图像处理时会抛出异常，此时相似度返回0。
        try {
            boolean isPure_1 = (boolean) interpreter.invoke("semantic_model.is_pure", bytes1);
            boolean isPure_2 = (boolean) interpreter.invoke("semantic_model.is_pure", bytes2);
            if(isPure_1 || isPure_2){
                log.info("存在纯色图片，返回相似度0。");
                return 0;
            }
            return (double) interpreter.invoke("semantic_model.sim_image2image", bytes1, bytes2);
        } catch (JepException exception) {
            // exception.printStackTrace();
            log.info("图像不合法，返回相似度0。");
            return 0;
        }
    }

    // 判断图片是否为近似纯色
    public static boolean isPure(byte[] bytes){
        return (boolean) interpreter.invoke("semantic_model.is_pure", bytes);
    }
}
