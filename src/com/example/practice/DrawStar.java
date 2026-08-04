package com.example.practice;

public class DrawStar {
	public static void main(String[] args) {
		var drawStar = new DrawStar();
		System.out.println("(1)");
		drawStar.draw1(5);
		System.out.println("(2)");
		drawStar.draw2(5);
		System.out.println("(3)");
		drawStar.draw3(5);
		System.out.println("(4)");
		drawStar.draw4(5);
		System.out.println("(5)");
		drawStar.draw5(5);
		System.out.println("(6)");
		drawStar.draw6(5);
	}

	private void draw1(int num) {
		// rows
		for (int i = 1; i <= num; i++) {
			// columns
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private void draw2(int num) {
		// rows
		for (int i = 1; i <= num; i++) {
			// columns
			// 填空格
			for (int s = 1; s <= num - i; s++) {
				System.out.print(" ");
			}
			// 畫星星
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private void draw3(int num) {
		// rows
		for (int i = 1; i <= num; i++) {
			// columns
			for (int j = num; j >= i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private void draw4(int num) {
		// rows
		for (int i = 1; i <= num; i++) {
			// columns
			// 填空格
			for (int s = 1; s < i; s++) {
				System.out.print(" ");
			}
			// 畫星星
			for (int j = num; j >= i; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

	private void draw5(int num) {
		// rows
		for (int i = 1; i <= num; i++) {
			// space
			for (int s = 0; s < num - i; s++) {
				System.out.print(" ");
			}
			// star
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
				if (j != i) { // 用空格打開星星之間的間距，最後一顆星星不需要空格
					System.out.print(" ");
				}
			}
			// 換行
			System.out.println();
		}
	}

	private void draw6(int num) {
		// rows, 上半部
		for (int i = 1; i <= num; i++) {
			// space
			for (int s = 0; s < num - i; s++) {
				System.out.print(" ");
			}
			// star
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
				if (j != i) {
					System.out.print(" ");
				}
			}
			// 換行
			System.out.println();
		}
		// rows, 下半部，與上半部差不多，只是第一個 for 裡面的 i 的起訖顛倒，並且從 num - 1 開始
		for (int i = num - 1; i >= 1; i--) {
			// space
			for (int s = 0; s < num - i; s++) {
				System.out.print(" ");
			}
			// star
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
				if (j != i) {
					System.out.print(" ");
				}
			}
			// 換行
			System.out.println();
		}
	}
}
