package com.example.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlusDate {
	public static void main(String[] args) {
		PlusDate plusDate = new PlusDate();
		Date date = plusDate.ComputationDate("2026-08-03", 4);
		System.out.println(date);

	}

	public Date ComputationDate(String dateStr, int dateNum) {
		// 將字串解析為日期
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = simpleDateFormat.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 利用 Calendar 修改日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dateNum);
		Date newDate = calendar.getTime();
		return newDate;
	}
}
