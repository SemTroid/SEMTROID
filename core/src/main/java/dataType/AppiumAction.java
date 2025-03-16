package main.java.dataType;

public enum AppiumAction {
    Click("click"),
    SendKeys("sendKeys"),
    Clear("clear"),
    Touch("touch"),
    Navigate("navigate"),
    getText("getText");

    private String value;

    AppiumAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
