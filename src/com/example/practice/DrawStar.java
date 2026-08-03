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
				if (j != i) {
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
		// rows, 下半部
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
