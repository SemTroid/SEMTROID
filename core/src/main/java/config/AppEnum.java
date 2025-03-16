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

    Reddit("Reddit", "main.resources.Reddit"),

    Anki("Anki","main.resources.Anki"),

    NetworkMo("NetworkMo","main.resources.NetworkMo"),

    AntenPod("AntenPod","main.resources.AntenPod"),

    KeePass("KeePass","main.resources.KeePass"),

    OIFile("OIFile","main.resources.OIFile");
    // 测试对象
//    UniversalTV("UniversalTV", "main.resources.UniversalTV"),// 测试用
//    testFotMob("FotMob", "main.resources.testFotMob"),// 测试用
//    AlarmMon("AlarmMon", "main.resources.AlarmMon"),
//    BBCNews("BBCNews", "main.resources.BBCNews"),
//    HryFine("HryFine", "main.resources.HryFine"),
//    iReader("iReader", "main.resources.iReader"),
//    // LarkPlayer("LarkPlayer", "main.resources.LarkPlayer"),
//    Notepad("Notepad", "main.resources.Notepad"),
//    SenseWeather("SenseWeather", "main.resources.SenseWeather"),
//    Webtoon("Webtoon", "main.resources.Webtoon"),
//    APIDemo("APIDemo", "main.resources.APIDemo");

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
