package com.xiaohan.coding.algorithm.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 
 * @ClassName:  RandomUniqueArray   
 * @Description:在指定数组中随机获取指定不重复数值
 * 场景：现有一组员工编号,进行抽奖活动,要求每个员工只能中一次将,即抽取指定不重复获奖人员。
 * @author: hanpanpan
 * @date:   2017年10月26日 下午11:40:34   
 * 
 *
 */
public class RandomUniqueArray {
	
	/**
	 * 主函数.
	 *
	 * @param args the arguments
	 */
	public static void main( String[] args )
	{
		//准备一组员工编号
		int[] persons = new int[500];
		for(int i=0; i<persons.length; i++) {
			persons[i] = i + 100;
		}
		System.out.println("全体员工编号:" + Arrays.toString(persons));
		//进行抽奖
		int[] array = getPersons(persons,499);
		//公布中奖人员信息
		System.out.println("不重复的编号:" + Arrays.toString(array));
	}

	
	/**
	 * 在指定员工编号数组中，随机获取指定数量的中奖人编号
	 * @param persons 全体员工编号数组
	 * @param needs 需要中奖的人数
	 * @return persons 中奖人员编号数组
	 */
	private static int[] getPersons(int[] persons,int needs) {
		//int[]转list
		List<Integer> list = new ArrayList<Integer>();
		for(int person : persons) {
			list.add(person);
		}
		//随机获取中奖人员
		int[] result = new int[needs];
		Random random = new Random();
		for(int i=0; i<needs; i++) {
			result[i] = list.remove(random.nextInt(list.size()));
		}
		return result;
	}
}
