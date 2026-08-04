package com.example.practice;

public class IdCard {
	public static void main(String[] args) {
		IdCard idCard = new IdCard();
		boolean isVaildedId = idCard.idCardVerification("A123456789");
		System.out.println(isVaildedId);
		boolean isVaildedId2 = idCard.idCardVerification("A123456780");
		System.out.println(isVaildedId2);
		String producedId = idCard.idCardProduce();
		System.out.println(producedId);
	}

	public boolean idCardVerification(String id) {
		// 檢查長度與結構
		if (id.length() != 10) {// 長度必須是 10
			return false;
		}
		int secNum = Character.getNumericValue(id.charAt(1));
		if (secNum != 1 && secNum != 2) {// 性別碼(第二碼)檢查，只能是 1 或 2
			return false;
		}
		// 轉換首碼(英文轉換成數字)
		int locationCodeInt = locationCodeToInt(id.charAt(0));
		String convertedId = String.valueOf(locationCodeInt) + id.substring(1);
		// 乘上加權並加總
		int[] weightArr = { 1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1 };
		int checkSum = 0;
		for (int i = 0; i < weightArr.length; i++) {
			int weight = weightArr[i];
			int idCode = Character.getNumericValue(convertedId.charAt(i));
			checkSum += idCode * weight;
		}
		// 總和除以 10 取餘數，餘數為 0 則為合規身分證字號
		if (checkSum % 10 == 0) {
			return true;
		}
		return false;
	}

	public String idCardProduce() {
		StringBuilder idStringBuilder = new StringBuilder();
		// 產生隨機首碼(地區碼)
		int rand = (int) (Math.random() * 26 + 1);
		char locationCode = (char) (rand + 'A');
		idStringBuilder.append(locationCode);
		// 隨機產生性別碼 (1或2)
		idStringBuilder.append((int) (Math.random() * 1) + 1);
		// 隨機產生七個數字(留一個數字來讓身分證有效)
		for (int i = 0; i < 7; i++) {
			int randNum = (int) (Math.random() * 9);
			idStringBuilder.append(randNum);
		}
		// 透過產生正確的最後一碼讓身分證字號有效
		int checkSum = 0;
		int locationCodeInt = locationCodeToInt(locationCode);
		String convertedId = String.valueOf(locationCodeInt) + idStringBuilder.substring(1);
		int[] weightArr = { 1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1 };
		for (int i = 0; i < convertedId.length(); i++) {
			int weight = weightArr[i];
			int idCode = Character.getNumericValue(convertedId.charAt(i));// 同 convertedId.charAt(i) - '0' (利用 Unicode
																			// 相減)
			checkSum += idCode * weight;
		}
		int lastCode = 10 - checkSum % 10; // 補上正確的檢查碼來讓 checkSum % 10 == 0(最後一碼權重是 1)
		idStringBuilder.append(lastCode);
		return idStringBuilder.toString();
	}

	// 把地區碼轉成對應的數值
	private int locationCodeToInt(char locationCode) {
		String convertLocationCode = "ABCDEFGHJKLMNPQRSTUVXYWZIO"; // 地區碼排序
		return convertLocationCode.indexOf(locationCode) + 10;// 地區碼轉換從 10 開始
	}
}
