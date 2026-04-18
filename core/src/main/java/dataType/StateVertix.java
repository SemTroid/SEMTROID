package main.java.dataType;

//import main.java.config.Threshold;
//import main.java.util.UiNode;
//import main.java.util.UtilsXmlLoader;
//import main.java.util.XmlTreeNode;
//import main.java.utils.WordsSplit;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;

// import static main.java.util.UtilsRepair.computeSimilarity;
// import static main.java.util.UtilsRepair.removeNewLines;

public class StateVertix {
//    private final String xmlFilePath;
//    private List<EnhancedMobileElement> elesToBeClicked;
//    private Set<String> stateKeywordSet;
//
//    public StateVertix(String filePath) {
//        this.xmlFilePath = filePath;
//        elesToBeClicked = new ArrayList<>();
//        stateKeywordSet = new HashSet<>();
//
//        UtilsXmlLoader xmlLoader = new UtilsXmlLoader();
//        xmlLoader.parseXml(filePath);
//        List<XmlTreeNode> nodeList = xmlLoader.getLeafNodes();
//        for (XmlTreeNode node : nodeList) {
//            UiNode tempNode = (UiNode) node;
//            String tempStr = tempNode.getAttribute("content-desc");
//            if (StringUtils.isNotBlank(tempStr)) stateKeywordSet.addAll(WordsSplit.getWords(removeNewLines(tempStr.trim())));
//            tempStr = tempNode.getAttribute("text");
//            if (StringUtils.isNotBlank(tempStr)) stateKeywordSet.addAll(WordsSplit.getWords(removeNewLines(tempStr.trim())));
//        }
//    }
//
//    public String getXmlFilePath() {
//        return xmlFilePath;
//    }
//
//    public List<EnhancedMobileElement> getElesToBeClicked() {
//        return elesToBeClicked;
//    }
//
//    public void setElesToBeClicked(List<EnhancedMobileElement> elesToBeClicked) {
//        this.elesToBeClicked = elesToBeClicked;
//    }
//
//    public void addElement(EnhancedMobileElement element) {
//        this.elesToBeClicked.add(element);
//    }

    // 页面状态比较逻辑：收集页面上的文本形成集合，计算相似度得分判断是否相似
//    @Override
//    public boolean equals(Object obj) {
//        if (obj instanceof StateVertix) {
                // 计算当前文本集合与原文本集合之间的语义相似度
//            double stateSimScore = computeSimilarity(this.stateKeywordSet, ((StateVertix) obj).stateKeywordSet);
//            if (stateSimScore >= Threshold.STATE_SIM_SCORE.getValue()) {
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }
}
