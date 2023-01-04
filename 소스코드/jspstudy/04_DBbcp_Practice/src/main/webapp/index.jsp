<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%-- # 절대경로의 변수화 
- 설명 : 현재 디렉토리인 contextPath를 el로 가져올 수 있다

# 컨텍스트 패스의 변수화 방법 두가지
(1) jsp 스크립트:  <%=request.getContextPath()%>
(2) 표현식(el)  :  <c:set var="contextPath" value="${pageContext.request.contextPath}" />
=> <%=request.getContextPath()%> = ${pageContext.request.contextPath} 둘은 값이 같다
- 형태가 다른 이유 : request는 el의 내장객체가 아니다

* 결론 : 어자피 jsp 스크립트방식은 쓰지않는다
=> el표현언어의 방식인 ${pageContext.request.contextPath} el방식만 사용할 것!

------------------------------------------------------
* el의 내장객체
(1) pageContext : jsp 페이지 객체를 참고할때
(2) param       : 요청 파라미터의 값을 단일로 반환

* el의 영역(객체의 유효기간)
(1) page        : 하나의 jsp페이지에서만
(2) request     : 하나의 요청을 처리하는 동안
(3) session	    : 브라우저가 켜져있는동안
(4) application : 하나의 웹이 유지되는 동안
------------------------------------------------------
 --%>

<%-- # 컨텍스트 패스 el 변수처리 --%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   
<!DOCTYPE html>
<%-- --%>
<html> 
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
	<%-- [4장 : dbcp를 이용한 게시판 생성] --%>
	
	<%-- [시작화면] : index.jsp
		 - 용도 : 게시판 목록인 list.jsp로 이동
		 - 요청 : a태그를 이용한 list.do 서비스 요청, 단순이동
		 
		 * 추가사항 : 경로작성시 컨텍스트 패스를 변수화
		 - 이유 : 컨텍스트패스(프로젝트명)이 변경되는 경우 방지
		 - 조건 : 코어라이브러리 jar 파일 lib 폴더에 가져오기
		 -------------------------------------------------------
	--%>
	
	<%-- # 이동링크 --%>
	<a href="${contextPath}/board/list.do">게시판</a>
	
	<%-- - 경로 : 컨텍스트패스(webapp)/jsp폴더(board)/요청
	=> 요청이 서블릿 컨트롤러에서 처리된다
	 --%>

</body>
</html>