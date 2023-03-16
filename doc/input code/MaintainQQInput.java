package com;

import com.ace.util.DataTypeUtil;
import com.ace.util.FileUtil;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MaintainQQInput {

    static String filePath = "D:\\SVN\\spring-boot\\doc\\input code";
    static String fileName = "\\QQINPUT.ini";
    static String fullPath = filePath + fileName;

    public static void main(String[] args) throws IOException {

        MaintainQQInput qqInput = new MaintainQQInput();
        Map<String, Object> map = qqInput.read();
        //读qq文件入list
        List<StringBuilder> allList = (List<StringBuilder>) map.get("list");

        //looping处理
        List<StringBuilder> resultList = new LinkedList<>();
        for (int i = 0; i < allList.size(); i++) {
            String current = allList.get(i).toString();
            if (i != 0) {
                String before = new String(allList.get(i - 1)).toString();
                String[] beforeSplit1 = before.split("=");
                String[] beforeSplit2 = beforeSplit1[1].split(",");

                String[] currentSplit1 = current.split("=");
                String[] currentSplit2 = currentSplit1[1].split(",");

                before = beforeSplit1[0];
                //    System.out.println(current.contains(before));
                if (current.contains(before) && (currentSplit1[0].length() == beforeSplit1[0].length())) {
                    Integer beforeNum = DataTypeUtil.stringToInteger(beforeSplit2[0]);

                    Integer currentNum = DataTypeUtil.stringToInteger(currentSplit2[0]);
                    if (currentNum == beforeNum) {
                        currentNum = currentNum + 1;
                        String newCurrent = currentSplit1[0] + "=" + currentNum.toString() + "," + currentSplit2[1];
                        resultList.add(new StringBuilder(newCurrent + System.getProperty("line.separator")));
                    } else {
                        //add same row but number is difference
                        resultList.add(new StringBuilder(current + System.getProperty("line.separator")));
                    }
                } else {
                    //add difference row
                    resultList.add(new StringBuilder(current + System.getProperty("line.separator")));
                }
            } else {
                //add first row
                resultList.add(new StringBuilder(current + System.getProperty("line.separator")));
            }
        }
        //写入并输出新的文件
        qqInput.write(filePath, "\\newQQInput.ini", resultList);
    }

    public Map<String, Object> read() throws IOException {
        FileUtil fileUtil = new FileUtil();
        Map<String, Object> map = fileUtil.read(fullPath);
        return map;
    }

    public void write(String filePath, String fileName, Object obj) {
        FileUtil fileUtil = new FileUtil();
        fileUtil.write(filePath, fileName, obj);
    }


}
