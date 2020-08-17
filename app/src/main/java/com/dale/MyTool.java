package com.dale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyTool {


	public static void main(String[] args) {

		int x_现在公司总额 = 803494;//(这里)
		int x_现在公司打回家 = 485279;//这里
		int y_自己 = getMy();//这里方法里面
//		int y_自己 = getMy() + 12800 + 7100;//这里
		int y_父母额外 = 13320;

		int x_现在公司开销 = 6300;//(2800 + 3500)
		int y_上家总额 = 270000;
		int y_上家打回总额 = 195100;
		int x_累计发 =y_上家总额 + x_现在公司总额 - x_现在公司开销;
		int x_打回家 = y_上家打回总额 + x_现在公司打回家;
		int y_父母累计 = 40900 + y_父母额外;
		int y_还账 = x_现在公司总额 - x_现在公司开销 - x_现在公司打回家 + y_上家总额 - y_上家打回总额 - y_父母累计 - y_自己;

		System.out.println("现在公司实发：" + (x_现在公司总额 - x_现在公司开销));
		System.out.println("现在公司打回家：" + ( x_打回家 - y_上家打回总额 ));
		String nowText = "现在公司还账：" + ((x_现在公司总额 - x_现在公司开销 - y_父母累计 - y_自己 - (x_打回家 - y_上家打回总额)));
		System.out.println(nowText);
		System.out.println("*********************");

		System.out.println("全部累积发：" + x_累计发);
		System.out.println("实际打回家：" + x_打回家);
		System.out.println("抽取金额：" + (x_累计发 - x_打回家));
		System.out.println("自己库：" + (y_自己));

		System.out.println("*********************");

		System.out.println("还账：" + y_还账);
		System.out.println("上家公司还账：" + (y_上家总额 -y_上家打回总额));
		System.out.println(nowText);

		System.out.println("*********************");
		System.out.println("父母累计：" + y_父母累计 + "  存外：" + y_父母额外 + "  父母打回家:" + (y_父母累计 - y_父母额外));


		System.out.println("平均每月：" + (x_打回家 / (getCount())));
		System.out.println("实际平均每月：" + (x_累计发 / (getCount())));
		System.out.println("现在公司总额:" + x_现在公司总额);
		System.out.println("上家公司总额:" + y_上家总额);

	}




	private static int getCount() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
		list.add(11);
		list.add(12);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		System.out.println("累计发了" + list.size() +"月工资");
		return list.size();
	}

	private static int getMy() {
		int all = 0;
		Map<String,Integer> myMaps = new HashMap<>();
		myMaps.put("6月第一次自己", 2000);
		myMaps.put("6月红分红", 4620);
		myMaps.put("7月工资", 7388);
		myMaps.put("7月分红", 7700);
		myMaps.put("8月工资", 7100);
		Set<String> set = myMaps.keySet();
		for(String s : set) {
			all = all + myMaps.get(s);
		}
		return all;
	}


}
