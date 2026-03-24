package main.java.util;

import com.google.gson.Gson;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import main.java.config.Settings;
import main.java.config.Threshold;
import main.java.dataType.*;
import main.java.model.Model;
import main.java.runner.RepairRunner;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.ScreenshotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UtilsRepair {
    private static Logger log = LoggerFactory.getLogger(UtilsRepair.class);

    // 深拷贝
    public static Statement deepClone(Statement object) {
        Gson gson = new Gson();
        if (object instanceof EnhancedMobileElement) {
            return gson.fromJson(gson.toJson(object), EnhancedMobileElement.class);
        } else if (object instanceof EnhancedTouchAction) {
            return gson.fromJson(gson.toJson(object), EnhancedTouchAction.class);
        }else if (object instanceof EnhancedNavigate) {
            return gson.fromJson(gson.toJson(object), EnhancedNavigate.class);
        }
        log.error("Object is not Statement type...");
        return null;
    }

    // Add: 根据 EnhancedMobilElement 的 locator 获取 MobileElement 对象
    public static MobileElement getElementFromStatement(AndroidDriver driver, AppiumLocator locator){
        String strategy = locator.getStrategy();
        String value = locator.getValue();
        List<MobileElement> elements = new ArrayList<>();
        List<MobileElement> filteredElements = new ArrayList<>();

        if ("resourceId".equalsIgnoreCase(strategy)) {
            elements = (List<MobileElement>)driver.findElementsById(value);
        } else if ("contentDesc".equalsIgnoreCase(strategy)) {
            elements = (List<MobileElement>)driver.findElementsByAccessibilityId(value);
        } else if ("xpath".equalsIgnoreCase(strategy)) {
            elements = (List<MobileElement>)driver.findElementsByXPath(value);
        }
        for (MobileElement me:elements){
            if(elementFilter(me)){
                filteredElements.add(me);
            }
        }
        return filteredElements.get(0);
    }

    // Add: 过滤无效元素：过滤掉不显示和未启用的元素(暂未考虑换行和分割线) + 过滤掉尺寸太小（小于20像素）的元素 + 过滤尺寸太大的元素
    public static boolean elementFilter(MobileElement e){
        boolean temp;
        try {
            int H = RepairRunner.Height;
            int W = RepairRunner.Width;
            int h = e.getSize().getHeight();
            int w = e.getSize().getWidth();
            // && (double)(h/w)>=0.1 && (double)(w/h)>=0.1 && h<=0.75*H && w<=0.75*W
            temp = e.isDisplayed() && e.isEnabled() && h>=5 && w>=10 && h*w <= 0.75*W*H;
        }catch (StaleElementReferenceException ex){
            // ex.printStackTrace();
            log.info("目标元素已不在DOM中, elementFilter 返回 false");
            return false;
        }catch (NoSuchElementException ex){
            log.info("目标元素已过期, elementSizeFilter 返回 false");
            return false;
        }
        return temp;
    }

    // Add: 过滤尺寸不匹配元素：过滤掉与原元素尺寸差异过大的图标元素（长或宽超过原元素1.5倍）
    public static boolean elementSizeFilter(MobileElement e, EnhancedMobileElement ori_ele){
        int ori_height = ori_ele.getDimension().getHeight();
        int ori_width = ori_ele.getDimension().getWidth();
        int height = 0;
        int width = 0;
        try {
            height = e.getSize().getHeight();
            width = e.getSize().getWidth();
        }catch (StaleElementReferenceException ex){
            // ex.printStackTrace();
            log.info("目标元素已不在DOM中, elementSizeFilter 返回 false");
            return false;
        }catch (NoSuchElementException ex){
            log.info("目标元素已过期, elementSizeFilter 返回 false");
            return false;
        }
        // 仅过滤掉尺寸差异过大 且 是图标（无文本）的元素
        if(StringUtils.isNotBlank(e.getText()) || StringUtils.isNotBlank(ori_ele.getText()) || height <= 4 * ori_height &&  width <= 4 * ori_width && height >= 0.25 * ori_height && width >= 0.25 * ori_width){
            return true;
        }
        return false;
    }

    // Add：尝试使用原定位器定位元素(先过滤掉不显示和未启用的元素) (使用较高的相似度阈值，避免误判)
    // 1 只有一个元素且满足阈值：计算元素相似度，满足条件返回此元素
    // 2 否则，考虑上下文：计算上下文相似度，返回相似度最高且满足阈值的元素
    public static MobileElement retrieveElementFromAppiumLocator(AndroidDriver driver, EnhancedMobileElement originEle, String curLayoutXmlFile,boolean isContextConsidered) {
        AppiumLocator locator = originEle.getLocator();
        String strategy = locator.getStrategy();
        String value = locator.getValue();
        List<MobileElement> elements = new ArrayList<>();
        List<MobileElement> filteredElements = new ArrayList<>();

        if ("resourceId".equalsIgnoreCase(strategy)) {
            elements = (List<MobileElement>)driver.findElementsById(value);
        } else if ("contentDesc".equalsIgnoreCase(strategy)) {
            elements = (List<MobileElement>)driver.findElementsByAccessibilityId(value);
        } else if ("xpath".equalsIgnoreCase(strategy)) {
            elements = (List<MobileElement>)driver.findElementsByXPath(value);
        }

        // 1 定位到唯一元素且满足阈值，未失效
        if(elements.size() == 1 && getElementSimilarityOrigin(elements.get(0),originEle) >= Threshold.ELE_SIM.getValue()){
//            System.out.println("相似度为：" + getElementSimilarity(elements.get(0), originEle));
            log.info("*** Element Similarity: " + getElementSimilarityOrigin(elements.get(0), originEle) + " ***");
            log.info("*** No Breakage ***");
            return elements.get(0);
        }
        // 2 失效，进行元素修复
        log.info("*** Breakage, start repair *** ");
        if (elements.size() == 0){
            log.info("*** Breakage Type: Element not found (ENF) ***");
            return null;
        }
        // 过滤掉无效元素
        for(MobileElement ele:elements){
            if(elementFilter(ele)){
                filteredElements.add(ele);
            }
        }
        if(filteredElements.size()==0){
            return null;
        }
        System.out.println("原定位器获取到的候选元素: "+filteredElements.size()+" 个");

        // 只有一个元素且满足阈值，返回目标元素
        double s = getElementSimilarityOrigin(filteredElements.get(0),originEle);
        if(filteredElements.size() == 1 && s >= Threshold.ELE_SIM.getValue()){
            return filteredElements.get(0);
        } else{ // 否则
            // Add: 如果只有一个元素且此元素的个体相似度不满足阈值，直接返回空，进行整个页面搜索
            if(filteredElements.size() == 1){
                log.info("原定位器定位到的元素个体相似度: " + s);
                log.info("*** Similarity lower than threshold ***");
                log.info("*** Breakage Type: Element located incorrectly (ELI) ***");
                return null;
            }
            // 存在多个候选元素
            double maxSim = 0;
            MobileElement maxEle = null;
            // 获取初始元素上下文
            Context ori_context = originEle.getContext();
            for(MobileElement me : filteredElements){
                // 过滤尺寸不匹配的图标元素
                if(elementSizeFilter(me,originEle)){
                    // 获取me的上下文元素
                    Context new_context = new Context(driver, me, curLayoutXmlFile);
                    // isContextConsidered为true时，计算考虑上下文相似度的元素相似度
                    double sim = getContextSimilarity(isContextConsidered,me,originEle,new_context,ori_context);
                    System.out.println("综合相似度："+sim);
                    if(sim > maxSim && sim >= Threshold.ELE_SIM.getValue()){
                        maxSim = sim;
                        maxEle = me;
                    }
                }
            }
            System.out.println("原定位器下大于阈值的最大相似度：" + maxSim);
            // 使用原定位器能定位到元素，但未从中找到满足阈值的元素，属于 定位错 的情况
            if(maxSim==0){
                log.info("*** Similarity lower than threshold ***");
                log.info("*** Breakage Type: Element located incorrectly (ELI) ***");
            }
            return maxEle;
        }
    }

    // Add: 原定位器找不到满足条件的元素时，在当前界面寻找最相似且满足阈值的元素
    public static MobileElement retrieveElementFromCurrentPage(AndroidDriver driver, EnhancedMobileElement originEle,String curLayoutXmlFile, boolean isContextConsidered) throws StaleElementReferenceException, IOException, ClassNotFoundException, InterruptedException {
        // 1 根据元素属性（常用classname、原元素的classname、resourceId、contentDescription、text、xpath）获取候选元素
        // 候选元素
        Set<MobileElement> candidateElements = new HashSet<>();
        List<MobileElement> tempElements = new ArrayList<>();
        // ---ClassName---
        // 常见classname（一般属于这些classname的元素都是叶子结点元素） 最后加上 "android.view.View",
        Set<String> clazzSet = new HashSet<>(Arrays.asList( "android.view.View", "android.widget.Button", "android.widget.TextView", "android.widget.ImageView",
                "android.widget.RadioButton", "android.widget.CheckedTextView", "android.widget.ImageButton", "android.widget.CompoundButton"));
        // 原元素classname
        clazzSet.add(originEle.getClassName());
        for (String classname:clazzSet){
            tempElements = driver.findElementsByClassName(classname);
            if(!tempElements.isEmpty()){
                for(MobileElement me:tempElements){
                    if(elementFilter(me)){
                        candidateElements.add(me);
                    }
                }
            }
        }
        // ---ResourceID---
        String resourceId = originEle.getResourceId();
        if(StringUtils.isNotEmpty(resourceId)){
            tempElements = driver.findElementsById(resourceId);
            if(!tempElements.isEmpty()){
                for(MobileElement me:tempElements){
                    if(elementFilter(me)){
                        candidateElements.add(me);
                    }
                }
            }
        }
        // ---Content Description---
        String contentDesc = originEle.getContentDesc();
        if(StringUtils.isNotEmpty(contentDesc)){
            tempElements = driver.findElementsByAccessibilityId(contentDesc);
            if(!tempElements.isEmpty()){
                for(MobileElement me:tempElements){
                    if(elementFilter(me)){
                        candidateElements.add(me);
                    }
                }
            }
        }
        // ---Text---
        String text = originEle.getText();
        if(StringUtils.isNotEmpty(text)){
            tempElements = driver.findElementsByXPath("//*[@text='" + text + "']");
            if(!tempElements.isEmpty()){
                for(MobileElement me:tempElements){
                    if(elementFilter(me)){
                        candidateElements.add(me);
                    }
                }
            }
        }
        // ---xpath---
        String[] xpaths = originEle.getXpath().split(";");
        for(String xpath : xpaths){
            tempElements = driver.findElementsByXPath(xpath);
            if(!tempElements.isEmpty()){
                for(MobileElement me:tempElements){
                    if(elementFilter(me)){
                        candidateElements.add(me);
                    }
                }
            }
        }

        // 2 前面方法获取不到，则直接获取当前界面中所有元素
        if(candidateElements.isEmpty()){
            // /*获取子元素，//*获取子孙元素
            tempElements = driver.findElementsByXPath("//hierarchy//*");
            if(!tempElements.isEmpty()){
                for (MobileElement me:tempElements){
                    // 过滤
                    if(elementFilter(me)){
                        candidateElements.add(me);
                    }
                }
            }else{
                log.info("当前界面获取不到任何元素");
                return null;
            }
        }
        log.info("*** Size of candidate elements in current screen: " + candidateElements.size() + " ***");

        // 3 候选元素中选择综合相似度最高且满足阈值的元素
        if(!candidateElements.isEmpty()){
            // (候选元素XPath，相似度)
            Map<String,Double> candidateMap = new HashMap<>();
            // 最高相似度及对应元素
            double maxSim = 0;
            MobileElement maxEle = null;
            // 获取初始元素上下文
            Context ori_context = originEle.getContext();
            for(MobileElement me:candidateElements){
//                long start = System.currentTimeMillis();
                if(elementSizeFilter(me,originEle)){
                    // 1) 提取me的上下文
                    Context new_context = new Context(driver, me, curLayoutXmlFile);
                    // 2) 计算综合相似度
                    double sim = getContextSimilarity(isContextConsidered, me, originEle,new_context,ori_context);
                    // 3) 生成xpath，加入map
                    String xpath = UtilsXpath.getAbsoluteXpath(me,curLayoutXmlFile);
                    candidateMap.put(xpath,sim);
                    System.out.println("综合相似度："+sim);
                    if(sim > maxSim && sim >= Threshold.ELE_SIM.getValue()){
                        maxSim = sim;
                        maxEle = me;
                    }
                }
//                log.info("检查此元素花费 {} s。", String.format("%.3f", (System.currentTimeMillis()-start) / 1000.0f));
            }
//            if(false && maxEle == null && isContextConsidered){ // 关闭上下文增强
            if(maxEle == null && isContextConsidered){
                log.info("***** 当前界面获取不到满足阈值的元素，是否进行上下文增强搜索?(0-False/1-True) *****");
                Scanner sc= new Scanner(System.in);

                if(sc.nextInt()==1){ // 仅限click操作：&& Objects.equals(originEle.getAction().getValue(), "click")
                    // 4 对于点击操作的语句，尝试考虑目标界面作为上下文的增强，计算目标界面增强的综合相似度
                    // 1) candidateMap 根据相似度排序
                    Map<String,Double> sortedCandidateMap = new LinkedHashMap<>();
                    candidateMap.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                            .forEachOrdered(entry->sortedCandidateMap.put(entry.getKey(),entry.getValue()));
                    // 2) 获取原版本目标界面布局xml文件
                    int line = originEle.getLine() + 1;
                    String originTargetLayoutXmlFile = Settings.extractInfoPath + Settings.sep + RepairRunner.appEnum.getAppName() +
                            Settings.sep + RepairRunner.testcaseName + Settings.sep + line + "-hierarchy" + Settings.XML_EXT;
                    // 界面相似度法2使用
//                    String originTargetLayoutXmlFile = "output/targetPageInfo/delete-5-oldFile.xml";
                    // 3) 目标界面上下文增强搜索
                    long start = System.currentTimeMillis();
                    String targetXpath = enhancedSearch(driver, originTargetLayoutXmlFile, sortedCandidateMap);
                    if(targetXpath!=null)
                        maxEle = (MobileElement) driver.findElementByXPath(targetXpath);
                    log.info("目标界面上下文增强搜索花费 {} s。", String.format("%.3f", (System.currentTimeMillis()-start-1500) / 1000.0f));
                    if (maxEle == null){
                        log.info("增强搜索仍未搜索到目标元素");
                        return null;
                    }
                    return maxEle;
                }else {
                    log.info("非click语句 或 当前界面未找到目标元素");
                    return null;
                }
            } else {
                System.out.println("当前界面满足阈值的最大相似度：" + maxSim);
                return maxEle;
            }
        }else{
            log.info("当前界面获取不到有效元素");
            return null;
        }
    }

    // Add: 结合 目标界面增强的上下文语义 进一步搜索（只针对click操作，其他操作不涉及界面状态转换，目标界面无意义）
    // originTargetLayoutXmlFile: 原版本目标界面
    // （忽略）initLayoutXmlFile: 新版本当前界面（未点击探索前）
    public static String enhancedSearch(AndroidDriver driver, String originTargetLayoutXmlFile, Map<String,Double> candidateElements) throws InterruptedException, IOException, ClassNotFoundException {
        // 保存初始界面xml布局
        String preStateLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
        UtilsHierarchyXml.takeXmlSnapshot(driver, preStateLayoutXmlFile);
        // 1 获取候选元素（待定：top 1/5/10/全部 作为候选元素）
        int n = 5;
        double maxSim = 0;
        // Add: 记录并返回Xpath，若直接返回目标元素，之后使用时，元素容易过期失效
        String maxEleXpath = null;
        for(Map.Entry<String,Double> map:candidateElements.entrySet()){
            if(n<=0){
                break;
            }
            String xpath = map.getKey();
            Double sim = map.getValue();
            // 2 点击元素（无论是否是可点击元素都进行点击操作，因为无法根据属性或界面文本变化情况直接断定是否是可点击元素）
            MobileElement me = null;
            try {
                me = (MobileElement) driver.findElementByXPath(xpath);
            }catch (NoSuchElementException ne){
                log.info("此元素已不存在于初始界面，跳过");
                n--;
                continue;
            }
            System.out.println("点击: "+xpath);
            System.out.println("综合相似度: "+sim);
            me.click();
            Thread.sleep(3000);
            // 3 保存点击后的布局层次结构xml文件
            String curLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
            UtilsHierarchyXml.takeXmlSnapshot(driver, curLayoutXmlFile);
            // 4 计算增强后的综合相似度 = 综合相似度 + 增强相似度
            // 计算界面相似度，用于判断目标界面是否相似
            double pageSim = getPageSimilarity(true, curLayoutXmlFile, originTargetLayoutXmlFile);

            // 4.1 算法1：仅文本
            double targetSim = getEnhancedSimilarity(true, curLayoutXmlFile, originTargetLayoutXmlFile);
            System.out.println();

//            // 4.2 算法2：文本和图像
            // 分别获取当前界面信息和原目标界面信息，计算相似度
//            List<Object> pageInfo_1 = UtilsRepair.getPageInfo(driver);
//            List<Object> pageInfo_2 = (List<Object>)main.java.util.FileUtils.readObject("output/targetPageInfo/delete-5-oldInfo");
//            double targetSim = getEnhancedSimilarity2(true, pageInfo_1, pageInfo_2);
//            System.out.println();

            // 4.3 算法3：仅关键文本
//            double targetSim = getEnhancedSimilarity3(true, curLayoutXmlFile, originTargetLayoutXmlFile);
//            System.out.println();

//            System.out.println("目标界面相似度：" + pageSim + " / " + pageSim2+" / " + pageSim3);
            System.out.println("目标界面相似度：" + pageSim);
            System.out.println("跨界面上下文相似度：" + targetSim);
            double enhancedSim = sim;
            // 界面相似度大于阈值0.5时，才认为可能是相似界面，此时才将界面相似度作为上下文加入（防止增加无关元素的相似度分值，避免将已删除的元素误判为错误元素）
            if(pageSim>=Threshold.PAGE_SIM.getValue()){
                enhancedSim += Threshold.CONTEXT_WEIGHT.getValue() * targetSim;
            }
            System.out.println("增强后相似度："+enhancedSim);
            if(enhancedSim > maxSim && enhancedSim >= Threshold.ELE_SIM.getValue()){
                maxSim = enhancedSim;
                maxEleXpath = xpath;
            }
            // 5 恢复初始界面
//            backToPreState(driver,preStateLayoutXmlFile);
            log.info("***** 请恢复到初始界面，并输入任意数字继续。 *****");
            Scanner sc2 = new Scanner(System.in);
            int input = sc2.nextInt();
            n--;
        }
        return maxEleXpath;
    }

    // Add: 计算元素个体相似度 -- MobileElement && EnhancedMobileElement
    public static double getElementSimilarityOrigin(MobileElement candidateEle,EnhancedMobileElement originEle){
        String text1;
        byte[] image1;
        // MobileElement 可能已经失效（动态元素），返回相似度0
        try{
            text1 = candidateEle.getText();
            image1 = candidateEle.getScreenshotAs(OutputType.BYTES);
        }catch (StaleElementReferenceException ex){
            // e.printStackTrace();
            log.info("目标元素已不在DOM中，相似度返回0。");
            return 0;
        }catch (NoSuchElementException ex){
            log.info("目标元素已过期，相似度返回0。");
            return 0;
        }catch (ScreenshotException se){
            log.info("裁剪区域有误: The cropping area is outside of the original bitmap，相似度返回0。");
            return 0;
        }
        String text2 = originEle.getText();
        byte[] image2 = originEle.getImage();

        // 此处不能使用text!=null判断，元素无文本时返回的不是null，而是空字符串
        if (!StringUtils.isBlank(text1) && !StringUtils.isBlank(text2)) {
            System.out.println("text1:"+text1+" text2:"+text2);
            return Model.getSimilarity(text1, text2);
        } else if (!StringUtils.isBlank(text1) && image2 != null) {
            System.out.println("text1:"+text1+" img2:"+image2);
            return Model.getSimilarity(text1, image2);
        } else if (!StringUtils.isBlank(text2) && image1 != null) {
            System.out.println("text2:"+text2+" img1:"+image1);
            return Model.getSimilarity(text2, image1);
        } else if (image1 != null && image2 != null) {
            System.out.println("img1:"+image1+" img2:"+image2);
            return Model.getSimilarity(image1, image2);
        } else {
            return 0;
        }
    }
    public static double getElementSimilarityEnhanced(MobileElement candidateEle,EnhancedMobileElement originEle){
        String text1;
        byte[] image1;
        // MobileElement 可能已经失效（动态元素），返回相似度0
        try{
            text1 = candidateEle.getText();
            image1 = candidateEle.getScreenshotAs(OutputType.BYTES);
        }catch (StaleElementReferenceException ex){
        // e.printStackTrace();
            log.info("目标元素已不在DOM中，相似度返回0。");
            return 0;
        }catch (NoSuchElementException ex){
            log.info("目标元素已过期，相似度返回0。");
            return 0;
        }catch (ScreenshotException se){
            log.info("裁剪区域有误: The cropping area is outside of the original bitmap，相似度返回0。");
            return 0;
        }
        String text2 = originEle.getText();
        byte[] image2 = originEle.getImage();

        // -----------存储所有比较过的元素对的文本和图片-------------------
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String col1 = StringUtils.isBlank(text1) ? "null" : text1;
        String col2 = "null";
        if(image1 != null) {
            col2 = CSVAppender.NEW_IMG_SAVED_PATH_PREFIX + dateFormat.format(new Date()) + "_" + UUID.randomUUID();
            CSVAppender.byte2png(image1, col2);
        }
        String col3 = StringUtils.isBlank(text2) ? "null" : text2;
        String col4 = "null";
        if(image2 != null) {
            col4 = CSVAppender.OLD_IMG_SAVED_PATH_PREFIX + dateFormat.format(new Date()) + "_" + UUID.randomUUID();
            CSVAppender.byte2png(image2, col4);
        }
        // -----------------------------------------------------------

        // 此处不能使用text!=null判断，元素无文本时返回的不是null，而是空字符串
        double sim = 0;
        if (!StringUtils.isBlank(text1) && !StringUtils.isBlank(text2)) {
            sim = Model.getSimilarity(text1, text2);
        } else if (!StringUtils.isBlank(text1) && image2 != null) {
            sim = Model.getSimilarity(text1, image2);
        } else if (!StringUtils.isBlank(text2) && image1 != null) {
            sim = Model.getSimilarity(text2, image1);
        } else if (image1 != null && image2 != null) {
            sim = Model.getSimilarity(image1, image2);
        }

        // -----------------------------------------------------------
        String col5 = String.valueOf(sim);
        CSVAppender.appendToCSV(new String[]{col1, col2, col3, col4, col5});
        return sim;
    }
        // -------------------------

    // Add: 计算元素个体相似度 -- ViewTreeInfo && ViewTreeInfo
    public static double getElementSimilarity(ViewTreeInfo new_v,ViewTreeInfo ori_v){
        String text1 = new_v.getText();
        byte[] image1 = new_v.getImage();
        String text2 = ori_v.getText();
        byte[] image2 = ori_v.getImage();

        if (!StringUtils.isBlank(text1) && !StringUtils.isBlank(text2)) {
            return Model.getSimilarity(text1, text2);
        } else if (!StringUtils.isBlank(text1) && image2 != null) {
            return Model.getSimilarity(text1, image2);
        } else if (!StringUtils.isBlank(text2) && image1 != null) {
            return Model.getSimilarity(text2, image1);
        } else if (image1 != null && image2 != null) {
            return Model.getSimilarity(image1, image2);
        } else {
            return 0;
        }
    }

    // Add: 计算考虑上下文相似度的综合相似度，isContextConsidered为false时退化为元素个体相似度
    public static double getContextSimilarity(boolean isContextConsidered,MobileElement candidateEle,EnhancedMobileElement originEle,Context new_context,Context ori_context){
        double ele_sim = getElementSimilarityOrigin(candidateEle, originEle);
        System.out.println("个体相似度: "+ele_sim);
        // 是否考虑上下文
        if(isContextConsidered){
            if(ori_context.isEmpty() || new_context.isEmpty()){
                return ele_sim;
            }
            double[][] m = new double[ori_context.size()][new_context.size()];
            // 计算上下文元素间相似度矩阵
            for (int i = 0; i < ori_context.size(); ++i) {
                for (int j = 0; j < new_context.size(); ++j) {
                    ViewTreeInfo ori_v = ori_context.get(i);
                    ViewTreeInfo new_v = new_context.get(j);
                    m[i][j] = getElementSimilarity(new_v, ori_v);
                }
            }
            double sum = 0;
            double maxMatch;
            while ((maxMatch = getAndSetMax(m)) >= Threshold.ELE_SIM.getValue()) {
                sum += (maxMatch - Threshold.ELE_SIM.getValue());
            }
            return ele_sim + Threshold.CONTEXT_WEIGHT.getValue() * sum;
        }else{
            return ele_sim;
        }
    }

    // Add：计算界面相似度（原 跨界面相似度=界面相似度）（目标界面作为上下文的补充）：捕获界面文本，加入集合，计算相似度
    public static double getPageSimilarity(boolean isContextConsidered,String pageXmlFile_1,String pageXmlFile_2){
        if (isContextConsidered){
            UtilsXmlLoader xmlLoader1 = new UtilsXmlLoader();
            xmlLoader1.parseXml(pageXmlFile_1);
            UtilsXmlLoader xmlLoader2 = new UtilsXmlLoader();
            xmlLoader2.parseXml(pageXmlFile_2);
            // 解析出所有结点
            List<XmlTreeNode> allNodes_1 = xmlLoader1.getAllNodes();
            List<XmlTreeNode> allNodes_2 = xmlLoader2.getAllNodes();
            // 获取所有非空文本
            List<String> list_1 = new ArrayList<>();
            for(XmlTreeNode node:allNodes_1){
                String text = ((UiNode)node).getAttribute("text");
                if(!StringUtils.isBlank(text)){
//                    System.out.println(text);
                    list_1.add(text);
                }
            }
            // System.out.println();
            List<String> list_2 = new ArrayList<>();
            for(XmlTreeNode node:allNodes_2){
                String text = ((UiNode)node).getAttribute("text");
                if(!StringUtils.isBlank(text)){
//                    System.out.println(text);
                    list_2.add(text);
                }
            }
            if (list_1.isEmpty() && list_2.isEmpty()){
                log.info("两界面均无显式文本，界面相似度为1");
                return 1;
            }
            if (list_1.isEmpty() || list_2.isEmpty()){
                log.info("一界面无显式文本，界面相似度为0");
                return 0;
            }
            // 对文本集合统一编码
            Model.encodeTexts(list_1);
            Model.encodeTexts(list_2);
            double[][] m = new double[list_1.size()][list_2.size()];
            for (int i = 0; i < list_1.size(); ++i) {
                for (int j = 0; j < list_2.size(); ++j) {
                    m[i][j] = Model.getSimilarity(list_1.get(i), list_2.get(j));
                }
            }
            // 语义匹配的文本数量
            int matchNum = 0;
            while (getAndSetMax(m) >= Threshold.ELE_SIM.getValue()) {
                ++matchNum;
            }
//            System.out.println("matchNum:"+matchNum);
//            System.out.println("list_1.size(): " + list_1.size());
//            System.out.println("list_2.size(): " + list_2.size());
            return matchNum / Math.sqrt(list_1.size() * list_2.size());
        } else {
            return 0;
        }
    }

    // Add: 计算跨界面相似度1（新）：匹配的元素的相似度相加/两集合的几何平均值 （原与界面相似度公式相同）
    public static double getEnhancedSimilarity(boolean isContextConsidered,String pageXmlFile_1,String pageXmlFile_2){
        if (isContextConsidered){
            UtilsXmlLoader xmlLoader1 = new UtilsXmlLoader();
            xmlLoader1.parseXml(pageXmlFile_1);
            UtilsXmlLoader xmlLoader2 = new UtilsXmlLoader();
            xmlLoader2.parseXml(pageXmlFile_2);
            // 解析出所有结点
            List<XmlTreeNode> allNodes_1 = xmlLoader1.getAllNodes();
            List<XmlTreeNode> allNodes_2 = xmlLoader2.getAllNodes();
            // 获取所有非空文本
            List<String> list_1 = new ArrayList<>();
            for(XmlTreeNode node:allNodes_1){
                String text = ((UiNode)node).getAttribute("text");
                if(!StringUtils.isBlank(text)){
                    list_1.add(text);
                }
            }
            List<String> list_2 = new ArrayList<>();
            for(XmlTreeNode node:allNodes_2){
                String text = ((UiNode)node).getAttribute("text");
                if(!StringUtils.isBlank(text)){
                    list_2.add(text);
                }
            }
            if (list_1.isEmpty() && list_2.isEmpty()){
                log.info("两界面均无显式文本，界面相似度为1");
                return 1;
            }
            if (list_1.isEmpty() || list_2.isEmpty()){
                log.info("一界面无显式文本，界面相似度为0");
                return 0;
            }
            // 对文本集合统一编码
            Model.encodeTexts(list_1);
            Model.encodeTexts(list_2);
            double[][] m = new double[list_1.size()][list_2.size()];
            for (int i = 0; i < list_1.size(); ++i) {
                for (int j = 0; j < list_2.size(); ++j) {
                    m[i][j] = Model.getSimilarity(list_1.get(i), list_2.get(j));
                }
            }
            // 语义匹配的文本元素对的相似度求和
            int matchNum = 0;
            double matchSim = 0;
            double sim = 0;
            while ((sim=getAndSetMax(m)) >= Threshold.ELE_SIM.getValue()) {
                matchSim += sim;
//                System.out.println(sim);
                ++matchNum;
            }
            System.out.println("matchNum:" + matchNum);
            System.out.println("matchSim:" + matchSim);
            System.out.println("new list_1.size(): " + list_1.size());
            System.out.println("old list_2.size(): " + list_2.size());

            return matchSim / Math.sqrt(list_1.size() * list_2.size());
        } else {
            return 0;
        }
    }

    // Add：计算跨界面相似度2：捕获界面所有叶子结点元素（过滤无用元素），加入集合，计算相似度
    public static double getEnhancedSimilarity2(boolean isContextConsidered, List<Object> pageInfo_1, List<Object> pageInfo_2){
        if (isContextConsidered){
            if (pageInfo_1.isEmpty() && pageInfo_2.isEmpty()){
                log.info("两界面均无有效元素，界面相似度为1");
                return 1;
            }
            if (pageInfo_1.isEmpty() || pageInfo_2.isEmpty()){
                log.info("一界面无有效元素，界面相似度为0");
                return 0;
            }
            double[][] m = new double[pageInfo_1.size()][pageInfo_2.size()];
            for (int i = 0; i < pageInfo_1.size(); ++i) {
                for (int j = 0; j < pageInfo_2.size(); ++j) {
                    if (pageInfo_1.get(i) instanceof String && pageInfo_2.get(j) instanceof String)
                        m[i][j] = Model.getSimilarity((String) pageInfo_1.get(i), (String) pageInfo_2.get(j));
                    else if (pageInfo_1.get(i) instanceof byte[] && pageInfo_2.get(j) instanceof byte[])
                        m[i][j] = Model.getSimilarity((byte[]) pageInfo_1.get(i), (byte[]) pageInfo_2.get(j));
                    else if (pageInfo_1.get(i) instanceof byte[] && pageInfo_2.get(j) instanceof String)
                        m[i][j] = Model.getSimilarity((String) pageInfo_2.get(j), (byte[]) pageInfo_1.get(i));
                    else
                        m[i][j] = Model.getSimilarity((String) pageInfo_1.get(i), (byte[]) pageInfo_2.get(j));
                }
            }
            // 语义匹配的文本数量
            int matchNum = 0;
//            while (getAndSetMax(m) >= Threshold.ELE_SIM.getValue()) {
//                ++matchNum;
//            }
//            System.out.println("matchNum:"+matchNum);
//            System.out.println("pageInfo_1.size(): " + pageInfo_1.size());
//            System.out.println("pageInfo_2.size(): " + pageInfo_2.size());
//            return matchNum / Math.sqrt(pageInfo_1.size() * pageInfo_2.size());
            // 语义匹配的元素对的相似度求和
            double matchSim = 0;
            double sim = 0;
            while ((sim=getAndSetMax(m)) >= Threshold.ELE_SIM.getValue()) {
                matchSim += sim;
//                System.out.println(sim);
                ++matchNum;
            }
            System.out.println("matchNum:" + matchNum);
            System.out.println("matchSim:" + matchSim);
            System.out.println("list_1.size(): " + pageInfo_1.size());
            System.out.println("list_2.size(): " + pageInfo_2.size());

            return matchSim / Math.sqrt(pageInfo_1.size() * pageInfo_2.size());
        } else {
            return 0;
        }
    }

    // Add: 计算跨界面相似度3：只选择界面中最大文本、最顶端文本、selected为true的文本，加入集合，计算相似度
    public static double getEnhancedSimilarity3(boolean isContextConsidered,String pageXmlFile_1,String pageXmlFile_2){
        if (isContextConsidered){
            // 分别获取关键文本
            List<String> list_1 = getImportantPageTexts(pageXmlFile_1);
            List<String> list_2 = getImportantPageTexts(pageXmlFile_2);
            if (list_1.isEmpty() && list_2.isEmpty()){
                log.info("两界面均无显式文本，界面相似度为1");
                return 1;
            }
            if (list_1.isEmpty() || list_2.isEmpty()){
                log.info("一界面无显式文本，界面相似度为0");
                return 0;
            }
            // 对文本集合统一编码
            Model.encodeTexts(list_1);
            Model.encodeTexts(list_2);
            double[][] m = new double[list_1.size()][list_2.size()];
            for (int i = 0; i < list_1.size(); ++i) {
                for (int j = 0; j < list_2.size(); ++j) {
                    m[i][j] = Model.getSimilarity(list_1.get(i), list_2.get(j));
                }
            }
            // 语义匹配的文本数量
            int matchNum = 0;
//            while (getAndSetMax(m) >= Threshold.ELE_SIM.getValue()) {
//                ++matchNum;
//            }
//            System.out.println("matchNum:"+matchNum);
//            System.out.println("list_1.size(): " + list_1.size());
//            System.out.println("list_2.size(): " + list_2.size());
//            return matchNum / Math.sqrt(list_1.size() * list_2.size());
            // 语义匹配的文本元素对的相似度求和
            double matchSim = 0;
            double sim = 0;
            while ((sim=getAndSetMax(m)) >= Threshold.ELE_SIM.getValue()) {
                matchSim += sim;
//                System.out.println(sim);
                ++matchNum;
            }
            System.out.println("matchNum:" + matchNum);
            System.out.println("matchSim:" + matchSim);
            System.out.println("list_1.size(): " + list_1.size());
            System.out.println("list_2.size(): " + list_2.size());

            return matchSim / Math.sqrt(list_1.size() * list_2.size());
        } else {
            return 0;
        }
    }

    // Add: 获取当前界面中的所有有效图像和文本
    public static List<Object> getPageInfo(AndroidDriver driver){
        List<Object> pageInfo = new ArrayList<>();
        List<MobileElement> tempElements = new ArrayList<>();
        try {
            tempElements = driver.findElementsByXPath("//hierarchy//*");
        }catch (StaleElementReferenceException se){
            log.info("界面获取不到元素");
            return pageInfo;
        }
        // 获取xml布局文件
        String hierarchyLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
        UtilsHierarchyXml.takeXmlSnapshot(driver, hierarchyLayoutXmlFile);

        // 判断是否是叶节点 并 保存元素信息
        for(MobileElement me:tempElements){
            // 生成元素xpath
            String xpath = "";
            try{
                xpath = UtilsXpath.getAbsoluteXpath(me, hierarchyLayoutXmlFile);
            }catch (StaleElementReferenceException se){
                log.info("元素已失效，检查下一个元素");
                continue;
            }
            String childXpath = xpath+"/*";
            List<MobileElement> childElements = new ArrayList<>();
            try {
                childElements = driver.findElementsByXPath(childXpath);
            }catch (WebDriverException wex){
//                wex.printStackTrace();
                System.out.println("***** Page out of time. Next element. *****");
                continue;
            }
            // 过滤掉 不显示 不可用 尺寸异常 的元素
            boolean isValid = me.isDisplayed() && me.isEnabled() && (me.getSize().getHeight()>=25) && (me.getSize().getWidth()>=25);
            // 如果获取不到子元素，此元素是叶子结点
            if(childElements.isEmpty() && isValid){
                String text = me.getText();
                // 文本非空使用文本作为元素信息，否则使用图像。加入界面信息
                if(!StringUtils.isBlank(text)){
                    pageInfo.add(text);
                }else{
                    try{
                        byte[] img = me.getScreenshotAs(OutputType.BYTES);
                        pageInfo.add(img);
                    }catch (ScreenshotException se){
                        System.out.println("裁剪区域有误,不提取元素图像: The cropping area is outside of the original bitmap");
                    }
                }
            }
        }
        return pageInfo;
    }

    // Add: 获取当前界面中重要文本(最大文本、最顶端文本、selected为true的文本)
    public static List<String> getImportantPageTexts(String pageXmlFile){
        List<String> importantTexts = new ArrayList<>();
        UtilsXmlLoader xmlLoader = new UtilsXmlLoader();
        xmlLoader.parseXml(pageXmlFile);
        // 解析出所有结点
        List<XmlTreeNode> allNodes = xmlLoader.getAllNodes();
        String largeText = "";
        int maxArea = 0;
        String topText = "";
        int minY = 2000;
        for(XmlTreeNode node:allNodes){
            if (node!=null){
                String text = ((UiNode)node).getAttribute("text");
                if(!StringUtils.isBlank(text)){
                    // selected为true的文本元素直接加入
                    if("true".equals(((UiNode)node).getAttribute("selected"))){
                        importantTexts.add(text);
                        System.out.println("selected: "+text);
                        continue;
                    }
                    // 获取元素尺寸(宽高)
                    Dimension d = ((UiNode)node).getDimension();
                    // 计算元素面积
                    int area = d.getWidth() * d.getHeight();
                    if(area > maxArea){
                        largeText = text;
                        maxArea = area;
                    }
                    // 获取元素中心点Y坐标
                    int Y = ((UiNode)node).getY1() + (d.getHeight()/2);
                    if(Y < minY && Y >= 0){
                        topText = text;
                        minY = Y;
                    }
                }
            }
        }
        if (!largeText.equals("")){
            System.out.println("large: "+largeText);
            importantTexts.add(largeText);
        }

        if (!topText.equals("")){
            System.out.println("top: "+topText);
            importantTexts.add(topText);
        }

        return importantTexts;
    }

    // 返回矩阵m中的最大值，并将最大值所在的行和列都置为0（成功找到一对后，这两个匹配的元素不再参与配对）
    private static double getAndSetMax(double[][] m) {
        double max = 0;
        int first = 0;
        int second = 0;
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[0].length; ++j) {
                if (m[i][j] > max) {
                    max = m[i][j];
                    first = i;
                    second = j;
                }
            }
        }
        for (double[] mm : m) {
            mm[second] = 0;
        }
        for (int i = 0; i < m[0].length; ++i) {
            m[first][i] = 0;
        }
        return max;
    }

    // Add: 路径搜索1（点击），在邻近界面搜索目标元素，返回目标元素和要点击的元素
    public static MobileElement retrieveElementFromNeighborPage(AndroidDriver driver, EnhancedMobileElement originEle, String curLayoutXmlFile, List<EnhancedMobileElement> clickEle, boolean isContextConsidered) throws InterruptedException, IOException, ClassNotFoundException {
        // 获取当前界面所有可点击元素
        List<EnhancedMobileElement> clickableELes = fetchClickableStmsOnState(curLayoutXmlFile);
        log.info("可点击元素数量：" + clickableELes.size());
        MobileElement targetEle = null;
        int isSearch = 1; // 默认对此界面进行搜索
        // 获取旧版本元素所在界面
        int line = originEle.getLine();
        String originalLayoutXmlFile = Settings.extractInfoPath + Settings.sep + RepairRunner.appEnum.getAppName() +
                Settings.sep + RepairRunner.testcaseName + Settings.sep + line + "-hierarchy" + Settings.XML_EXT;
        if(!clickableELes.isEmpty()){
            // 保存初始界面xml布局
            String preStateLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
            UtilsHierarchyXml.takeXmlSnapshot(driver, preStateLayoutXmlFile);

            // 1 优先尝试点击More Options
            MobileElement moreOptionsEle = null;
            try{
                moreOptionsEle = (MobileElement) driver.findElementByAccessibilityId("More options");
            }catch (Exception e){
//                e.printStackTrace();
                log.info("未搜索到 More Options元素");
                moreOptionsEle = null;
            }
            if(moreOptionsEle != null){
                log.info("优先探索 More Options 元素：");
                moreOptionsEle.click();
                Thread.sleep(3000);
                String moreOptionsLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
                UtilsHierarchyXml.takeXmlSnapshot(driver, moreOptionsLayoutXmlFile);
                targetEle = retrieveElementFromCurrentPage(driver,originEle,moreOptionsLayoutXmlFile,isContextConsidered);
                if(targetEle!=null){
                    EnhancedMobileElement clickMoreOptionsEle = new EnhancedMobileElement();
                    clickMoreOptionsEle.setXpath("//android.widget.ImageView[@content-desc=\"More options\"]");
                    clickMoreOptionsEle.setAction(AppiumAction.Click);
                    clickMoreOptionsEle.setContentDesc("More options");
                    clickMoreOptionsEle.setLocator(new AppiumLocator("contentDesc", "More options"));
                    clickEle.add(clickMoreOptionsEle);
                    return targetEle;
                }else{
                    // 返回原界面
//                    backToPreState(driver,preStateLayoutXmlFile);
                    log.info("***** 此界面未找到目标元素，请返回原界面，并输入任意数字继续。 *****");
                    Scanner sc2 = new Scanner(System.in);
                    int input2 = sc2.nextInt();
                }
            }
            long start = System.currentTimeMillis();
            EnhancedMobileElement lastTryEme = null;
            // 2 所有可点击元素中全局搜索
            for(EnhancedMobileElement eme: clickableELes){
                // 排除点击后可能会退出的元素
                String str = eme.getText()+" "+eme.getContentDesc();
                if(str.contains("Navigate up")||str.contains("Back")||str.contains("CANCEL")||str.contains("OK")){
                    log.info("find element that may cause return, store");
                    lastTryEme = eme;
                    continue;
                }else{// 触发元素点击
                    // 点击元素进入新页面
                    fireEleClick(driver, eme);
                    // Add: 手动选择是否搜索该页面
                    log.info("Please determine whether to search on this page: (0-false/1-true)");
                    Scanner sc = new Scanner(System.in);
                    isSearch = sc.nextInt();
                    // 保存新页面的层次布局
                    String layoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
                    UtilsHierarchyXml.takeXmlSnapshot(driver, layoutXmlFile);
                    // 输入0 或 界面相似度小于0.5(0.4) 不搜索此界面
//                    System.out.println(getPageSimilarity(true, originalLayoutXmlFile,layoutXmlFile));
                    //  按相似度过滤：|| getPageSimilarity(true, originalLayoutXmlFile,layoutXmlFile)<Threshold.PAGE_SIM.getValue()
                    if(isSearch == 0 || getPageSimilarity(true, originalLayoutXmlFile,layoutXmlFile)<Threshold.PAGE_SIM.getValue()){
                        log.info("*** 未搜索此界面，返回原界面 ***");
                        // 返回原界面
//                        backToPreState(driver,preStateLayoutXmlFile);
//                        // 手动返回操作
                        Scanner sc2 = new Scanner(System.in);
                        int input = sc2.nextInt();
                        continue;
                    }
                    // 在新界面执行元素搜索
                    targetEle = retrieveElementFromCurrentPage(driver,originEle,layoutXmlFile,isContextConsidered);
                    if(targetEle!=null){
                        // 保存要点击的元素
                        // Add: 将点击元素eme传出去，由于值传递，clickEle = eme 无法把值传出去，
                        // 故设置参数为List形式，修改List（Add操作） 或返回一个Pair(目标元素，点击元素)
                        clickEle.add(eme);
                        return targetEle;
                    }else{
                        log.info("*** 此界面未找到目标元素，返回原界面 ***");
                        // 返回原界面
//                        backToPreState(driver,preStateLayoutXmlFile);
//                         driver.navigate().back();
                        // 手动返回操作
                        Scanner sc2 = new Scanner(System.in);
                        int input2 = sc2.nextInt();
                    }
                }
                if ((System.currentTimeMillis()-start)/1000 > 100){
                    log.info("路径搜索超过100s，退出。");
                    break;
                }
            }
            if(lastTryEme != null){
                log.info("Please determine whether to click on a RETURN element (0-false/1-true)");
                Scanner sc = new Scanner(System.in);
                isSearch = sc.nextInt();
                if(isSearch == 1) {
                    fireEleClick(driver, lastTryEme);
                    log.info("Please determine whether to search on this page: (0-false/1-true)");
                    Scanner sc2 = new Scanner(System.in);
                    isSearch = sc2.nextInt();
                    String layoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
                    UtilsHierarchyXml.takeXmlSnapshot(driver, layoutXmlFile);
                    // 输入0 或 界面相似度小于0.5(0.4) 不搜索此界面
                    //  按相似度过滤：|| getPageSimilarity(true, originalLayoutXmlFile,layoutXmlFile)<Threshold.PAGE_SIM.getValue()
                    if(isSearch == 0 || getPageSimilarity(true, originalLayoutXmlFile,layoutXmlFile)<Threshold.PAGE_SIM.getValue()){
                        log.info("*** 未搜索点击“返回”操作后的界面 ***");
                        return null;
                    }
                    // 在新界面执行元素搜索
                    targetEle = retrieveElementFromCurrentPage(driver,originEle,layoutXmlFile,isContextConsidered);
                    if(targetEle!=null){
                        // 保存要点击的元素
                        // Add: 将点击元素eme传出去，由于值传递，clickEle = eme 无法把值传出去，
                        // 故设置参数为List形式，修改List（Add操作） 或返回一个Pair(目标元素，点击元素)
                        clickEle.add(lastTryEme);
                        return targetEle;
                    }
                }
            }
        }else{
            // 无可点击元素
            log.info("当前界面无可点击元素");
        }
        return null;
    }

    // 返回初始界面状态：输入初始（前一）界面xml，已经修复的/执行过的测试语句（已修复列表/标准答案列表,根据修复时是否使用标准答案决定使用哪个列表）
    public static void backToPreState(AndroidDriver driver, String preStateXmlFile) throws InterruptedException {
        // 1 先尝试使用back物理返回
        driver.navigate().back();
        // 2 保存当前界面布局
        String curStateXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
        UtilsHierarchyXml.takeXmlSnapshot(driver, curStateXmlFile);
        if(getPageSimilarity(true, curStateXmlFile, preStateXmlFile) < 0.9){
            // 3 如果返回的界面与初始界面相似度小于0.9，认为未返回到正确初始界面，重新启动应用，执行一遍前面所有测试动作
            log.info("back无法返回初始界面，重启应用");
            // 重启应用 driver.resetApp()会重装应用
            driver.closeApp();
            driver.launchApp();
            Thread.sleep(5000);

            for(Statement st : RepairRunner.executedStm){
                if ( st == null)
                    continue;
                // 除touch和navigate外的测试动作
                if(st instanceof EnhancedMobileElement){
                    MobileElement e = getElementFromStatement(driver, ((EnhancedMobileElement) st).getLocator());
                    // 执行
                    RepairRunner.fireEvent(e, st);
                }else{
                    // navigate操作
                    if (st.getAction().equals(AppiumAction.Navigate)) {
                        // 执行
                        if (st.getValue().equals("back")) {
                            driver.navigate().back();
                        } else if (st.getValue().equals("forward")) {
                            driver.navigate().back();
                        } else if (st.getValue().equals("refresh")) {
                            driver.navigate().refresh();
                        }
                    }
                    // touch操作
                    if (st.getAction().equals(AppiumAction.Touch)) {
                        if (st.getValue().equals("swipe")) {
                            // 执行
                            Point startPoint = ((EnhancedTouchAction)st).getStartPoint();
                            Point endPoint = ((EnhancedTouchAction)st).getEndPoint();
                            new TouchAction(driver).longPress(PointOption.point(startPoint)).moveTo(PointOption.point(endPoint)).release().perform();
                        } else if (st.getValue().equals("tap")) {
                            Point tapPoint = ((EnhancedTouchAction)st).getStartPoint();
                            new TouchAction(driver).tap(PointOption.point(tapPoint)).perform();
                        }
                    }
                }
            }
            // 4 如果仍然未到达初始界面，手动执行
            UtilsHierarchyXml.takeXmlSnapshot(driver, curStateXmlFile);
            if (getPageSimilarity(true, curStateXmlFile, preStateXmlFile) < 0.9){
                log.info("***** 返回初始界面失败，请手动返回初始界面，并输入任意数字继续后续操作 *****");
                Scanner sc2 = new Scanner(System.in);
                int input2 = sc2.nextInt();
            }else {
                log.info("已返回到初始界面");
            }
        } else {
            log.info("已返回到初始界面");
        }
    }

    // Add: 路径搜索2（滑动）
    public static MobileElement retrieveElementFromSwipedPage(int start_x, int start_y,int end_x, int end_y, AndroidDriver driver, EnhancedMobileElement originEle, boolean isContextConsidered) throws InterruptedException, IOException, ClassNotFoundException {
        MobileElement targetElement = null;
        new TouchAction(driver).longPress(PointOption.point(start_x, start_y)).moveTo(PointOption.point(end_x, end_y)).release().perform();
        Thread.sleep(5000);
        // 捕获当前屏幕状态的层次布局文件
        String swipeLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
        UtilsHierarchyXml.takeXmlSnapshot(driver, swipeLayoutXmlFile);
        targetElement = UtilsRepair.retrieveElementFromCurrentPage(driver, originEle, swipeLayoutXmlFile, isContextConsidered);
        if (targetElement==null){
            log.info("滑动后仍未找到目标元素");
        }
        return targetElement;
    }

    // Add: 获取尺寸最大的可滑动元素的 滑动起始坐标(1/2, 9/10) 和 滑动终止坐标(1/2, 1/10)
    public static List<Integer> getSwipedElement(String curLayoutXmlFile){
        List<Integer> coord = new ArrayList<>();
        int start_x, start_y, end_x, end_y;
        int max = 0;
        UiNode maxNode = null;
        UtilsXmlLoader xmlLoader = new UtilsXmlLoader();
        xmlLoader.parseXml(curLayoutXmlFile);
        // 获取最大可滑动结点
        List<XmlTreeNode> allNodes = xmlLoader.getAllNodes();
        for(XmlTreeNode node:allNodes){
            if (node != null) {
                if (node instanceof UiNode && "true".equals(((UiNode) node).getAttribute("scrollable"))){
                    // 获取元素尺寸(宽高)
                    Dimension d = ((UiNode)node).getDimension();
                    // 计算元素面积
                    int area = d.getWidth() * d.getHeight();
                    if(area > max){
                        maxNode = (UiNode)node;
                        max = area;
                    }
                }
            }
        }
        if(maxNode == null){
            System.out.println("界面无可滑动元素");
            return coord;
        }
        int height = maxNode.getDimension().getHeight();
        int width = maxNode.getDimension().getWidth();
        System.out.println("宽高：" + width + " " + height);
        System.out.println("轮廓：" + maxNode.getX1() +" "+ maxNode.getY1() +" "+ maxNode.getX2() +" "+ maxNode.getY2());
        System.out.println();
        // 滑动起始和终止坐标
        start_x = maxNode.getX1() + width/2;
        end_x = start_x;
        start_y = (int) (maxNode.getY2() - height * 0.1);
        end_y = (int) (maxNode.getY1() + height * 0.1);
        coord.add(start_x);
        coord.add(start_y);
        coord.add(end_x);
        coord.add(end_y);
        return coord;
    }

    // Add: 触发元素点击操作，进入新界面
    public static void fireEleClick(AndroidDriver driver, EnhancedMobileElement clickableStm) {
        AppiumLocator locator = clickableStm.getLocator();
        MobileElement elementToBeClicked = null;
        log.info("Click Element: " + locator.toString());
        try {
            if (locator.getStrategy().equals("resourceId")) {
                elementToBeClicked = (MobileElement) driver.findElementById(locator.getValue());
            } else if (locator.getStrategy().equals("contentDesc")) {
                elementToBeClicked = (MobileElement) driver.findElementByAccessibilityId(locator.getValue());
            } else if (locator.getStrategy().equals("xpath")) {
                elementToBeClicked = (MobileElement) driver.findElementByXPath(locator.getValue());
            }
        } catch (NoSuchElementException e) {
            log.info("界面变动，元素已不在当前界面，将点击下一个元素。。。");
            return;
        }
        elementToBeClicked.click();
        // 元素点击后需要等待 5s 以待新界面加载完成
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.info("Driver wait has been interrupted");
        }
    }

    // 将字符串中的空白字符替换为空格
