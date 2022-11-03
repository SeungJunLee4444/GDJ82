<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- [EL : 표현언어] --%>
	<%-- - 용도 : 자바스크립트에서 변수화 --%>
	<%-- - 종류
		(1) ${} : 변수만 담는다
		(2) #{} : 변수에 ''를 포함해서 담는다 => 사용시 문자열로 변환됨
		
		- el 사용이 가능한 4개의 영역
		   (1) pageContext			: JSP 한 페이지를 영역으로 유지
		   (2) request				: 요청시에 한해서 유지
		   (3) session				: 브라우저가 꺼지거나, 로그아웃전까지 유지
		   (4) application			: 서버가 사라질때까지 유지
	--%>
	 
	 <%-- - el 연산자
	 		EL 연산자
			(1) 산술 연산자 : +  -  *  /(div)  %(mod)
			(2) 관계 연산자 : <(lt)  <=(le)  >(gt)  >=(ge)  ==(eq)  !=(ne)
			(3) 논리 연산자 : &&(and)  ||(or)  !(not)
			(4) 조건 연산자 : (조건식) ? 값1 : 값2 
	--%>

	<%
		pageContext.setAttribute("a", 7);
		pageContext.setAttribute("b", 2);
	%>
	
	<div>${a + b}</div>
	<div>${a - b}</div>
	<div>${a * b}</div>
	<div>${a / b}, ${a div b}</div>
	<div>${a % b}, ${a mod b}</div>
	
	<%-- / = div, % mod --%>
	<%-- lt 작다, gt 크다, le 작거나같다, gt 크거나 같다, eq 같다, ne 다르다 --%>
	<%-- and = &&, or = || --%>
	<%-- ! = not --%>
	
	<div>${a <  b}, ${a lt b}</div>
	<div>${a <= b}, ${a le b}</div>
	<div>${a >  b}, ${a gt b}</div>
	<div>${a >= b}, ${a ge b}</div>
	<div>${a == b}, ${a eq b}</div>
	<div>${a != b}, ${a ne b}</div>
	
	<div>${a == 7 && b == 2}, ${a eq 7 and b eq 2}</div>
	<div>${a == 7 || b == 2}, ${a eq 7 or  b eq 2}</div>
	<div>${!(a == 7)}, ${not (a eq 7)}</div>
	
	<div>${a == 7 ? "a는 7이다" : 'a는 7이 아니다'}</div>	

</body>
</html>