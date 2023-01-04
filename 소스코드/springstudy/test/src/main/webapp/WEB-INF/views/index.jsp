<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>	
<!-- jar 파일 없이도 maven이 알아서 내려받아서 사용가능해짐  -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- # 웰컴 jsp파일 --%>
	<%-- ! 스프링은 web.xml에 웰컴파일리스트가 작성되있지않다
	=> /(컨텍스트패스)를 요청하면 index.jsp를 열어주는 코드가 필요하다
	 --%>
	
	<a href="${contextPath}/animal">동물보러가기</a>

</body>
</html>