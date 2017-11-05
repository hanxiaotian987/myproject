package com.xiaohan.coding.algorithm;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName:  BubbleSort   
 * @Description:冒泡排序 
 * @author: hanpanpan
 * @date:   2017年10月22日 下午11:23:33   
 * 
 *
 */
public class BubbleSort {
	public static void main(String[] args) {
		//可能产生重复数字
		int[] a = random();
        // 产生不重复数字
        // int[] a = random_unique();
		//int[] a = random_unique4();
        System.out.println("排序前：" + Arrays.toString(a));
        System.out.println("--------排序开始------------");
        sort(a);
        System.out.println("--------排序结束------------");
        System.out.println("排序后：" + Arrays.toString(a));
    }

    private static void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            boolean flag = false;// 无交换标记
            for (int j = a.length - 1; j > i; j--) {
                if (a[j - 1] > a[j]) {
                    int c = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = c;
                    flag = true;// 有交换
                }
            }
            if (!flag) {// 没有交换
                break;// 排序完成，中断循环
            }
            System.out.println(Arrays.toString(a));
        }
    }

    /** 产生随机数字,可能有重复 */
    private static int[] random() {
        /*
        * 1.产生数组随机长度赋给len 范围是[5,11) 2.新建int[]数组赋给a，长度是len 3.循环i从0到<len
        * 4.在a数组的i位置， 放入一个100以内的随机数 5.返回数组a
        */
        int len = new Random().nextInt(6) + 5;
        int[] a = new int[len];
        for (int i = 0; i < len; i++) {
            a[i] = new Random().nextInt(100);
        }
        return a;
    }

    /** 产生不重复数字数组方法1： */
    private static int[] random_unique() {
        int len = new Random().nextInt(6) + 5;
        int[] a = new int[len];
        outer: for (int i = 0; i < len; i++) {
            int c = new Random().nextInt(100);// 随机数范围：[0,100)
            for (int j = 0; j < i; j++) {
                if (a[j] == c) {
                    i--;
                    continue outer;
                }
            }
            a[i] = c;
        }
        return a;
    }

    /** 产生不重复数字数组方法2： */
    private static int[] random_unique2() {
        int n = 10; // 随机生成数组的数值范围
        int[] num = new int[n];
        for (int i = 0; i < num.length; i++)
            num[i] = i + 1;
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int r = (int) (Math.random() * n);
            arr[i] = num[r];
            num[r] = num[n - 1];
            n--;
        }
        return arr;
        // 输出显示生成的arr数组
        /*
        * for(int i=0;i <arr.length;i++) System.out.print(arr[i]+"  ");
        */
    }

    /** 产生不重复数字数组方法3： */
    private static int[] random_unique3() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 10) {// set集合存储10个数字
            set.add(new Random().nextInt(100));
        }
        // System.out.println("HashSet集合内容："+set);//显示获取的不重复数字set集合内容,存储于HashMap的键位置;
        int[] arr = new int[set.size()];
        int index = 0;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            arr[index] = it.next();
            index++;
        }
        // System.out.println("获得的不重复数字数组："+Arrays.toString(arr));
        return arr;
    }

    /** 产生不重复数字数组方法4(已排序)： */
    private static int[] random_unique4() {
        Set<Integer> set = new TreeSet<>();
        while (set.size() < 10) {
            set.add(new Random().nextInt(100));
        }
        // System.out.println(set);
        int[] arr = new int[set.size()];
        int index = 0;
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            arr[index] = it.next();
            index++;
        }
        System.out.println("通过TreeSet获得的不重复数字数组：" + Arrays.toString(arr));
        return arr;
    }
}
