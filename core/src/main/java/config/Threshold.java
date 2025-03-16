package main.java.config;

public enum Threshold {
    // Add: 元素相似度阈值
    ELE_SIM(0.7),

    // Add: 页面相似度阈值
    PAGE_SIM(0.5),

    // Add: 上下文相似度权重
    CONTEXT_WEIGHT(0.75);

    private double value;

    Threshold(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}