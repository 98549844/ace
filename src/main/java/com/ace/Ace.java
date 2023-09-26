package com.ace;

/**
 * @Classname: Ace
 * @Date: 2023/8/4 下午 12:00
 * @Author: kalam_au
 * @Description:
 */


public class Ace {

    public static void main(String[] args) {
        aaa instance = new aaa();

        bbb bInstance = (bbb) instance; // 将 aaa 对象转换为 bbb 类型
        ccc cInstance = (ccc) bInstance; // 将 bbb 对象转换为 ccc 类型

        cInstance.print(); // 调用 ccc 类中的 print 方法
    }

}

class ccc {
    public void print() {
        System.out.println("ccc's print method");
    }
}

class bbb extends ccc {
    public void print() {
        System.out.println("bbb's print method");
    }
}

class aaa extends bbb {
    public void print() {
        System.out.println("aaa's print method");
    }
}
