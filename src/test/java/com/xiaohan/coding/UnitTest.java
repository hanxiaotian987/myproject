package com.xiaohan.coding;

import java.util.HashMap;
import java.util.Hashtable;

import org.junit.Test;

public class UnitTest {
	
	@Test
	public void test() {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(null,null);
		map.put("a", "a");
		map.put("b", "b");
		map.put("c", "c");
		map.put("d", "d");
		map.put("e", "e");
		System.out.println(map);
		Hashtable<String,Object> ht = new Hashtable<String,Object>();
		ht.put("", "");
		ht.put("a", "a");
		ht.put("b", "b");
		ht.put("c", "c");
		ht.put("d", "d");
		ht.put("e", "e");
		System.out.println(ht);
	}
}
