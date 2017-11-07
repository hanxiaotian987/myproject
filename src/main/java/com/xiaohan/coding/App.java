package com.xiaohan.coding;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("请输入手机号：");
        Scanner s = new Scanner(System.in);
        String tel = s.nextLine();
        tel = tel.replaceAll("(\\d{3})(\\d{4})(\\d{4})","$1****$3");
        System.out.println(tel);
    }


}
