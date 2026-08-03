package com.example.practice;

import java.util.Scanner;

// 猜數字遊戲 0A0B
public class GuessNumber {
	public static void main(String[] args) {
		// 產生正解
		StringBuffer answerSB = new StringBuffer();
		while (answerSB.length() < 4) {
			int ran = randomInt(0, 9);
			if (answerSB.indexOf(String.valueOf(ran)) == -1) {
				answerSB.append(ran);
			}
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
			if (guess.length() != 4) {
				System.out.println("請輸入四位數字");
				continue;
			}
			// 判斷 ?A?B
			int a = 0;
			int b = 0;

			for (int i = 0; i < 4; i++) {
				char guessNum = guess.charAt(i);
				char ansNum = answer.charAt(i);

				// 數字不包含在 answer 裡面
				if (answer.indexOf(String.valueOf(guessNum)) == -1) {
					continue;
				}

				// 位置與數字都正確
				if (guessNum == ansNum) {
					a++;
					continue;
				}

				// 數字包含在 answer 裡面但是位置不對
				b++;
			}
			System.out.println(a + "A" + b + "B");

			System.out.println("你猜的數字是: " + guess);
		}

		System.out.println("你答對了!");

		scanner.close();
	}

	// 產生 含 min 到 max 兩端的亂數
	private static int randomInt(int min, int max) {
		return (int) (Math.random() * (max - min + 1) + min);
	}
}
