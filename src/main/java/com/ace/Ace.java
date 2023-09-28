package com.ace;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ace {
    public static void main(String[] args) {
        String rootPath = "/Users/garlam/ace/users/garlam/";
        File rootFolder = new File(rootPath);
        List<String> fileList = new ArrayList<>();
        listFilesAndFolders(rootFolder, 0, fileList);

        // 打印结果
        for (String entry : fileList) {
            System.out.println(entry);
        }
    }

    public static void listFilesAndFolders(File folder, int level, List<String> fileList) {
        if (folder.isDirectory()) {
            // 将文件夹名称添加到列表
            fileList.add(getIndent(level) + "[Folder] " + folder.getName());

            // 获取文件夹中的所有文件和子文件夹
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    listFilesAndFolders(file, level + 1, fileList); // 递归调用
                }
            }
        } else {
            // 将文件名称添加到列表
            fileList.add(getIndent(level) + "[File] " + folder.getName());
        }
    }

    public static String getIndent(int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("\t"); // 使用制表符缩进
        }
        return indent.toString();
    }
}


