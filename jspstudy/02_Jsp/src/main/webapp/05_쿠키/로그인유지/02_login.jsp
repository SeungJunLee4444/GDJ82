<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 요청 파라미터
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	String chkKeepLogin = request.getParameter("chk_keep_login");
	
	// id="min", pwd="1234"이면 로그인 성공					// => equals메서드를 이용해 로그인성공조건 생성(임의)
	if(id.equals("min") && pwd.equals("1234")){
		
		// & 쿠키 생성
		// 1) 로그인 유지 체크
		// 2) checkbox, select는 선택시 파라미터의 value값이 생성 => null이 아님
		
		// - 조건 : 로그인 유지하기를 체크했다면 로그인 정보를 쿠키에 저장(브라우저를 닫아도 정보를 유지하기 위해서)
		// & session은 브라우저가 닫히면 저장된 값이 사라지지만 쿠키는 정해진 시간동안 유지된다
		
		// & 쿠키는 setMaxAge()메서드
		// 시간은 초단위로 계산된다
		// ex) cookie1.setMaxAge(60 * 60 * 24 * 7);		답 : 7일
		
		if(chkKeepLogin != null){
			Cookie cookie1 = new Cookie("id", id);
			cookie1.setMaxAge(60 * 60 * 24 * 7);		// 7일
			response.addCookie(cookie1);
			Cookie cookie2 = new Cookie("pwd", pwd);
			cookie2.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie2);
		}
		// 로그인 유지하기를 체크하지 않았다면 일반 로그인 처리(로그인 정보를 세션에 저장)
		else {
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
		}
		
	}
	
	// 로그인 폼으로 가기
	response.sendRedirect("01_form.jsp");
%>