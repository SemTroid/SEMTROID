package main.java.dataType;

import java.io.Serializable;


/**
 * @author QS
 * @version 1.0
 * @date 2022/1/14 10:50
 */
public class Statement implements Serializable {
    /* Statement's info */
    protected int line;
    protected AppiumAction appiumAction;
    protected String value;

    public Statement() {}

    public Statement(int line) {
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public AppiumAction getAction() {
        return appiumAction;
    }

    public void setAction(AppiumAction appiumAction) {
        this.appiumAction = appiumAction;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
