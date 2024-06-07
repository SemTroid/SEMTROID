package main.java.dataType;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import main.java.runner.RepairRunner;
import main.java.util.UtilsRepair;
import main.java.util.UtilsXpath;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.text.View;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
/**
* @Version 1.0
* @Author anonymous author
*/
public class Context implements Serializable {
    private static final long serialVersionUID = 6254210471854577589L;
    private static final Logger logger = LoggerFactory.getLogger(Context.class);
    private final List<ViewTreeInfo> context;
    public List<ViewTreeInfo> getContext(){
        return context;
    }
    public int getContextSize() { return context.size(); }

    public Context(AndroidDriver driver, MobileElement me, String hierarchyLayoutXmlFile){
        List<ViewTreeInfo> context1;
        if(RepairRunner.isContextConsidered){
            try {
                context1 = collectContext(driver,me,hierarchyLayoutXmlFile);
            }catch (StaleElementReferenceException ex){
                // e.printStackTrace();
                context1 = new ArrayList<>();
            }catch (NoSuchElementException ex){
                context1 = new ArrayList<>();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            context1 = new ArrayList<>();
        }
        this.context = context1;
    }

    private static List<ViewTreeInfo> collectContext(AndroidDriver driver, MobileElement me,String hierarchyLayoutXmlFile) throws StaleElementReferenceException, NoSuchElementException, InterruptedException {
        List<ViewTreeInfo> res = new ArrayList<>();
        // 目标元素绝对xpath
        String xpath = UtilsXpath.getAbsoluteXpath(me,hierarchyLayoutXmlFile);
        String temp; // 暂存当前结点xpath（要排除）
        // 计算xpath层数
        int cnt = 0;
        for (int i = 0; i < xpath.length(); ++i) {
            if (xpath.charAt(i) == '/') {
                cnt++;
            }
        }
        while (res.isEmpty() && cnt > 2) {
            // 保存当前结点xpath
            temp = xpath;
            // 当前元素（用于向上找时，排除此元素以及它的子元素）
            MobileElement excludedEle = (MobileElement) driver.findElementByXPath(temp);
            // 向上一层
            xpath = xpath.substring(0, xpath.lastIndexOf("/"));
            --cnt;
            // 搜寻上下文元素(所有兄弟结点及其子节点)，并排除要获取上下文的目标元素temp及其子元素,过滤无效结点
            List<ViewTreeInfo> elements = collect(driver, excludedEle, xpath, hierarchyLayoutXmlFile);
            res.addAll(elements);
            // 只保留叶结点
            if(!res.isEmpty())
                reserveBasicElements(driver, res);
        }
        if(res.size()>2){
            res = reserveAdjacentElement(me, res);
        }
        System.out.println("size of context: "+res.size());
        return res;
    }
    // 获取目标元素的父结点，兄弟结点及其子结点（不包括目标元素本身及其子结点）
    private static List<ViewTreeInfo> collect(AndroidDriver driver, MobileElement ExcludedEle, String xpath, String hierarchyLayoutXmlFile) throws StaleElementReferenceException, NoSuchElementException, InterruptedException {
        // 候选元素集合：所有兄弟结点及其子结点
        List<ViewTreeInfo> elements = new ArrayList<>();
        // 目标元素所在层的所有元素（目标元素+兄弟结点）
        List<MobileElement> mobileElements;
        try {
            // 获取 目标元素ExcludedEle 的父结点（xpath）的下层子结点
            mobileElements = (List<MobileElement>)driver.findElementsByXPath(xpath+"/*");
        }catch (WebDriverException wex){
            mobileElements = new ArrayList<>();
        }
        if(mobileElements.isEmpty()){
            return elements;
        }
        for(MobileElement me:mobileElements){
            // 收集排除掉目标元素及其子孙结点
            if(!me.equals(ExcludedEle)){
                if(me.isEnabled() && me.isDisplayed()){// 过滤无用元素（此处的下层子结点不能使用elementFilter()进行尺寸过滤，否则会过滤掉某些非叶结点导致此非叶结点的子孙结点也被排除）
                    // 加入当前层元素
                    ViewTreeInfo v = new ViewTreeInfo(me,hierarchyLayoutXmlFile);
                    v.setXpath(v.getXpath().split(";")[0]);
                    elements.add(v);
                    String tempXpath = v.getXpath();
                    List<MobileElement> tempList;
                    try {
                        tempList = driver.findElementsByXPath(tempXpath+"//*");
                    }catch (WebDriverException wex){
                        tempList = new ArrayList<>();
                    }
                    if (tempList.isEmpty()){
                        continue;
                    }
                    for(MobileElement e:tempList){
                        if(UtilsRepair.elementFilter(e)){
                            // 加入下层元素
                            ViewTreeInfo v2 = new ViewTreeInfo(e,hierarchyLayoutXmlFile);
                            v2.setXpath(v2.getXpath().split(";")[0]);
                            elements.add(v2);
                        }
                    }
                }
            }
        }
        return elements;
    }
    // 只保留叶结点
    private static void reserveBasicElements(AndroidDriver driver, List<ViewTreeInfo> res){
        List<ViewTreeInfo> temp = new ArrayList<>(res);
        // 判断是否是叶节点
        for(ViewTreeInfo v:temp){
            String childXpath = v.getXpath()+"/*";
            List<MobileElement> childElements;
            try {
                childElements = driver.findElementsByXPath(childXpath);

            }catch (WebDriverException wex){
                logger.info("***** Page out of time. Next element. *****");
                childElements = new ArrayList<>();
            }
            // 如果获取不到子元素，此元素是叶子结点，否则删除
            if(!childElements.isEmpty()){
                res.remove(v);
            }
        }
    }
    private static List<ViewTreeInfo> reserveAdjacentElement(MobileElement targetEle, List<ViewTreeInfo> res){
        LinkedHashMap<ViewTreeInfo,Integer> map = new LinkedHashMap<>();
        List<ViewTreeInfo> temp = new ArrayList<>();
        // 目标元素中心坐标
        int x1 = targetEle.getLocation().getX() + targetEle.getSize().getWidth()/2;
        int y1 = targetEle.getLocation().getY() + targetEle.getSize().getHeight()/2;
        for(ViewTreeInfo v:res){
            // 候选元素中心坐标
            int x2 = v.getX() + v.getWidth()/2;
            int y2 = v.getY() + v.getHeight()/2;
            int distance = (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1);
            map.put(v,distance);
        }
        // 按距离排序
        LinkedHashMap<ViewTreeInfo,Integer> sortedMap = map
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        int n = 2;
        for(Map.Entry entry : sortedMap.entrySet()){
            if(n==0){
                break;
            }
            temp.add((ViewTreeInfo) entry.getKey());
            n--;
        }
        return temp;
    }
    public boolean isEmpty() {
        return context.isEmpty();
    }
    public int size() {
        return context.size();
    }
    public ViewTreeInfo get(int index) {
        return context.get(index);
    }

}
