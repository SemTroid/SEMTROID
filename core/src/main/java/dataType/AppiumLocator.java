package main.java.dataType;

import java.io.Serializable;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/14 10:57
 */
public class AppiumLocator implements Serializable {
    /* strategy 合理取值有：resourceId、contentDesc、xpath */
    // TODO: classname 和 uiautomator
    private String strategy, value;

    public AppiumLocator(String strategy, String value) {
        this.strategy = strategy;
        this.value = value;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        if (strategy.equals("resourceId")) {
            return "ById(\"" + value + "\")";
        } else if(strategy.equals("contentDesc")) {
            return "ByAccessibilityId(\"" + value + "\")";
        } else if(strategy.equals("xpath")) {
            return "ByXPath(\"" + value + "\")";
        }
        return "By" + (char)(strategy.charAt(0) - 32) + strategy.substring(1) + "(\"" + value + "\")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AppiumLocator) {
            return this.strategy.equals(((AppiumLocator) obj).getStrategy())
                    && this.value.equals(((AppiumLocator) obj).getValue());
        }
        return false;
    }
}
