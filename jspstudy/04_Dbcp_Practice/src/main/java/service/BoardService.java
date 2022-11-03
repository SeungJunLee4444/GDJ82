package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;

public interface BoardService {
	// 모든 Service에서 직접 예외 처리함(throws Exception이 없는 이유)
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response);
}
