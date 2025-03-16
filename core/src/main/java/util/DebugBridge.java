package main.java.util;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;

import java.util.Arrays;
import java.util.List;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/4 10:50
 */
public class DebugBridge {

    // adb 命令，确保已经将 sdk/platform-tools 加入系统变量
    private static final String ADB_COMMAND = "adb";
    private static AndroidDebugBridge debugBridge;

    public static void init() {
        AndroidDebugBridge.init(false);
        debugBridge = AndroidDebugBridge.createBridge(ADB_COMMAND, false);

        waitForDevice(debugBridge);
    }

    /**
     * 等待 ADB 命令连接设备
     * @param bridge
     */
    private static void waitForDevice(AndroidDebugBridge bridge) {
        int count = 0;
        while (!bridge.hasInitialDeviceList()) {
            try {
                Thread.sleep(20);
                count++;
            } catch (InterruptedException ignored) {
            }

            if (count > 300) {
                System.err.print("Time out");
                break;
            }
        }
    }

    public static boolean isInitialized() {
        return debugBridge != null;
    }

    public static void terminate() {
        if (debugBridge != null) {
            debugBridge = null;
            AndroidDebugBridge.terminate();
        }
    }

    public static List<IDevice> getDevices() {
        return Arrays.asList(debugBridge.getDevices());
    }
}
