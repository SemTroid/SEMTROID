package main.java.util;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/4 22:52
 */
public class UtilsXmlLoader {

    private XmlTreeNode rootNode;
    private List<XmlTreeNode> nodeList;

    public UtilsXmlLoader() {
    }

    /**
     * 使用 SAX 解析器处理 XML 文件
     * @param xmlPath xml文件路径
     * @return 根据该xml文件构建生成的树，返回树根节点
     */
    public void parseXml(String xmlPath) {
        rootNode = null;
        nodeList = new ArrayList<>();

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;

        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }

        // handler class for SAX parser to receiver standard parsing events:
        // e.g. on reading "<foo>", startElement is called, on reading "</foo>", endElement is called
        DefaultHandler handler = new DefaultHandler(){
            XmlTreeNode parentNode;
            XmlTreeNode currentNode;
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                // starting an element implies that the element that has not yet been closed
                // will be the parent of the element that is being started here
                parentNode = currentNode;
                if ("hierarchy".equals(qName)) {
                    int rotation = 0;
                    for (int i = 0; i < attributes.getLength(); i++) {
                        if ("rotation".equals(attributes.getQName(i))) {
                            try {
                                rotation = Integer.parseInt(attributes.getValue(i));
                            } catch (NumberFormatException nfe) {
                                // do nothing
                            }
                        }
                    }
                    currentNode = new RootWindowNode(attributes.getValue("windowName"), rotation);
                } else {
                    UiNode tmpNode = new UiNode();
                    for (int i = 0; i < attributes.getLength(); i++) {
                        tmpNode.addAttribute(attributes.getQName(i), attributes.getValue(i));
                    }
                    currentNode = tmpNode;
                }
                if (rootNode == null) {
                    // this will only happen once
                    rootNode = currentNode;
                }
                if (parentNode != null) {
                    parentNode.addChild(currentNode);
                    nodeList.add(currentNode);
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                //mParentNode should never be null here in a well formed XML
                if (parentNode != null) {
                    // closing an element implies that we are back to working on the parent node of
                    // the element just closed, i.e. continue to parse more child nodes
                    currentNode = parentNode;
                    parentNode = parentNode.getParent();
                }
            }
        };

        try {
            parser.parse(new File(xmlPath), handler);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return 返回处理 XML 文件生成的根节点
     */
    public XmlTreeNode getRootNode(){
        return rootNode;
    }
    
    /**
     * @return 返回处理 XML 文件生成的所有节点
     */
    public List<XmlTreeNode> getAllNodes(){
        return nodeList;
    }

    /**
     * @return 返回处理 XML 文件生成的所有叶节点
     */
    public List<XmlTreeNode> getLeafNodes() {
        List<XmlTreeNode> list = new ArrayList<>();
        for(XmlTreeNode node : nodeList) {
            if(!node.hasChild()){
                list.add(node);
            }
        }
        return list;
    }
}
