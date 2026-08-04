package com.example.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

public class Lotto {
	public static void main(String[] args) {
		Lotto lotto = new Lotto();
		String[] lottoStrArr = lotto.playLotto();
		System.out.println("第一題:");
		System.out.println(Arrays.toString(lottoStrArr));

		System.out.println("第四題:");
		TreeSet<String> treeSet = lotto.playLottoSet();
		System.out.println(treeSet.toString());

		System.out.println("第五題:");
		ArrayList<String> arrayList = lotto.playLottoList(lottoStrArr);
		System.out.println(arrayList);

		System.out.println("第六題:");
		HashMap<Integer, String> hashMap = lotto.playLottoMap(treeSet);
		System.out.println(hashMap.toString());
	}

	// 1. 大樂透要排序、不重複、如果個位數的話前面補0 (01~49, 取6個)
	public String[] playLotto() {
		ArrayList<String> arrayList = new ArrayList<String>();
		// 直到填滿六個數字為止
		while (arrayList.size() < 6) {
			int ranNum = randomInt(1, 49);
			String ranNumStr = String.valueOf(ranNum);
			if (ranNumStr.length() == 1) {
				ranNumStr = "0" + ranNumStr;
			}
			// 已存在 -> 跳過這圈
			if (arrayList.contains(ranNumStr))
				continue;

			arrayList.add(ranNumStr);
		}

		// 排序
		arrayList.sort(Comparator.naturalOrder());

		return arrayList.toArray(new String[0]);

	}

	// 4. 使用TreeSet產生大樂透
	public TreeSet<String> playLottoSet() {
		// TreeSet 預設按照自然順序排列
		TreeSet<String> treeSet = new TreeSet<String>();
		while (treeSet.size() < 6) {
			int ranNum = randomInt(1, 49);
			String ranNumStr = String.valueOf(ranNum);
			if (ranNumStr.length() == 1) {
				ranNumStr = "0" + ranNumStr;
			}
			// 無須判斷是否重複，嘗試往 Set 裡面加入重複的元素時，Set 不會引發錯誤，也不會將重複的元素加入
			treeSet.add(ranNumStr);
		}
		return treeSet;
	}

	// 5.使用第一個題目回傳string陣列，把裡面的資料放入arrayList裡面
	public ArrayList<String> playLottoList(String[] lotto) {
		ArrayList<String> lottoList = new ArrayList<String>();
		for (String num : lotto) {
			lottoList.add(num);
		}
		return lottoList;
	}

	// 6.使用第四個題目回傳TreeSet陣列，把裡面的資料放入HashMap裡面
	public HashMap<Integer, String> playLottoMap(TreeSet<String> lotto) {
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		// TreeSet 自然就有按照順序
		int i = 1;
		for (String str : lotto) {
			map.put(i, str);
			i++;
		}
		return map;
	}

	private int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
}
