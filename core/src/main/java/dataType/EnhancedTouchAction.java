package main.java.dataType;

import org.openqa.selenium.Point;

public class EnhancedTouchAction extends Statement{

    private static final long serialVersionUID = 1L;

    private Point startPoint;
    private Point endPoint;

    // touchAction 目前仅考虑 swipe 和 tap(不支持)
    public EnhancedTouchAction(String touchAction) {
        super();
        this.appiumAction = AppiumAction.Touch;
        this.value = touchAction;
    }

    public EnhancedTouchAction(int line, String touchAction) {
        super(line);
        this.appiumAction = AppiumAction.Touch;
        this.value = touchAction;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        if (getValue().equals("swipe")) {
            return "new TouchAction(driver).longPress(PointOption.point" + startPoint + ").moveTo(PointOption.point" + endPoint +").release().perform()";
        } else if (getValue().equals("tap")) {
            return "new TouchAction(driver).tap" + startPoint + ".perform()";
        }
        return null;
    }
}
