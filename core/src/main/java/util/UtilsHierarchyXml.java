package main.java.util;

import com.android.ddmlib.CollectingOutputReceiver;
import com.android.ddmlib.IDevice;
import io.appium.java_client.android.AndroidDriver;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author QS
 * @version 1.0
 * @date 2021/12/31 15:43
 */
public class UtilsHierarchyXml {

    private static Logger logger = LoggerFactory.getLogger(UtilsHierarchyXml.class);

    private static final String UIAUTOMATOR = "uiautomator";
    private static final String UIAUTOMATOR_DUMP_COMMAND = "dump";
    private static final String UIDUMP_DEVICE_PATH = "/data/local/tmp/uidump.xml";
    private static final int XML_CAPTURE_TIMEOUT_SEC = 10;


    /**
     * 弃用原因：UIAutomation Client只能有一个连接，测试用例运行时独占了，导致 adb shell命令根本无法使用
     *          但 adb pull 命令还是可以使用的，所以获取到的 XML 文件永远是预存在相应路径上的
     *          参考：https://stackoverflow.com/questions/37016366/uiautomator-dump-in-runtime-getruntime-exec-not-working
     */
    @Deprecated
    public static void getHierarchyFile(String filePath) {
        try {
            long startTime = System.currentTimeMillis();
            // 执行该命令的前提是 adb 命令所在的文件夹被设置为系统变量
            String cmd = String.format("adb shell %s %s --compressed %s", UIAUTOMATOR, UIAUTOMATOR_DUMP_COMMAND, UIDUMP_DEVICE_PATH);
            // 创建实例进程执行命令行代码
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();

            cmd = String.format("adb pull %s %s", UIDUMP_DEVICE_PATH, filePath);
            p = Runtime.getRuntime().exec(cmd);
            p.waitFor();
            p.destroy();
            logger.info("层次布局文件获取成功！");

            long endTime = System.currentTimeMillis();
            logger.info("TakeSnapshot in {} s", String.format("%.3f", (endTime - startTime) / 1000.0f));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("命令行执行失败！");
        }
    }

    /**
     * 获取当前Android APP界面 XML 层次布局文件
     * @param filePath 文件存放路径
     *        弃用原因：UIAutomation Client被占用，无法使用 adb shell 命令，不能生成 XML 文件
     */
    @Deprecated
    public static void getHierarchyXmlFile(String filePath) {
        final IDevice device = pickDevice();

        if (device == null) {
            logger.error("Android 设备获取失败！");
            return;
        }

        // 获取层次布局文件
        takeXmlSnapshot(device, filePath);
    }

    /**
     * @return 通过调试桥获取当前安卓设备
     */
    public static IDevice pickDevice() {
        if(!DebugBridge.isInitialized()) {
            logger.error("ADB 初始化失败！");
            return null;
        }else {
            List<IDevice> devices = DebugBridge.getDevices();
            if (devices.size() == 0) {
                logger.error("ADB未检测到Android设备！");
                return null;
            } else {
                // 返回获取的第一个Android设备
                return devices.get(0);
            }
        }
    }

    /**
     * 获取Android设备当前页面层次布局文件
     * @param device
     * @param filePath
     */
    public static void takeXmlSnapshot(IDevice device, String filePath) {
        long startTime = System.currentTimeMillis();

        // 此时已进入adb shell, 命令样式：rm /data/local/tmp/uidump.xml
        // 主机上模拟该操作可以使用命令：adb shell rm /data/local/tmp/uidump.xml
//        String command = "rm " + UIDUMP_DEVICE_PATH;
//
//        CountDownLatch commandCompleteLatch = new CountDownLatch(1);
//        try {
//            device.executeShellCommand(command, new CollectingOutputReceiver());
//            commandCompleteLatch.await(100, TimeUnit.MILLISECONDS);
//        } catch (Exception e1) {
//            // ignore exceptions while deleting stale files
//        }

        // 此时已进入adb shell, 命令样式：uiautomator dump --compressed /data/local/tmp/uidump.xml
        // 主机上模拟该操作可以使用命令：adb shell uiautomator dump --compressed /data/local/tmp/uidump.xml
        // 生成简洁版的层次布局文件，要生成完整版的可去除 --compressed 关键字
        // 新的层次布局文件会将老的文件覆盖，因此无需使用 rm 命令
        String command = String.format("%s %s --compressed %s", UIAUTOMATOR, UIAUTOMATOR_DUMP_COMMAND, UIDUMP_DEVICE_PATH);

        try {
            device.executeShellCommand(command, new CollectingOutputReceiver(), XML_CAPTURE_TIMEOUT_SEC * 1000);
            // 获取xml文件, 存储到filePath指定的地方（注意是绝对路径）
            // 主机上模拟该操作可以使用命令：adb pull /data/local/tmp/uidump.xml ${filePath}
            device.pullFile(UIDUMP_DEVICE_PATH, filePath);
            logger.info("层次布局文件获取成功！");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long endTime = System.currentTimeMillis();
        logger.info("TakeSnapshot in {} s", String.format("%.3f", (endTime - startTime) / 1000.0f));
    }

    public static void takeXmlSnapshot(AndroidDriver driver, String filePath) {
        try {
            String xmlContent = driver.getPageSource();
            FileUtils.writeStringToFile(new File(filePath), xmlContent, Charset.defaultCharset());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
