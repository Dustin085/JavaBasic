package com.example.practice;

public class IdCard {
	public static void main(String[] args) {
		IdCard idCard = new IdCard();
		System.out.println(idCard.locationCodeToInt("I"));
	}

	public boolean idCardVerification(String id) {
		// 轉換首碼(英文轉換成數字)
		

		// 乘上加權

		// 總和除以 10 取餘數，餘數為 0 則為合規身分證字號
		return true;
	}

	private int locationCodeToInt(String locationCode) {
		String convertLocationCode = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
		return convertLocationCode.indexOf(locationCode) + 10;

	}
}