//    public static String removeNewLines(String str) {
//        return str.replaceAll("[\\t\\n\\f\\r]", " ");
//    }

    // 计算两个点的欧氏距离
//    public static double getEuclideanDistance(Point point1, Point point2) {
//        return Math.sqrt(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));
//    }

    // 计算坐到达屏幕边界最远的距离
//    public static double getMaxBoundDistance(Point point,Dimension windowSize) {
//        double max1 =  Math.max(getEuclideanDistance(new Point(0,0), point),
//                getEuclideanDistance(new Point(windowSize.width, 0), point));
//        double max2 = Math.max(getEuclideanDistance(new Point(0, windowSize.height), point),
//                getEuclideanDistance(new Point(windowSize.width, windowSize.height), point));
//        return Math.max(max1, max2);
//    }

    // 查找当前节点的兄弟节点，兄弟节点必须包含文本
    // 逐级向上层节点查找（最多找三步，且母节点尺寸必须小于屏幕尺寸的 1/6），遍历其下的叶节点，找不到返回一个空 List
//    public static List<Set<String>> getBroNodesText(UiNode curNode, Dimension windowSize, boolean isIdConsidered) {
//        List<Set<String>> broTextList = new ArrayList<>();
//        int windowArea = windowSize.height * windowSize.width;
//        for (int i=1; i<=3; i++) {
//            XmlTreeNode parentNode = curNode.getParent();
//            if (parentNode instanceof RootWindowNode || ((UiNode) parentNode).getY2() == windowSize.height) {
//                return broTextList;
//            } else {
//                UiNode parNode = (UiNode) parentNode;
//                // 计算母节点的面积，面积应小于屏幕面积的 1/6
//                int parNodeArea = parNode.getDimension().height * parNode.getDimension().width;
//                if (parNodeArea > windowArea / 6) {
//                    return broTextList;
//                } else {
//                    List<UiNode> broNodes = curNode.getBrotherNodes();
//                    for (UiNode bro : broNodes) {
//                        Set<String> broText = new HashSet<>();
//                        List<UiNode> leaves = bro.getLeafNodes();
//                        for (UiNode leaf : leaves) {
//                            String temp = leaf.getAttribute("content-desc");
//                            if (StringUtils.isNotBlank(temp) && !isInteger(temp)) broText.addAll(WordsSplit.getWords(removeNewLines(temp.trim())));
//                            temp = leaf.getAttribute("text");
//                            if (StringUtils.isNotBlank(temp) && !isInteger(temp)) broText.addAll(WordsSplit.getWords(removeNewLines(temp.trim())));
//
//                            if (isIdConsidered && broText.isEmpty()) {
//                                temp = leaf.getAttribute("resource-id");
//                                if (StringUtils.isNotBlank(temp) && !isInteger(temp)) {
//                                    if (temp.contains(":id/")) {
//                                        temp = temp.substring(temp.indexOf("/") + 1);
//                                    }
//                                    broText.addAll(WordsSplit.getWords(removeNewLines(temp.trim())));
//                                }
//                            }
//                        }
//                        if (!broText.isEmpty()) {
//                            broTextList.add(broText);
//                        }
//                    }
//                    if (!broTextList.isEmpty()) {
//                        // 找到临近节点的文本就返回，不再向上迭代查找
//                        return broTextList;
//                    }
//                }
//            }
//            curNode = (UiNode) parentNode;
//        }
//        return broTextList;
//    }

