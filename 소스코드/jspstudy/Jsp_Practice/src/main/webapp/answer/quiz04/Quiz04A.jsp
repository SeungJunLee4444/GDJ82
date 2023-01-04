<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		$('#result').on('blur', function(){
			if ( isNaN($(this).val()) ) {  // $(this)는 $('#result')이다.
				alert('정수만 입력할 수 있습니다.');
				$(this).val('');
				$(this).focus();
			}
		});
	})
</script>
</head>
<body>
	<%
		// 1 ~ 9 랜덤 생성
		int a = (int)(Math.random() * 9) + 1;
		int b = (int)(Math.random() * 9) + 1;
		// +, -, *, /, % 랜덤 생성
		String[] operators = {"+", "-", "*", "/", "%"};
		String op = operators[(int)(Math.random() * operators.length)];
		// 연산
		int answer = 0;
		switch (op) {
		case "+": answer = a + b; break;
		case "-": answer = a - b; break;
		case "*": answer = a * b; break;
		case "/": answer = a / b; break;
		case "%": answer = a % b; break;
		}
	%>
	<h3>랜덤 계산기</h3>
	<form action="Quiz04B.jsp" method="post">
		<%=a%>&nbsp;<%=op%>&nbsp;<%=b%>&nbsp;=&nbsp;<input type="text" name="result" size="3" id="result">
		<input type="hidden" name="a" value="<%=a%>">
		<input type="hidden" name="b" value="<%=b%>">
		<input type="hidden" name="op" value="<%=op%>">
		<input type="hidden" name="answer" value="<%=answer%>">
		<button>제출</button>
	</form>
</body>
</html>