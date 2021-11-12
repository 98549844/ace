package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: TreeNode
 * @Date: 13/11/2021 3:44 上午
 * @Author: garlam
 * @Description:
 */


public class TreeNode {
    private static Logger log = LogManager.getLogger(TreeNode.class.getName());
    private String id;
    private String parentId;
    private String name;
    private boolean hasChild;

    public TreeNode(String id, String parentId, String name) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHasChild() {
        return hasChild;
    }

    public void setHasChild(boolean hasChild) {
        this.hasChild = hasChild;
    }
}

