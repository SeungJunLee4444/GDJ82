<%@page import="java.util.ArrayList"%>
<%@page import="domain.Board"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- # 반복문 : <c:forEach> --%>
	<%-- - 형태
	<c:forEach var="변수선언" begin="시작값" end="끝나는값" step="증가값" />
	- 주의 : 범위는 begin <= n <= end다
	( * 자바와 달리 end값이 범위에 포함된다)
	
	* <c:forEach/> : 태그를 슬래시로 닫는태그를 해도 문제없다
	
	 --%>
	
	<%-- - 실습 --%>
	<%-- 1. 1 ~ 5 --%>
	<c:forEach var="n" begin="1" end="5" step="1"/>
		${n}&nbsp;
	<hr>
	
	<%-- 2. 5 ~ 1 --%>
	<c:forEach var="n" begin="1" end="5" step="1">
		${6 - n}&nbsp;
	</c:forEach>
	
	<hr>
	
	<%-- 3. <select> 1월 ~ 12월 --%>
	<select name="month">
		<option value="">월 선택</option>
		<c:forEach var="m" begin="1" end="12" step="1">
			<option value="${m}">${m}월</option>
		</c:forEach>
	</select>
	
	<hr>
	
	<%-- # 배열 반복문 --%>
	<%-- - 형태
	<c:forEach var="menu" items="배열의변수" varStatus="상태용변수">
	
	- 출력
	${상태용변수.속성명}	: 속성값
	
	 --%>
	<%-- 4. 배열 --%>
	<%
	String[] menus = {"김밥", "떡볶이", "순대"};
			pageContext.setAttribute("menus", menus);
	%>
	
	<%-- * setAttrubute는 배열도 저장가능하며,
	이는 c:forEach문을 이용해서 출력할 수 있다
	 --%>
	
	<c:forEach var="menu" items="${menus}" varStatus="vs">
		인덱스 : ${vs.index}, 순번 : ${vs.count}, 배열요소 : ${menu}<br>
	</c:forEach>
	
	<hr>
	
	<%-- 5. 리스트 --%>
	<%
		List<String> seasons = Arrays.asList("봄", "여름", "가을", "겨울");
		pageContext.setAttribute("seasons", seasons);
	%>
	<c:forEach var="season" items="${seasons}" varStatus="k">
		인덱스 : ${k.index}, 순번 : ${k.count}, 리스트요소 : ${season}<br>
	</c:forEach>
	
	<hr>
	
	<%-- 6. Map (반복이 필요한 건 아님) --%>
	<%
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", 1);
		map.put("end", 10);
		pageContext.setAttribute("map", map);
	%>
	${map.begin} ~ ${map.end}<br>
	
	<hr>
	
	<%-- 7. 객체 (반복이 필요한 건 아님) --%>
	<%
		Board board = new Board();
		board.setBoardNo(1);
		board.setTitle("도대체 언제까지...");
		board.setHit(100);
		pageContext.setAttribute("board", board);
	%>
	${board.boardNo}, ${board.title}, ${board.hit}<br>
	${board.getBoardNo()}, ${board.getTitle()}, ${board.getHit()}<br>
	
	<%--
		${board.title}은 ${board.getTitle()}을 자동으로 호출한다.
	--%>
	
	<hr>
	
	<%--
		문제. 임의의 Board 객체를 3개 저장한 리스트
	--%>
	<%
		List<Board> boards = new ArrayList<>();
		boards.add(new Board(100, "질문입니다", 2));
		boards.add(new Board(200, "  [Re] 저도 궁금해요", 1));
		boards.add(new Board(300, "답변입니다", 5));
		pageContext.setAttribute("boards", boards);
	%>
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>게시글번호</td>
				<td>제목</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="board" items="${boards}" varStatus="vs">
				<tr>
					<td>${vs.count}</td>
					<td>${board.boardNo}</td>
					<td>${board.title}</td>
					<td>${board.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>