//    public static boolean isInteger(String str) {
//        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//        return pattern.matcher(str).matches();
//    }

    // 滑动搜索参考
//            if (swipeRect == null) {
//                // 初始界面找不到修复元素，查找可滑动布局
//                swipeRect = retrieveScrollableNode(RepairRunner.curLayoutXmlFile);
//                if (swipeRect == null) {
//                    // 未找到最相似的元素，同时界面不可滑动
//                    return null;
//                }
//            }
//            // 上滑以找出更多候选元素
//            EnhancedTouchAction swipe = new EnhancedTouchAction("swipe");
//            // 起始点为滑动界面从上至下 7/8 处的中间位置
//            // 结束点为滑动界面从上至下 1/8 处的中间位置
//            Point startPoint = new Point(swipeRect.x + swipeRect.width / 2, swipeRect.y + swipeRect.height * 7/8);
//            Point endPoint = new Point(swipeRect.x + swipeRect.width / 2, swipeRect.y + swipeRect.height /8);
//            swipe.setStartPoint(startPoint);
//            swipe.setEndPoint(endPoint);
//            repairedSwipe.add(swipe);
//
//            String preState = driver.getPageSource();
//            // 滑动界面
//            log.info("滑动界面，继续探索。。。");
//            new TouchAction(driver).press(PointOption.point(startPoint)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(endPoint)).release().perform();
//            String postState = driver.getPageSource();
//            if (preState.equals(postState) || repairedSwipe.size() >= 3) {
//                // 上滑过后没变化，表明已经滑到底了，无法再找到新的元素了
//                // 此时将页面状态恢复到滑动前的状态并退出
//                for (int i=1; i<=repairedSwipe.size()-1; i++) {
//                    new TouchAction(driver).press(PointOption.point(endPoint)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).moveTo(PointOption.point(startPoint)).release().perform();
//                }
//                repairedSwipe.clear();
//                RepairRunner.curLayoutXmlFile = originXmlFile;
//                return null;
//            } else {
//                // 滑动带来新元素，清除老元素
//                result.clear();
//                // 捕获当前屏幕状态的层次布局文件
//                RepairRunner.curLayoutXmlFile = Settings.TEMP_XML_SAVED_FOLDER + Settings.sep + System.currentTimeMillis() + Settings.XML_EXT;
//                UtilsHierarchyXml.takeXmlSnapshot(driver, RepairRunner.curLayoutXmlFile);
//            }
//        }

    // 查找当前界面上的可滑动节点，返回可滑动布局位置
