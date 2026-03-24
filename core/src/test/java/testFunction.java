package test.java;
import main.java.model.Model;

import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class testFunction {


    // 工具方法：byte数组 转 图像
    public static void byte2png(byte[] data, String path){
        try{
            FileImageOutputStream imageOutput = new FileImageOutputStream(new File(path));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
//            System.out.println("Make Picture success,Please find image in " + path);
        } catch(Exception ex) {
            System.out.println("Exception: " + ex);
            ex.printStackTrace();
        }
    }
    // 工具方法：
    public static byte[] png2byte(String path){
        byte[] data = null;
        FileImageInputStream input = null;
        try {
            input = new FileImageInputStream(new File(path));
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int numBytesRead = 0;
            while ((numBytesRead = input.read(buf)) != -1) {
                output.write(buf, 0, numBytesRead);
            }
            data = output.toByteArray();
            output.close();
            input.close();
        } catch (FileNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (IOException ex1) {
            ex1.printStackTrace();
        }
        return data;
    }

    // 工具方法：修复工具生成Xpath格式 转 Inspector生成Xpath格式
    public static void printXpath(String xpath){
        System.out.println(xpath.replaceAll("\\[1\\]", "").substring(1));
    }

    public static void test(Object obj){
        System.out.println(((ArrayList<String>)obj).get(0));
        System.out.println(((ArrayList<String>)obj).get(1));
    }

    public static void main(String[] args) throws Exception {

//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("platformVersion", "9");
//        desiredCapabilities.setCapability("deviceName", "device");
//        desiredCapabilities.setCapability("automationName", "UiAutomator2");
//        desiredCapabilities.setCapability("appPackage", "com.reddit.frontpage");
//        desiredCapabilities.setCapability("appActivity", "launcher.default");
//        desiredCapabilities.setCapability("noReset", true);
//        desiredCapabilities.setCapability("dontStopAppOnReset", true);
//        desiredCapabilities.setCapability("udid", "emulator-5554");
//        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

//        List<Object> pageInfo_1 = (List<Object>)main.java.util.FileUtils.readObject("output/testResult/3-newInfo");
//        List<Object> pageInfo_2 = (List<Object>)main.java.util.FileUtils.readObject("output/testResult/3-oldInfo");
//        System.out.println(UtilsRepair.getEnhancedSimilarity(true, "output/testResult/app-source-uber2.xml", "output/testResult/app-source-uber1.xml"));
//        System.out.println();
//        System.out.println(UtilsRepair.getPageSimilarity(true, "output/testResult/app-source-uber2.xml", "output/testResult/app-source-uber1.xml"));
//        System.out.println();
//        System.out.println(UtilsRepair.getEnhancedSimilarity2(true, pageInfo_1, pageInfo_2));
//        System.out.println();
//        System.out.println(UtilsRepair.getEnhancedSimilarity3(true, "output/testResult/3-newFile.xml", "output/testResult/3-oldFile.xml"));

//         获取旧目标界面的xml布局文件
//        UtilsHierarchyXml.takeXmlSnapshot(driver, "output/testResult/3-newFile.xml");
//        // 获取旧目标界面中所有有效的文本和图像信息
//        long start = System.currentTimeMillis();
//        List<Object> pageInfo = UtilsRepair.getPageInfo(driver);
//        // 以文件形式保存pageInfo到指定目录
//        main.java.util.FileUtils.writeObject(pageInfo,"output/testResult/3-newInfo");
//        System.out.println(System.currentTimeMillis()-start);

//        System.out.println(Model.getSimilarity("111","111"));
//        long start = System.currentTimeMillis();
//        System.out.println(UtilsRepair.getPageSimilarity(true,"output/targetPageInfo/DM-share-newFile.xml","output/targetPageInfo/DM-share-oldFile.xml"));
//        System.out.println(System.currentTimeMillis()-start);
//        System.out.println();
//        long start2 = System.currentTimeMillis();
//        List<Object> pageInfo_1 = (List<Object>)main.java.util.FileUtils.readObject("output/targetPageInfo/DM-share-newInfo");
//        List<Object> pageInfo_2 = (List<Object>)main.java.util.FileUtils.readObject("output/targetPageInfo/DM-share-oldInfo");
//        System.out.println(UtilsRepair.getPageSimilarity2(true,pageInfo_1,pageInfo_2));
//        System.out.println(System.currentTimeMillis()-start2);
//        System.out.println();
//        long start3 = System.currentTimeMillis();
//        System.out.println(UtilsRepair.getPageSimilarity3(true,"output/targetPageInfo/DM-share-newFile.xml","output/targetPageInfo/DM-share-oldFile.xml"));
//        System.out.println(System.currentTimeMillis()-start3);

        // 读取检查
//        List<Object> pageInfo_1 = (List<Object>)main.java.util.FileUtils.readObject("output/targetPageInfo/GT-page-1");
//        System.out.println(UtilsRepair.getPageSimilarity2(true,pageInfo_1,pageInfo_2));
//        int n = 1;
//        for (Object o:pageInfo_2){
//            if(o instanceof String)
//                System.out.println("文本"+ o);
//            if (o instanceof byte[]){
//                System.out.println("图片");
//                byte2png((byte[]) o,"C:\\Users\\dell\\Desktop\\test_Image\\collect\\ico2_"+n+".png");
//                n++;
//            }
//        }

//
//        byte[] b1 = {-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 80, 0, 0, 0, 96, 8, 6, 0, 0, 0, -118, 107, -11, 0, 0, 0, 0, 4, 115, 66, 73, 84, 8, 8, 8, 8, 124, 8, 100, -120, 0, 0, 3, -47, 73, 68, 65, 84, 120, -100, -19, -101, 49, 78, 43, 49, 16, -122, 39, 8, -91, 101, -91, 8, -18, -79, -95, -124, 46, 5, 5, -57, 72, 46, 65, 79, 46, -108, 2, -119, 6, 113, 3, -50, 17, 39, 69, 110, 48, -81, -128, 60, -79, 94, -17, -114, 39, 99, 123, -68, 104, 62, 41, 69, -30, 89, 123, 98, -42, -1, -2, 99, 7, 0, 15, 68, -60, -79, -9, 84, 60, 5, -73, 63, 105, -1, -91, -13, 55, 12, 99, 90, 104, 107, 88, 110, -115, -29, -114, 39, -19, -33, 48, -116, 105, -95, -83, 33, -90, -127, -122, 97, -44, -124, 118, 45, 105, -75, -80, 97, 24, -102, 104, 107, 72, -60, -8, -24, -67, -72, -41, -77, -14, -95, -30, 103, -100, -117, 43, -63, -1, -62, -86, -33, -31, 90, 115, -16, 75, -16, 111, -104, -39, 76, -9, 30, -72, 82, 29, -3, 47, 80, 90, -61, 48, 64, -88, 29, 18, 105, 92, -18, -4, -117, -33, -1, 61, 17, -10, -42, -32, -71, -3, -41, -57, 85, -23, -76, -97, 127, 117, 26, -88, -83, 105, 92, 76, 3, 83, -109, -63, 71, 117, 124, -37, 4, 107, -31, -47, -4, 75, -84, -105, -86, 124, -37, 5, -116, -26, 111, 75, 88, -120, 77, -96, -108, 88, 13, 24, -14, 109, 84, 127, -71, 107, 81, -19, -4, 99, -12, 8, 127, 58, -6, -66, 96, 106, 62, 35, 115, -2, -92, 15, 100, -34, 32, -43, -111, 59, 127, -45, 64, 41, -91, 107, -55, 80, -68, 115, 14, -41, -21, 53, 46, -105, 75, 92, 46, -105, -72, 94, -81, -47, 57, 23, -44, 43, 110, -83, -102, 59, 127, 117, 61, 59, -99, 78, -8, -12, -12, 4, -50, -71, -50, -25, 119, 119, 119, -16, -10, -10, 6, -117, -59, -126, -107, 35, 85, 107, -89, 70, 125, 9, -65, -66, -66, -10, 38, 15, 0, -64, 57, 7, -37, -19, 86, 33, 35, 30, -22, 19, -8, -15, -15, 49, -40, -10, -7, -7, 89, 46, -111, 11, -23, 77, 96, 105, 31, 55, -97, -49, 7, 99, -25, -13, 57, 91, -29, 0, -66, -105, -19, -103, -36, -75, 112, 111, 2, 125, -51, -96, 52, -124, -85, 49, 126, -4, -29, -29, -29, 96, -20, -61, -61, -125, -72, -1, -36, -7, -85, 63, 68, -114, -57, 35, 62, 63, 63, 7, 31, 34, -17, -17, -17, -48, 52, 77, 112, -61, -11, 76, 1, 99, 95, -9, 102, 8, 34, -30, 126, -65, -57, -51, 102, -125, 109, -37, 98, -37, -74, -72, -39, 108, 112, -65, -33, 15, -38, -112, -40, -78, 44, 85, -118, -34, -85, -97, 80, -24, -126, 82, 62, 74, -22, -29, 98, -6, -25, 76, 122, 10, 31, -88, 90, -5, -90, 94, -94, -71, -105, 124, -81, 22, 46, -77, 42, 104, -90, -78, 103, -95, -18, 3, -89, 78, 112, 55, -26, -9, 109, -114, -120, 56, 118, -37, 83, -19, 84, 60, 34, -30, -31, 112, -128, -105, -105, 23, -8, -6, -6, -126, -5, -5, 123, 104, -37, 22, -100, 115, 112, 123, 123, 11, 0, -48, -117, -1, -55, -15, 127, -70, 84, 62, -95, -21, 99, -37, 67, -57, -82, -77, -95, -63, 53, -32, -42, -62, -44, 4, 82, -60, 76, -48, 88, -69, -113, -6, -71, 48, -73, 22, -82, 77, 27, -43, 53, 112, -22, -75, 112, -24, 41, -36, -45, 28, -95, 6, 118, 108, 17, 64, 119, 89, -60, -44, -62, 94, -1, -93, -73, 32, -107, 63, -87, 105, -3, -17, -30, -5, -62, 78, -116, -43, -62, -75, -41, -62, -108, 40, 115, 107, 97, 5, 116, 107, -31, -104, -46, -116, 83, 11, 43, -64, -85, -123, -71, -75, 104, 76, 127, -100, -8, 80, -5, 79, 76, 112, 63, -114, 59, -55, -39, 107, -31, -36, -75, 35, 23, -87, -17, -53, -115, -70, 15, -92, -88, -51, -9, -7, -88, -5, -64, -55, -29, 107, 94, 106, 13, -93, -30, 17, -57, -49, -123, 83, -12, -17, -121, 0, -29, -73, 50, 84, -1, -67, 67, -105, -46, -102, -105, -6, 92, 56, -126, -92, -5, -99, -22, 26, 88, -6, 92, 56, -75, 51, 82, -41, -64, 63, 81, 11, 75, -9, -13, -4, 24, 78, 45, 77, -43, -62, 49, -29, 75, -53, 49, 78, -1, -2, -9, -67, 74, 57, 120, -88, -99, -118, -89, 106, 97, 105, -1, -44, -11, -46, -2, -59, 2, 45, 125, 8, 77, -96, 22, -18, -48, -69, 3, -75, 18, 57, -77, 88, 44, 96, -73, -37, -63, 106, -75, -126, -90, 105, -96, 105, 26, 88, -83, 86, -80, -37, -19, -32, -26, -26, 70, 59, 61, -102, -40, 90, 52, -42, 55, 73, 125, 100, 96, -84, 81, -33, 38, -11, 113, -46, -4, -55, -27, 81, -38, 39, 6, -58, 59, 127, 94, 100, 124, 46, -22, 62, 112, 8, 127, -30, 106, 69, 93, 3, -89, -50, 53, -27, -29, -4, -9, 49, -15, 99, -60, -8, 54, -81, 93, 124, -18, -100, -11, 92, -104, -85, 113, -38, -75, -77, -108, -104, 9, 26, 107, -9, -79, 37, 44, -60, 38, 80, -118, -74, -113, -110, -114, -81, -99, -65, 97, 24, 127, -117, -46, 26, 38, 29, 95, 122, 125, -22, -4, 13, -61, -104, 54, -38, 26, 98, 26, 104, 24, 70, 73, 20, 52, -84, -13, -125, -59, -120, -15, -89, 125, 38, -110, 1, -18, 79, 102, 85, -1, 119, -113, -94, -8, -103, -120, -1, 7, -91, -26, -93, -10, -121, -98, -19, 7, -90, 70, -37, 71, -103, 15, 52, 12, -93, 36, -91, 53, 12, 3, 72, -58, -41, -50, -65, -72, -89, -14, 19, -86, -51, -41, 81, -8, -7, -101, -115, 17, 98, 19, 40, -28, 31, 76, -66, 76, -81, -53, 17, 44, -25, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};
//        byte2png(b1,"C:\\Users\\dell\\Desktop\\test_Image\\more2.png");
        byte[] b2 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\share_old.png");
        byte[] b3 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\share_new.png");
//        System.out.println(Model.getSimilarity("Share", b2));
//        System.out.println(Model.getSimilarity("message","email"));
        System.out.println(Model.getSimilarity(b3,b2));
//        System.out.println(Model.getSimilarity(b1,b3));

//        System.out.println(Model.getSimilarity("Enter a new address","Search for an address"));
        // 测试Object转ArrayList
//        List<String> list = new ArrayList<>();
//        list.add("ddddd");
//        list.add("true");
//        test(list);
        // 测试排序
//        Map<String,Double> unsortedMap = new HashMap<>();
//        unsortedMap.put("xpath1",2.3);
//        unsortedMap.put("xpath2",1.3);
//        unsortedMap.put("xpath3",5.3);
//        unsortedMap.put("xpath4",0.3);
//        unsortedMap.put("xpath5",4.3);
//        Map<String,Double> sortedMap = new LinkedHashMap<>();
//        unsortedMap.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
//                .forEachOrdered(entry->sortedMap.put(entry.getKey(),entry.getValue()));
//        for (Map.Entry<String,Double> map: sortedMap.entrySet()){
//            System.out.println(map.getKey() + ", " + map.getValue());
//        }

        // 测试获取界面中所有文本,界面相似度
//        String pageXmlFile_1 = "output/testResult/app-source-trans2.xml";
//        String pageXmlFile_2 = "output/testResult/app-source-trans1.xml";
//        System.out.println(UtilsRepair.getPageSimilarity3(true,pageXmlFile_1,pageXmlFile_2));
//        UtilsXmlLoader xmlLoader1 = new UtilsXmlLoader();
//        xmlLoader1.parseXml(pageXmlFile_1);
//        UtilsXmlLoader xmlLoader2 = new UtilsXmlLoader();
//        xmlLoader2.parseXml(pageXmlFile_2);
//        // 解析出所有结点
//        List<XmlTreeNode> allNodes_1 = xmlLoader1.getAllNodes();
//        List<XmlTreeNode> allNodes_2 = xmlLoader2.getAllNodes();
//        // 获取所有非空文本
//        List<String> list_1 = new ArrayList<>();
//        for(XmlTreeNode node:allNodes_1){
//            String text = ((UiNode)node).getAttribute("text");
//            if(!StringUtils.isBlank(text)){
//                System.out.println(text);
//                list_1.add(text);
//            }
//        }
//        System.out.println();
//        List<String> list_2 = new ArrayList<>();
//        for(XmlTreeNode node:allNodes_2){
//            String text = ((UiNode)node).getAttribute("text");
//            if(!StringUtils.isBlank(text)){
//                System.out.println(text);
//                list_2.add(text);
//            }
//        }

        // 测试纯色图片
//        byte[] b1 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\favorite.png");
////        System.out.println(Model.isPure(b1));
//        byte[] b2 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\favorite2.png");
//        System.out.println(Model.getSimilarity("Profile", b2));
//        System.out.println(Model.getSimilarity("Sign in", b1));
//        System.out.println(Model.getSimilarity("Profile", "Sign in"));
//        System.out.println(Model.getSimilarity("Favorites", "Following"));


        // 测试断言语句解析结果
//        String s = "Assert.assertEquals(\"Example Domain\", driver.findElementByXPath(\"//android.widget.TextView[@text=\\\"My News\\\"]\").getText());";
//        EnhancedMobileElement eme = new EnhancedMobileElement(1);
//        if(s.contains("getText()")) {
//            String ExpectValue = StringUtils.substringBetween(s,"\"","\"");
//            eme.setAction(AppiumAction.getText);
//            eme.setValue(ExpectValue);
//            String temp = s;
//            int indexLeft = temp.indexOf(",");
//            int indexRight = temp.lastIndexOf(")");
//            // 截取 driver.findElementByXXX("xxx") 部分
//            temp = temp.substring(indexLeft+1,indexRight).trim();
//            System.out.println(temp);
//            eme.setLocator(UtilsParser.getAppiumLocator(temp));
//        }
//        System.out.println(eme);


//         byte[] old_icon = png2byte("C:\\Users\\think\\Desktop\\test_Image\\view_all.png");// 旧版本图
//         byte[] new_icon = png2byte("C:\\Users\\think\\Desktop\\test_Image\\view_all2.png");// 新版本图
//         System.out.println(Model.getSimilarity(old_icon, new_icon));

//        byte[] b = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\account.png");
//        ViewTreeInfo v = UtilsFileGetter.getViewTreeInfoFromJsonFile("ViewMyFavoritesTest",40,"viewTreeInfo",Settings.extractInfoPath + Settings.sep +"UberEats");
//        // byte[] 转 png图像
//        byte[] b1 = v.getImage();
//        System.out.println(Model.getSimilarity(b1, b));
//        byte[] new_cha_manual = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\image2_new.png");// 旧版本裁剪处理后的图
//        System.out.println("手动剪裁结果："+Model.getSimilarity(old_cha, new_cha));
//        System.out.println("自动剪裁结果："+Model.getSimilarity(new_cha_manual, new_cha));
//        byte[] new_search = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\new_search.png");
//        byte[] new_search2 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\new_search2.png");
//        System.out.println(Model.getSimilarity("search", new_search));
//        System.out.println(Model.getSimilarity("search", new_search2));



//        AppiumLocator locator = UtilsParser.getAppiumLocator("driver.findElementByXPath(\"//android.widget.FrameLayout[@content-desc=\\\"Button: Add China to My News\\\"]/android.widget.LinearLayout/android.widget.ImageView\").click();");
//        System.out.println(locator.getStrategy());
//        System.out.println(locator.getValue());
        // printXpath("//hierarchy/android.widget.FrameLayout[1]/android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/android.widget.RelativeLayout[1]/android.widget.RelativeLayout[2]/android.widget.LinearLayout[3]/android.widget.RelativeLayout[2]/android.widget.TextView[1]");

//        // 读取上下文文件，转换为Context对象
//        Context c = (Context) FileUtils.readObject(Settings.extractInfoPath + Settings.sep + "UniversalTV" + Settings.sep + "RateUsTest" + Settings.sep + "33-contextInfo");
//        // 打印上下文元素的文本，输出图像
//        int index = 1;
//        for (ViewTreeInfo vt:c.getContext()){
//            System.out.println("文本" + index + ": " + vt.getText());
//            byte2png(vt.getImage(),"C:\\Users\\dell\\Desktop\\test_Image\\context0_" + "image" + index + ".png");
//            index++;
//        }

        // trace 相关测试
//        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//        desiredCapabilities.setCapability("platformName", "Android");
//        desiredCapabilities.setCapability("platformVersion", "9");
//        desiredCapabilities.setCapability("deviceName", "device");
//        desiredCapabilities.setCapability("automationName", "UiAutomator2");
//        desiredCapabilities.setCapability("appPackage", "com.mobilefootie.wc2010");
//        desiredCapabilities.setCapability("appActivity", "com.mobilefootie.fotmob.gui.v2.MainActivity");
//        desiredCapabilities.setCapability("noReset", true);
//        desiredCapabilities.setCapability("dontStopAppOnReset", true);
//        desiredCapabilities.setCapability("udid", "192.168.58.108:5555");
//        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);

        // 测试自动滑动
//        String hierarchyLayoutXmlFile = "output/testResult/11.xml";
//        List<Integer> list = UtilsRepair.getSwipedElement(hierarchyLayoutXmlFile);
//        System.out.println(list.get(0));
//        System.out.println(list.get(1));
//        System.out.println(list.get(2));
//        System.out.println(list.get(3));
//        new TouchAction(driver).longPress(PointOption.point(list.get(0), list.get(1))).moveTo(PointOption.point(list.get(2), list.get(3))).release().perform();
//        Thread.sleep(5000);

        // 测试提取元素图像并保存
//        File f = driver.findElementById("com.reddit.frontpage:id/action_share").getScreenshotAs(OutputType.FILE);
//        org.apache.commons.io.FileUtils.copyFile(f, new File("C:\\Users\\dell\\Desktop\\test_Image\\share_new.png"));
//        File f2 = driver.findElementByXPath("(//android.widget.ImageView[@content-desc=\"Remove from favorites\"])[4]").getScreenshotAs(OutputType.FILE);
//        org.apache.commons.io.FileUtils.copyFile(f2, new File("C:\\Users\\dell\\Desktop\\test_Image\\delete_new4.png"));
//        File f2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.ImageView[1]").getScreenshotAs(OutputType.FILE);
//        org.apache.commons.io.FileUtils.copyFile(f2, new File("C:\\Users\\dell\\Desktop\\test_Image\\delete_old2.png"));

//        File f2 = driver.findElementByAccessibilityId("More options").getScreenshotAs(OutputType.FILE);
//        org.apache.commons.io.FileUtils.copyFile(f2, new File("C:\\Users\\dell\\Desktop\\test_Image\\2.png"));
//        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[2]/android.widget.TextView").click();
//        Thread.sleep(3000);
//        File f3 = driver.findElementByAccessibilityId("Edit My News").getScreenshotAs(OutputType.FILE);
//        org.apache.commons.io.FileUtils.copyFile(f3, new File("C:\\Users\\dell\\Desktop\\test_Image\\3.png"));

//
//        byte[] b2 = driver.findElementByAccessibilityId("Search").getScreenshotAs(OutputType.BYTES);
//        byte2png(b2,"C:\\Users\\dell\\Desktop\\test_Image\\new_search.png");

//        byte[] b1 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image1_origin.png");
//        System.out.println(Arrays.toString(b1));

        //        byte[] b2 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\image2_new.png");
//        byte[] b3 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\image3_new.png");
//        byte[] b4 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\me1.png");
//        byte[] b5 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\new_cha.png");
//        System.out.println(Model.getSimilarity(b1,b4));
//        System.out.println(Model.getSimilarity(b2,b5));
//        System.out.println(Model.getSimilarity("search", b3));

//
//        byte[] b4 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image6_origin.png");
//        byte[] b5 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image6_new.png");
//        System.out.println("Menu");
//        System.out.println("文本和图像相似度：" + Model.getSimilarity("Menu",b4));
//        System.out.println("***裁剪后***");
//        System.out.println("文本和图像相似度：" + Model.getSimilarity("Menu",b5));
//        System.out.println("更改边框大小（少裁剪一点）文本和图像相似度：" + Model.getSimilarity("Menu",png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image6_new2.png")));
//
//        byte[] b6 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image5_origin.png");
//        byte[] b7 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image5_new.png");
//        System.out.println("Edit");
//        System.out.println("文本和图像相似度：" + Model.getSimilarity("Edit",b6));
//        System.out.println("***裁剪后***");
//        System.out.println("文本和图像相似度：" + Model.getSimilarity("Edit",b7));
//
//        byte[] b8 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image4_origin.png");
//        byte[] b9 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image4_new.png");
//        System.out.println("Add");
//        System.out.println("文本和图像相似度：" + Model.getSimilarity("Add",b8));
//        System.out.println("***裁剪后***");
//        System.out.println("文本和图像相似度：" + Model.getSimilarity("Add",b9));
//        System.out.println("更改分辨率文本和图像相似度：" + Model.getSimilarity("Add",png2byte("C:\\Users\\dell\\Desktop\\test_Image\\icon\\image4_new2.png")));



//        //读取Json文件，转换为ViewTreeInfo对象
//        ViewTreeInfo v = UtilsFileGetter.getViewTreeInfoFromJsonFile("BusinessDeleteTest",41,"viewTreeInfo",Settings.extractInfoPath + Settings.sep +"BBCNews");
//        // byte[] 转 png图像
//        byte[] b1 = v.getImage();
//        byte2png(b1,"C:\\Users\\dell\\Desktop\\test_Image\\old_cha.png");

//        byte[] b1 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\old_cha.png");
//        byte[] b2 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\new_cha.png");
//        byte[] b3 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\old_cha2.png");
//        System.out.println("原图像相似度：" + Model.getSimilarity(b1,b2));
//        System.out.println("新图像相似度：" + Model.getSimilarity(b3,b2));

//        driver.quit();
//
//        MobileElement me1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.LinearLayout[3]/android.widget.TextView[1]");
//        MobileElement me2 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.RelativeLayout[2]/android.widget.LinearLayout[3]/android.widget.TextView[2]");
//        byte[] b1 = me1.getScreenshotAs(OutputType.BYTES);
//        byte[] b2 = me2.getScreenshotAs(OutputType.BYTES);
//        byte2png(b1,"C:\\Users\\dell\\Desktop\\test_Image\\me1.png");
//        byte2png(b2,"C:\\Users\\dell\\Desktop\\test_Image\\me2.png");

        // 读取文件夹中的图片并计算相似度
//        byte[] b1 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\me1.png");
//        byte[] b0 = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\image00.png");
//        byte[] b = png2byte("C:\\Users\\dell\\Desktop\\test_Image\\image0.png");
//        System.out.println("截取后："+Model.getSimilarity(b1, b0));
//        System.out.println("原："+Model.getSimilarity(b1, b));

        // //android.widget.Button[@content-desc="Calendar"]
        // //android.widget.FrameLayout[@content-desc="Matches"]/android.widget.FrameLayout/android.view.View
        // //android.widget.FrameLayout[@content-desc="News"]/android.view.ViewGroup/android.widget.TextView
//        MobileElement me = (MobileElement) driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Matches\"]/android.widget.FrameLayout/android.view.View");

//        // 1 测试元素相等
//        System.out.println(me.equals(me2));
//        // 2 测试图片截取
//        File f = me.getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(f, new File("C:\\Users\\dell\\Desktop\\test_Image\\element.png"));
//        byte[] pic = me.getScreenshotAs(OutputType.BYTES);
//        // 3 测试相似度计算
//        System.out.println(Model.getSimilarity("time",pic));
        // 4 测试上下文提取和读写
//        String hierarchyLayoutXmlFile = "output/testResult/test2.xml";
//        UtilsHierarchyXml.takeXmlSnapshot(driver,System.getProperty("user.dir") + Settings.sep + hierarchyLayoutXmlFile);
//        MobileElement me = (MobileElement)driver.findElementById("com.dywx.larkplayer:id/tv_songs");
//        RepairRunner.isContextConsidered = true;
//        RepairRunner.Height = driver.manage().window().getSize().getHeight();
//        RepairRunner.Width = driver.manage().window().getSize().getWidth();
//        Context context = new Context(driver, me, hierarchyLayoutXmlFile);
//        for (ViewTreeInfo v: context.getContext()){
//            System.out.println(v.getXpath());
//        }
//        main.java.util.FileUtils.writeObject(context,"output/testResult/testContext");
//        Context context2 = (Context) main.java.util.FileUtils.readObject("output/AppInfo3/GoogleTranslate/VoiceConversationTest/43-contextInfo");
//        for (ViewTreeInfo v: context2.getContext()){
//            System.out.println(Arrays.toString(v.getImage()));
//        }
//        driver.quit();
    }
}
