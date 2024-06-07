package main.java.config;

// 应用及测试用例集信息
public enum AppEnum {
    // 实验对象
    UberEats("UberEats", "main.resources.UberEats"),

    YouTube("YouTube", "main.resources.YouTube"),

    GoogleTranslate("GoogleTranslate","main.resources.GoogleTranslate"),

    LarkPlayer("LarkPlayer", "main.resources.LarkPlayer"),

    FotMob("FotMob", "main.resources.FotMob"),

    GoogleNews("GoogleNews", "main.resources.GoogleNews"),

    DaysMatter("DaysMatter", "main.resources.DaysMatter"),

    Reddit("Reddit", "main.resources.Reddit");


    private String appName;

    // 旧版应用测试用例集存放包名
    private String testSuite;

    AppEnum(String appName, String testSuite) {
        this.appName = appName;
        this.testSuite = testSuite;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTestSuite() {
        return testSuite;
    }

    public void setTestSuite(String testSuite) {
        this.testSuite = testSuite;
    }
}
