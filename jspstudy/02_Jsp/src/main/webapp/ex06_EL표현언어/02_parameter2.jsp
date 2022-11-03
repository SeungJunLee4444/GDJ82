<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%--
		# EL의 파라미터 처리
		- param : el의 내장객체
		- 사용법 : ${param.파라미터명}
		- 특징 : 모든 파라미터는 String이기 때문에 크기비교를 수행할 수 없음
		(문자열의 크기비교는 먼저 만들어진 값이 크게 판단된다)
		
		* 파라미터 크기비교
		- 방법 : 두 파라미터의 차이를 구한 뒤 0보다 큰지 여부를 판단
		
		& 개인생각 &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		(1) 파라미터값을 받을때는 대체로 el을 사용한다
		(2) 파라미터값간의 크기비교는 파라미터간 차이를 구한후 0과의 크기비교로 실시해야한다
		&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	--%>

	<%-- & 단순연산 --%>
	<div>${param.a + param.b}</div>			
	<div>${param.a - param.b}</div>
	<div>${param.a * param.b}</div>
	<div>${param.a / param.b}, ${param.a div param.b}</div>
	<div>${param.a % param.b}, ${param.a mod param.b}</div>
	<%-- 결과 : 실행가능 --%>
	
	<%-- & 크기비교 : 그냥 비교해봤는데 경우에 따라 맞기도 다른것 같기도 하다  --%>
	
	<%-- ex) 2와 5의 비교 --%>
	
	<div>${param.a > param.b}</div>
	<%-- 결과 : false --%>
	<div>${param.a < param.b}</div>
	<%-- 결과 : true --%>
	
	
	<%-- & 크기비교 : el 표현언어의 파라미터값은 뺀값을 0과 비교해서 크기비교를 실시한다 --%>
	<%-- & 그냥 이렇게 생각하고 외우는게 좋을듯 --%>
	<div>${param.a - param.b >  0}, ${param.a - param.b gt 0}</div>	<%-- a가 클때 --%>
	<div>${param.a - param.b <  0}, ${param.a - param.b lt 0}</div>	<%-- a가 작을때 --%>
	<div>${param.a - param.b >= 0}, ${param.a - param.b ge 0}</div>	<%-- a가 크거나 같을때 --%>
	<div>${param.a - param.b <= 0}, ${param.a - param.b le 0}</div>	<%-- a가 작거나 같을때--%>
	<div>${param.a - param.b == 0}, ${param.a - param.b eq 0}</div>	<%-- a와 b가 같을때 --%>
	<div>${param.a - param.b != 0}, ${param.a - param.b ne 0}</div>	<%-- a와 b가 다를때--%>
	
	<div>${param.a == 7 && param.b == 2}, ${param.a eq 7 and param.b eq 2}</div>
	<div>${param.a == 7 || param.b == 2}, ${param.a eq 7 or  param.b eq 2}</div>
	<div>${!(param.a == 7)}, ${not (param.a eq 7)}</div>
	
	<div>${param.a == 7 ? "a는 7이다" : 'a는 7이 아니다'}</div>	

	<%-- & 잘못된 방식  --%>
	<div>${param.a} > ${param.b}</div>	
	<%-- 결과 : a값 < b값 그대로 출력된다(비교안함)  --%>
</body>
</html>