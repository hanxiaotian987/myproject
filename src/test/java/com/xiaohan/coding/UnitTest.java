package com.xiaohan.coding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.Test;

public class UnitTest {
	
	@Test
	public void test() {
		List<String> list = new ArrayList<String>();
		list.add("0");
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		String text=null;
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			text = it.next();
			System.out.println(text);
		}
		System.out.println(list);
	}
}
