<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- HTML 주석 : 소스보기에서 확인 가능 -->
	<%-- JSP  주석 : 소스보기에서 확인 불가능 --%>
	
	<%!
		// 선언부(Declaration) : 전역 변수 선언, 메소드 정의
		public int getSum(int begin, int end){
			int sum = 0;
			for(int n = begin; n <= end; n++){
				sum += n;
			}
			return sum;
		}
	%>
	
	<%
		// 스크립트릿(Scriptlet) : 일반 자바 코드
		int sum = getSum(1, 100);
	%>
	
	<!-- 표현식(Expression) : 값(변수, 메소드 호출 결과)을 나타낼 때 사용 -->
	<div><%=sum%></div>
	<div><%=getSum(1, 1000)%></div>
	
	
	<!-- 연습. 화면에 1 ~ 10 출력하기 -->
	<% for(int n = 1; n <= 10; n++) { %>
		<div><%=n%></div>
	<% } %>
	
	<!--
		연습. select 태그 만들기
		<select name="month">
			<option value="">월 선택</option>
			<option value="1">1월</option>
			...
			<option value="12">12월</option>
		</select>
	-->
	<div>
		<select name="month">
			<option value="">월 선택</option>
			<% for(int m = 1; m <= 12; m++) { %>
				<option value="<%=m%>"><%=m%>월</option>
			<% } %>
		</select>
	</div>
	
	<!--
		연습. 테이블 만들기
		순번	이름	나이
		1		정숙	25
		2		영희	26
		3		영숙	27
	-->
	<%
		String[] names = {"정숙", "영희", "영숙"};
		int[] ages = {25, 26, 27};
	%>
	<div>
		<table border="1">
			<thead>
				<tr>
					<td>순번</td>
					<td>이름</td>
					<td>나이</td>
				</tr>
			</thead>
			<tbody>
				<% for(int i = 0; i < names.length; i++) { %>
					<tr>
						<td><%=i+1%></td>
						<td><%=names[i]%></td>
						<td><%=ages[i]%></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>

</body>
</html>