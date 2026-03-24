package main.java.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author QS
 * @version 1.0
 * @date 2022/1/5 11:18
 */
public class XmlTreeNode {

    private static final XmlTreeNode[] CHILDREN_TEMPLATE = new XmlTreeNode[] {};

    protected XmlTreeNode mParent;

    protected final List<XmlTreeNode> mChildren = new ArrayList<>();

    // 当前已经添加了多少个孩子节点
    public int childOffset = 0;
    // 自己是第几个孩子
    public int index;
    // 自己的 className 排第几位，例：//父节点/className[classNameIndex]
    public int classNameIndex = 1;
    public HashMap<String,Integer> classNameMap = new HashMap<>();

    public void addChild(XmlTreeNode child) {
        if (child == null) {
            throw new NullPointerException("Cannot add null child");
        }
        if (mChildren.contains(child)) {
            throw new IllegalArgumentException("Node has already been a child");
        }
        mChildren.add(child);
        child.mParent = this;

        childOffset++;
        child.index = childOffset;
        if(!(child instanceof RootWindowNode)) {
            String className = ((UiNode) child).getAttribute("class");
            classNameMap.merge(className, 1, Integer::sum);
            child.classNameIndex = classNameMap.get(className);
        }
    }

    public List<XmlTreeNode> getChildrenList() {
        return Collections.unmodifiableList(mChildren);
    }

    public XmlTreeNode[] getChildren() {
        return mChildren.toArray(CHILDREN_TEMPLATE);
    }

    public XmlTreeNode getParent() {
        return mParent;
    }

    public boolean hasChild() {
        return mChildren.size() != 0;
    }

    public int getChildCount() {
        return mChildren.size();
    }

    public void clearAllChildren() {
        for (XmlTreeNode child : mChildren) {
            child.clearAllChildren();
        }
        mChildren.clear();
    }
}
