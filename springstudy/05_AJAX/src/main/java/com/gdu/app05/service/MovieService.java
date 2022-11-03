package com.gdu.app05.service;

public interface MovieService {
	
	// # 영화를 복수개 전달해줄 서비스 메서드 
	public String getBoxOffice(String targetDt);

	// * 필수 파라미터 : key(문자열), targetDt(문자열)
	
	// * String : 출력되는 결과의 타입
}
