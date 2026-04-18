package main.java.util;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/5 16:09
 */
public class RootWindowNode extends XmlTreeNode {
    private final String mWindowName;
    private int mRotation;

    public RootWindowNode(String windowName) {
        this(windowName, 0);
    }

    public RootWindowNode(String windowName, int rotation) {
        mWindowName = windowName;
        mRotation = rotation;
    }

    @Override
    public String toString() {
        return mWindowName;
    }

    public int getRotation() {
        return mRotation;
    }
}
