<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="../layout/header.jsp">
	<jsp:param value="블로그작성" name="title"></jsp:param>
</jsp:include>

<script>

		// * contextPath를 반한하는 자바스크립트 함수 : 별도로 js파일을 만들어 contextpath 변수를 사용할 수 없을때 사용
		// - 방법 : 
		// - location의 값
		/* Location {
			href: 'http://localhost:9090/app15/blog/write',
			origin: 'http://localhost:9090', 
			host: 'localhost:9090',
			
			protocol: 'http:', 
			ancestorOrigins: DOMStringList, 
			 …}
		
		// * location.href.indexOf(a) : href에서 a가 시작되는 값의 인덱스
		// * 자바스크립트의 인덱스는 0부터 시작한다
		// * indexOf
		// (1) a.indexOf(문자열) : a에서 문자열이 시작되는 인덱스값
		// (2) a.indexOf(문자열, 위치) : a에서 특정문자열을 찾아, 해당 문자열이 첫번째로 나타나는 위치 index를 리턴
		// 이때 위치는 찾기 시작할 위치를 말한다
		// * substring(a,b) : a에서 b인덱스값 사이의 문자열을 반환(a포함 b미포함)
		*/
		function getContextPath() {
			console.log(location);
			// 1. begin : http://localhost:9090까지의 인덱스값 : 0 + origin의 길이 22 => 즉 origin에서 가장 마지막인 0을 의미하는 숫자값
			var begin = location.href.indexOf(location.origin) + location.origin.length; // origin, http://localhost:9090에서 
			
			// 2. begin에서의 / 다음 /찾기
			var end = location.href.indexOf("/", begin + 1) // begin에서 + 1, 즉 begin 다음부터 첫번째로 발견되는 /의 인덱스값을 반환
			return location.href.substring(begin, end);
		}

	$(document).ready(function() {

		getContextPath();
		
		// # js : summernote 편집기 사용 스크립트
		$('#content').summernote({
			width : 800,
			height : 400,
			lang : 'ko-KR',
			toolbar: [
			    // [groupName, [list of button]]
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough', 'superscript', 'subscript']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['link', 'picture', 'video']]
			],
			// # js : summernote 편집기에 이미지를 로드할 때 이미지는 function의 매개변수 files로 전달됨
			callbacks: {
				onImageUpload : function(files){	// * files : 이미지가 저장되있음

					
					// 1. 이미지를 ajax로  서버로 보낼때 가상 form 데이터를 사용
					var formData = new FormData();
					formData.append('file', files[0]);	// * 파라미터 file, summernote 편집기에 추가된 이미지가 files[0]
				
					
					// 2. 이미지를 hdd(하드)에 저장하고 경로를 받아오는 ajax
					$.ajax({
						type : 'post',
						url : getContextPath() + '/blog/uploadImage',
						data : formData,
						contentType : false,					// ajax 이미지 첨부용으로 작성
						processData : false,					// ajax 이미지 첨부용으로 작성
						
						dataType : 'json',						// * 하드디스크에 저장된 이미지의 경로를 json으로 받아오겠다
						success : function(resData) {	
							$('#content').summernote('insertImage', resData.src);	// * serviceimpl의 src에서 가져옴
							/* 
								src = ${contextPath}/load/image/aaa.jpg 값이 넘어온 경우
								summernote는
								<img src="${contextPath}/load/image/aaa.jpg"> 태그를 만든다
								
								* 스프링에서 정적 자원 표시하는 방법
								- 답 : servlet-context.xml에 표시되있음
							*/
						}
					
					});	// ajax
				}	// onImageUpload
			}	// callbacks
			
		});
		
		// # 목록 돌아가기 -------------------------------------------------
		$('#btn_list').click(function(){
			location.href = getContextPath() + '/blog/list';	// * 스크립트를 별도로 js를 만들경우, contextPath 문제발생
		});
		
		// # 서브밋 방지 : write와 title이 작성되지 않으면 서브밋 멈춤 -----
		// - 이유 : sql에서 title, write는 필수로 지정했음
		$('#frm_write').submit(function(event) {
			if($('#title').val() == '' || $('#writer').val() == '') {
				alert('제목과 작성자는 필수다 ')
				event.preventDefault();
				return;
			}
			
		});
	});
</script>
</head>
<body>

	<%-- # 게시글 추가 화면 : add요청으로 게시글 추가 요청 --%>
	
	<div>
		<h1>작성화면</h1>
		<form id="frm_write" action="${contextPath}/blog/add" method="post">	<%-- & 게시글을 추가해야하니 form 요청 사용 : name 속성 필요 --%>
			<div>
				<label for="title">제목</label>
				<input type="text" id="title" name="title">
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer">
			</div>
			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content"></textarea>
			</div>
			<div>
				<button>작성완료</button>
				<input type="reset" value="입력초기화">
				<input type="button" value="목록" id="btn_list">
			</div>
		
		
		</form>
	</div>
</body>
</html>