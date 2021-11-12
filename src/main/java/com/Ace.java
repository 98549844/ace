package com;

import com.google.gson.Gson;
import net.sf.jasperreports.engine.util.JsonUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());


  //  https://www.jianshu.com/p/1d1d469faf7f
    public static void main(String[] args) {


        List<TreeNode> treeNodeList = new ArrayList<>();
        TreeNode treeNode1 = new TreeNode("1", "0", "首页");
        TreeNode treeNode2 = new TreeNode("2", "0", "订单");
        TreeNode treeNode3 = new TreeNode("3", "1", "预约");
        TreeNode treeNode4 = new TreeNode("4", "2", "捐献");
        TreeNode treeNode5 = new TreeNode("5", "4", "我的订单");
        TreeNode treeNode6 = new TreeNode("6", "5", "个人中心");
        TreeNode treeNode7 = new TreeNode("7", "6", "个人中心2");
        TreeNode treeNode8 = new TreeNode("8", "99", "个人中心3");
        treeNodeList.add(treeNode1);
        treeNodeList.add(treeNode6);
        treeNodeList.add(treeNode5);
        treeNodeList.add(treeNode3);
        treeNodeList.add(treeNode4);
        treeNodeList.add(treeNode2);
        treeNodeList.add(treeNode7);
        treeNodeList.add(treeNode8);


        Ace ace = new Ace();
        Gson g = new Gson();
        System.out.print(g.toJson(ace.treeMenu(treeNodeList)));

    }


    private void setTreeMap(Map<String, Object> mapArr, TreeNode treeNode) {
        mapArr.put("id", treeNode.getId());
        mapArr.put("name", treeNode.getName());
        mapArr.put("parentId", treeNode.getParentId());
        List<?> childrens = menuChild(treeNode.getId());
        if (childrens.size() > 0) {
            mapArr.put("hasChild", true);
        } else {
            mapArr.put("hasChildren", false);
        }
        mapArr.put("childrens", menuChild(treeNode.getId()));
    }


    public List<?> menuChild(String id) {
        List<Object> lists = new ArrayList<Object>();
        for (TreeNode a : menuCommon) {
            Map<String, Object> childArray = new LinkedHashMap<String, Object>();
            if (a.getParentId().equals(id)) {
                setTreeMap(childArray, a);
                lists.add(childArray);
            }
        }
        return lists;
    }

    public static Map<String, Object> mapArray = new LinkedHashMap<String, Object>();

    public List<TreeNode> menuCommon;
    public List<Object> list = new ArrayList<Object>();

    public List<Object> treeMenu(List<TreeNode> menu) {
        this.menuCommon = menu;
        for (TreeNode treeNode : menu) {
            Map<String, Object> mapArr = new LinkedHashMap<String, Object>();
            if (treeNode.getParentId().equals("0")) {
                setTreeMap(mapArr, treeNode);
                list.add(mapArr);
            }
        }
        return list;
    }

}
