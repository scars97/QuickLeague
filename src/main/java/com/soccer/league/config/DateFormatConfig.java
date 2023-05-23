package com.soccer.league.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DateFormatConfig {

	// 날짜 포맷
	public List<String> dateFormat(String date) throws ParseException {

		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat newDtFormat = new SimpleDateFormat("MM.dd. (E)");
		SimpleDateFormat newTmFormat = new SimpleDateFormat("HH:mm");
		// String 타입을 Date 타입으로 변환
		Date formatDate = dtFormat.parse(date);
		// Date타입의 변수를 새롭게 지정한 포맷으로 변환
		String returnDate = newDtFormat.format(formatDate);
		String returnTime = newTmFormat.format(formatDate);

		List<String> returnList = new ArrayList<>();
		returnList.add(returnDate);
		returnList.add(returnTime);

		return returnList;
	}
}
