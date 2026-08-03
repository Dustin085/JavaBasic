package com.example.practice;

// 九九乘法表
public class TimesTable {
	public static void main(String[] args) {
		// for no.1 i = 左邊的數字
		for (int i = 1; i <= 9; i++) {
			// for no.2 j = 右邊的數字
			for (int j = 1; j <= 9; j++) {
				System.out.println(String.valueOf(i) + " * " + String.valueOf(j) + " = " + String.valueOf(i * j));
			}
		}
	}
}
