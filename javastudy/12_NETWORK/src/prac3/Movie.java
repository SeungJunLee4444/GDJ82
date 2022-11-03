package prac3;

import lombok.Builder;
import lombok.ToString;

@Builder	// 빌터패턴
@ToString	// 정보 간단히 나오도록

public class Movie {
	
	private String movieCd;	// 영화코드
	private String movieNm;	// 영화명
	private String openDt;	// 개봉일
	private String salesAcc;// 누적매출액
	private String audiAcc;	// 누적관객수
	
	

}
