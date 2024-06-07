package main.java.config;

public enum Threshold {
    // 元素相似度阈值
    ELE_SIM(0.6),
    // 页面相似度阈值
    PAGE_SIM(0.5),
    // 上下文相似度权重
    CONTEXT_WEIGHT(0.6),
    // 跨界面增强候选上下文集合大小
    SET_SIZE(5);

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