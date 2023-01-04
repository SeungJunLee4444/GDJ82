<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- # Core Library 지시 : taglib 지시어 사용 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- ex07 : jstl
	- 정의 : jstl과 el의 조합을 말한다
	- 형태 : ex) <%= student %> 를 ${student}로 바꿔서 사용한다
	- 기능 : jsp 페이지 내에서 자바 코드를 사용하지않고, 태그로 자바같은 기능을 구현할 수 있다
	- 조건 : 코어 라이브러리.jar 필요
	- 종류
	(1) c:set 				: 속성만들기, 일종의 변수화
	(2) c:if				: if문
	(3) c:choose , c:when 	:if-else문
	(4) c:forEach 			: 반복문
	 --%>

	<%--
		1. c:set 속성(Attribute) 만들기 태그
		- 형태 : <c:set var="속성명" value="값" scope="영역">
		- 영역 : page(디폴트), request, session, application (el과 비슷?)
		(el이 pageContext, c:set는 page(디폴트)
	--%>
	
	<%--
		* pageContext(자바)와 page(jsp)는 동일한 범위를 지닌다
		pageContext.setAttribute("name", "민경태");
		= <c:set var="name" value="가나다" scope="page" />
	--%>
	<c:set var="name" value="가나다" scope="page" />
	<c:set var="age" value="44" scope="page" />
	<c:set var="isAdult" value="${age >= 20}" scope="page" />		<%-- => 조건식도 사용가능 --%>
	<c:set var="height" value="180.5" scope="page" />
	<c:set var="weight" value="73.5" scope="page" />
	<c:set var="bmi" value="${weight div (height * height * 0.0001)}" scope="page" />
	<c:set var="health" value="${bmi ge 25 ? '비만' : '정상'}" scope="page" />
	
	<%-- # el 표현언어를 이용해 조회가능 --%>
	<h1>이름 : ${name}</h1>
	<h1>나이 : ${age}</h1>
	<h1>${isAdult ? '성인' : '미성년자'}</h1>
	<h1>bmi : ${bmi}</h1>
	<h1>건강상태 : ${health}</h1>

</body>
</html>