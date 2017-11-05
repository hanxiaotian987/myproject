package com.xiaohan.coding;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( String[] args )
	{
		//System.out.println( "Hello World!" );
		int[] persons = new int[10];
		for(int i=0; i<persons.length; i++) {
			persons[i] = i + 0;
		}
		System.out.println("全体员工卡号:" + Arrays.toString(persons));
		int[] array = getPersons(persons,9);
		System.out.println("不重复的卡号:" + Arrays.toString(array));
		HashMap<String,Object> map = new HashMap<>();
		map.put("key","value");
	}

	private static int[] getPersons(int[] persons,int needs) {
		//int[]转list
		List<Integer> list = new ArrayList<Integer>();
		for(int person : persons) {
			list.add(person);
		}
		int[] result = new int[needs];
		Random random = new Random();
		for(int i=0; i<needs; i++) {
			result[i] = list.remove(random.nextInt(list.size()));
		}
		return result;
	}
}
