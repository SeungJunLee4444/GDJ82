<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	caption {
		margin: 5px 0;
		font-weight: bold;
	}
	td {
		padding: 5px;
		border: 1px solid gray;
	}
	tbody td:first-of-type {
		background: aqua;
	}
	tfoot td {
		text-align: center;
	}
</style>
</head>
<body>
	<form action="Quiz11B.jsp" method="post">
		<table>
			<caption>개인정보처리</caption>
			<tbody>
				<tr>
					<td>성명</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>나이</td>
					<td><input type="text" name="age"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td>
						<label><input type="radio" name="gender" value="남"> 남</label>
						<label><input type="radio" name="gender" value="여"> 여</label>
					</td>
				</tr>
				<tr>
					<td>취미</td>
					<td>
						<label><input type="checkbox" name="hobbies" value="운동"> 운동</label>
						<label><input type="checkbox" name="hobbies" value="여행"> 여행</label>
						<label><input type="checkbox" name="hobbies" value="영화"> 영화</label>
						<label><input type="checkbox" name="hobbies" value="휴식"> 휴식</label>
					</td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
						<select name="address">
							<option value="">::선택::</option>
							<option value="경기">경기</option>
							<option value="인천">인천</option>
							<option value="서울">서울</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>좋아하는 음식</td>
					<td>
						<select name="foods" multiple>  <!-- multiple 옵션을 쓰면 다중 선택이 가능함. -->
							<option value="짜장">짜장</option>
							<option value="짬뽕">짬뽕</option>
							<option value="볶음밥">볶음밥</option>
							<option value="탕수육">탕수육</option>
						</select>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<button>전송</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</form>
</body>
</html>