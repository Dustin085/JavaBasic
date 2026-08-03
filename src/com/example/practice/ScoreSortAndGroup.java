package com.example.practice;

import java.util.ArrayList;
import java.util.HashMap;

// 產生 20 個亂數，再做分類
public class ScoreSortAndGroup {
	public static void main(String[] args) {
		var nums = new ArrayList<Integer>();
		// 產生亂數
		for (int i = 0; i < 20; i++) {
			nums.add(randomInt(0, 100));
		}
		// map key=分段, value=分數
		var scoreGroup = new HashMap<String, ArrayList<Integer>>();
		// 將 nums 內的分數放進 scoreGroup
		for (int score : nums) {
			String level = getLevel(score);
			// 試著找出符合 level 作為 key 的值，若沒有則用函數計算新值放入 map 裡面
			scoreGroup.computeIfAbsent(level, k -> new ArrayList<Integer>()).add(score);
		}
		System.out.println(scoreGroup);

	}

	// 產生 含 min 到 max 兩端的亂數
	private static int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}

	// 取得某分數的分段
	private static String getLevel(int score) {
		if (score >= 90)
			return "甲";
		if (score >= 80)
			return "乙";
		if (score >= 70)
			return "丙";
		if (score >= 60)
			return "丁";
		return "戊";
	}
}
