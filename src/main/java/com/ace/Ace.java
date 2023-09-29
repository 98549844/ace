package com.ace;

import java.util.*;

public class Ace {


    public static void main(String[] args) {
        List<Node> nodes = generateNodes();

        Map<Integer, Node> nodeMap = new HashMap<>();
        Node root = null;

        // 构建节点映射表
        for (Node node : nodes) {
            nodeMap.put(node.getId(), node);
        }

        // 构建树状结构
        for (Node node : nodes) {
            int parentId = node.getParentId();
            if (parentId == 0) {
                root = node;
            } else {
                Node parent = nodeMap.get(parentId);
                if (parent != null) {
                    parent.addChild(node);
                }
            }
        }

        // 打印树状结构
        if (root != null) {
            printTree(root, 0);
        }
    }

    private static List<Node> generateNodes() {
        List<Node> nodes = new ArrayList<>();

        // 添加节点数据
        nodes.add(new Node(1, 0, "Node 1"));
        nodes.add(new Node(2, 1, "Node 2"));
        nodes.add(new Node(3, 1, "Node 3"));
        nodes.add(new Node(4, 2, "Node 4"));
        nodes.add(new Node(5, 2, "Node 5"));
        nodes.add(new Node(6, 3, "Node 6"));

        return nodes;
    }

    private static void printTree(Node node, int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }

        System.out.println(indent + node.getName());

        List<Node> children = node.getChildren();
        for (Node child : children) {
            printTree(child, level + 1);
        }
    }

    static class Node {
        private int id;
        private int parentId;
        private String name;
        private List<Node> children;

        public Node(int id, int parentId, String name) {
            this.id = id;
            this.parentId = parentId;
            this.name = name;
            this.children = new ArrayList<>();
        }

        public int getId() {
            return id;
        }

        public int getParentId() {
            return parentId;
        }

        public String getName() {
            return name;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void addChild(Node child) {
            children.add(child);
        }
    }
}




