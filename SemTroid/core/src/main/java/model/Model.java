package main.java.model;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import main.java.util.UtilsRepair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Model {

    private static Logger log = LoggerFactory.getLogger(Model.class);
    private static final Interpreter interpreter;
    static {
        interpreter = new SharedInterpreter();
        interpreter.exec("import sys");
        interpreter.exec("sys.argv.append('')");
        interpreter.exec("sys.path.append('core/src/main/resource/resnet50')");
        interpreter.exec("import semantic_model");
    }
    public static void encodeTexts(List<String> texts) {
        try {
            interpreter.invoke("semantic_model.encode_texts", texts);
        } catch (JepException exception) {
            exception.printStackTrace();
        }
    }
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
            return 0;
        }
    }

    public static double getSimilarity(String text, byte[] bytes) {
        try {
            boolean isPure = (boolean) interpreter.invoke("semantic_model.is_pure", bytes);
            if(isPure){
                return 0;
            }
            return (double) interpreter.invoke("semantic_model.sim_text2image", text, bytes);
        } catch (JepException exception) {
            // exception.printStackTrace();
            return 0;
        }
    }

    public static double getSimilarity(byte[] bytes1, byte[] bytes2) {
        try {
            boolean isPure_1 = (boolean) interpreter.invoke("semantic_model.is_pure", bytes1);
            boolean isPure_2 = (boolean) interpreter.invoke("semantic_model.is_pure", bytes2);
            if(isPure_1 || isPure_2){
                return 0;
            }
            return (double) interpreter.invoke("semantic_model.sim_image2image", bytes1, bytes2);
        } catch (JepException exception) {
            // exception.printStackTrace();
            return 0;
        }
    }
}
