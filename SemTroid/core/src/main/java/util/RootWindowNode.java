package main.java.util;

/**
 * @author anonymous author
 * @version 1.0
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