//    private static Rectangle retrieveScrollableNode(String curLayoutXmlFile) {
//        UtilsXmlLoader xmlLoader = new UtilsXmlLoader();
//        xmlLoader.parseXml(curLayoutXmlFile);
//        List<XmlTreeNode> nodeList = xmlLoader.getAllNodes();
//
//        for (XmlTreeNode node : nodeList) {
//            if (node instanceof UiNode && "true".equals(((UiNode) node).getAttribute("scrollable"))) {
//                String[] boundStr = ((UiNode)node).getAttribute("bounds").substring(1).split("[,\\[\\]]+");
//                int[] bounds = Arrays.stream(boundStr).mapToInt(Integer::parseInt).toArray();
//                int width = bounds[2] - bounds[0];
//                int height = bounds[3] - bounds[1];
//                return new Rectangle(bounds[0], bounds[1], height, width);
//            }
//        }
//        return null;
//    }

    // 在当前布局文件中查找可点击元素，将其转化成 Statement 方便生成测试语句
    // 鉴于某些情况下，父节点已定义 clickable=true，子节点不再定义 clickable 属性，考虑获取第一个包含显示文本的元素
    public static List<EnhancedMobileElement> fetchClickableStmsOnState(String curLayoutXmlFile) {
        UtilsXmlLoader xmlLoader = new UtilsXmlLoader();
        xmlLoader.parseXml(curLayoutXmlFile);
        XmlTreeNode rootNode = xmlLoader.getRootNode();

        // 获取可点击节点
        List<XmlTreeNode> clickableNodes = new ArrayList<>();
        getClickableNodes(rootNode, clickableNodes);

        // 将可点击节点转化为可点击元素
        List<EnhancedMobileElement> clickableElements = new ArrayList<>();
        for (XmlTreeNode node : clickableNodes) {
            if (node != null) {
                clickableElements.add(UtilsXpath.castNode2Element(xmlLoader.getAllNodes(), (UiNode) node));
            }
        }
        return clickableElements;
    }

    // 获取指定节点下的可点击节点(SEMTER未根据可点击属性筛选，对于所有元素执行点击操作，页面变化认为元素可点击)
    // 上层元素clickable=true，当前元素clickable=false，但是可以点击。此时，上层元素下的所有元素点击后的效果应该相同
    private static void getClickableNodes(XmlTreeNode node, List<XmlTreeNode> clickableNodes) {
        // 叶节点
        if (!node.hasChild()) {
            if ("true".equals(((UiNode)node).getAttribute("clickable"))) {
                clickableNodes.add(node);
            }
            return;
        }
        // clickable 属性值为 true 的非叶节点需找到子节点中第一个包含显式文本的叶元素，找不到就返回第一个找到的叶元素
        if (node instanceof UiNode && "true".equals(((UiNode) node).getAttribute("clickable"))) {
            UiNode keyNode = null;
            Stack<XmlTreeNode> stack = new Stack<>();
            stack.push(node);
            boolean flag = false;
            while(!stack.empty()) {
                UiNode cur = (UiNode) stack.pop();
                if(!cur.hasChild()) {
                    if ("true".equals(cur.getAttribute("clickable"))) {
                        clickableNodes.add(cur);
                    } else if (!flag && (StringUtils.isNotBlank(cur.getAttribute("text")) || StringUtils.isNotBlank(cur.getAttribute("content-desc")))) {
                        // 获取第一个遇到的包含显式文本的叶元素，对于后面遇到的显式文本叶元素不予理会
                        // 不使用 break 的原因在于后续叶元素的 clickable 属性值为 true 时，应当加入 clickableNodes
                        keyNode = cur;
                        flag = true;
                    } else if (keyNode == null) {
                        // 将遇到的第一个叶元素设为 keyNode，以防后续遇不到包含显式文本的叶元素
                        keyNode = cur;
                    }
                } else {
                    // 当前节点有多个孩子节点时，由于栈后进先出的特性，需要倒栈以确保先访问在布局上靠前的元素
                    List<XmlTreeNode> childrenList = cur.getChildrenList();
                    for (int i=childrenList.size()-1; i>=0; i--) {
                        stack.push(childrenList.get(i));
                    }
                }
            }
            clickableNodes.add(keyNode);
            return;
        }

        // 对于每一个子节点，重复上述步骤
        for(XmlTreeNode childNode : node.getChildrenList()) {
            getClickableNodes(childNode, clickableNodes);
        }
    }

    // 对于查找到的修复元素，根据原测试语句的定位策略决策出合适的定位器
    public static AppiumLocator getAppropriateLocator(AndroidDriver driver, EnhancedMobileElement statement, MobileElement candidateElement, String curLayoutXmlFile) {
        AppiumLocator originLocator = statement.getLocator();
        // 重新获取一遍元素，防止元素过期
        // candidateElement = (MobileElement) driver.findElementByXPath(xpath);
        if (originLocator.getStrategy().equals("resourceId") && StringUtils.isNotBlank(candidateElement.getAttribute("resourceId"))) {
            if (driver.findElementsById(candidateElement.getAttribute("resourceId")).size() == 1) {
                return new AppiumLocator("resourceId", candidateElement.getAttribute("resourceId"));
            }
        } else if (originLocator.getStrategy().equals("contentDesc") && StringUtils.isNotBlank(candidateElement.getAttribute("contentDescription"))) {
            if (driver.findElementsByAccessibilityId(candidateElement.getAttribute("contentDescription")).size() == 1) {
                return new AppiumLocator("contentDesc", candidateElement.getAttribute("contentDescription"));
            }
        } else if (StringUtils.isNotBlank(candidateElement.getAttribute("resourceId"))) {
            if (driver.findElementsById(candidateElement.getAttribute("resourceId")).size() == 1) {
                return new AppiumLocator("resourceId", candidateElement.getAttribute("resourceId"));
            }
        } else if (StringUtils.isNotBlank(candidateElement.getAttribute("contentDescription"))) {
            if (driver.findElementsByAccessibilityId(candidateElement.getAttribute("contentDescription")).size() == 1) {
                return new AppiumLocator("contentDesc", candidateElement.getAttribute("contentDescription"));
            }
        }
        String xpath = UtilsXpath.getElementOptimalXpath(driver, candidateElement, curLayoutXmlFile);
        return new AppiumLocator("xpath", xpath);
    }

    public static void printTestCase(EnhancedTestCase tc) {
        for (Integer i: tc.getStatements().keySet()) {
            // Add: 避免空指针异常
            if(tc.getStatements().get(i)!=null){
                System.out.println(tc.getStatements().get(i).getLine() + ":\t" + tc.getStatements().get(i) + ";");
            }else{
                System.out.println( "此行语句已删除" + ";");
            }
        }
    }

    public static void saveTest(ParseTest pt, EnhancedTestCase testRepaired, String oldPath) {
        try {
            pt.parseAndSaveToJava(testRepaired, oldPath);
        } catch (IOException e) {
            log.error("An error occurred when saving the repaired test into java file...");
            e.printStackTrace();
        }
    }
}
