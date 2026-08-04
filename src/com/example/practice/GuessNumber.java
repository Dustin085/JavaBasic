package com.example.practice;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

// 猜數字遊戲 0A0B
public class GuessNumber {
	public static void main(String[] args) {
		// 產生正解
		Set<Integer> answerSet = new LinkedHashSet<Integer>(); // LinkedHashSet 會保留加入時的順序
		while(answerSet.size() < 4) {
			answerSet.add(randomInt(0, 9)); // Set 無法放入重複的元素，故不須額外檢查
		}
		StringBuilder answerSB = new StringBuilder();
		for(int i : answerSet) {
			answerSB.append(i);
		}
		String answer = answerSB.toString();
		System.out.println("正解是: " + answer);

		// 使用者猜測
		String guess = "";

		Scanner scanner = new Scanner(System.in);

		// 遊戲主體迴圈
		while (!guess.contentEquals(answer)) {
			System.out.println("猜一個四位數字");
			// 使用者輸入
			guess = scanner.next();
			// TODO 加入更多違法輸入的判斷，擋英文跟重複數字
			if (guess.length() != 4) { // 輸入非四位字串
				System.out.println("請輸入四位數字");
				continue;
			}
			// 判斷 ?A?B
			int a = 0;
			int b = 0;

			// 逐個檢查使用者的猜測與正確答案
			for (int i = 0; i < 4; i++) {
				char guessNum = guess.charAt(i);
				char ansNum = answer.charAt(i);

				// 數字不包含在 answer 裡面，不是 A 也不是 B
				if (answer.indexOf(String.valueOf(guessNum)) == -1) {
					continue;
				}

				// 位置與數字都正確，A 的情況
				if (guessNum == ansNum) {
					a++;
					continue;
				}

				// 數字包含在 answer 裡面但是位置不對，B 的情況
				b++;
			}
			System.out.println("你猜的數字是: " + guess);

			System.out.println(a + "A" + b + "B");
		}

		// 脫離 while 迴圈 => 猜到正解
		System.out.println("你答對了!");

		// scanner 務必關閉
		scanner.close();
	}

	// 產生 含 min 到 max 兩端的亂數
	private static int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
}
