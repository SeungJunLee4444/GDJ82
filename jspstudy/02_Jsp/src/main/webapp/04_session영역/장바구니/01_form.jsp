<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div>
		<h1>쇼핑 합시다</h1>
		<form method="GET" action="02_add.jsp">
			<select name="item">
				<option value="TV">TV</option>
				<option value="냉장고">냉장고</option>
				<option value="세탁기">세탁기</option>
				<option value="컴퓨터">컴퓨터</option>
				<option value="스타일러">스타일러</option>
			</select>
			<input type="text" name="amount" size="4">개
			<button>Add Cart</button>
		</form>
	</div>

</body>
</html>