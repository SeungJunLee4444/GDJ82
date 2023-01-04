package com.gdu.app14.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;

@Component 
public class MyFileUtil {
	
	// [[ MyFileUtil
	// # 파일명 : UUID값을 사용
	// # 경로명 : 현재 날짜를 디렉터리로 생성해서 사용 
	
	
	
	
	// # 확장자 만들기
	// - 기능 : 파라미터로 전달된 filename의 확장자만 살려서 UUID.확장자 방식으로 반환
	// - 이유 : unique 처리
	public String getFilename(String filename) {
		
		
		// 1. 확장자 예외 처리 : 
		String extension = null;
		if(filename.endsWith("tar.gz")) {
			
			extension = "tar.gz";

		} else {
			
			// 2. 확장자 처리
			// * split 메서드 : 문자열을 나눠 배열로 반환
			String[] arr = filename.split("\\.");	// * 정규식에서 .(마침표) 인식 : \. 또는 [.]
			
			// # 확장자
			extension = arr[arr.length - 1];
		
		}
		
		// 3. uuid 확장자 : -(하이푼) 제거
		return UUID.randomUUID().toString().replaceAll("\\-", "") + "." + extension;

	}
	
	//  Matcher : 대상 문자열의 패턴을 해석하고 주어진 패턴과 일치하는지 판별할 때 주로 사용
	
	// # 오늘 경로
	public String getTodayPath() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String sep = Matcher.quoteReplacement(File.separator);
		return "storage" + sep + year + sep + makeZero(month) + sep + makeZero(day);
	}
	
	// # 어제 경로
	public String getYesterdayPath() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);							// * 오늘 하루 전
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		String sep = Matcher.quoteReplacement(File.separator);		// * Matcher.quoteReplacement : 이거안쓰면 구분자 꺠져서 쓴거임 그냥
		return "storage" + sep + year + makeZero(month) + sep + makeZero(day);
	}
	
	// # 날짜 두자릿수로 맞추기 : 1~9 => 01 ~ 09
	public String makeZero(int n) {
		return (n < 10) ? "0" + n : "" + n;
	}
	
	
}
