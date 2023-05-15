package com.soccer.league;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTest {

	public static void main(String[] args) throws ParseException {
		String strDate = "2023-05-08T00:30:00+09:00";
		
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat newDtFormat = new SimpleDateFormat("MM.dd. (E)");
		SimpleDateFormat newDtFormat2 = new SimpleDateFormat("HH:mm");
		//String 타입을 Date 타입으로 변환
		Date formatDate = dtFormat.parse(strDate);
		//Date타입의 변수를 새롭게 지정한 포맷으로 변환
		String returnDate = newDtFormat.format(formatDate);
		String returnDate2 = newDtFormat2.format(formatDate);
		
		System.out.println(returnDate);
		System.out.println(returnDate2);
	}
}
