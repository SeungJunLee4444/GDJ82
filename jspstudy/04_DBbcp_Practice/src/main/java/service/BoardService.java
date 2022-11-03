package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface BoardService {
	
	// [서비스 인터페이스] : 모든 서비스들의 인터페이스 생성
	
	// # 모든 서비스들의 메서드 : 예외처리는 서비스 각각에서 함(throws exception이 없는 이유다)
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);

}
