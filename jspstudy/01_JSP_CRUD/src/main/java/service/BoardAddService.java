package service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ActionForward;
import domain.Board;
import repository.BoardDao;

public class BoardAddService implements BoardService {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// BoardAddService : 게시글 추가 서비스
		
		// # 요청--------------------------------------------------------------------------------------------------------------
		// # 요청 파라미터
		String title = request.getParameter("title");		// not null 처리는 write.jsp 화면에서 이미 했음
		String content = request.getParameter("content");
		
		// & not null처리의 종류 --------------------------------------------------------------------------------&
		// (1) 상세화면 요청시 boardNo가 null일 경우 
		// (2) 추가화면에서 title이 공백인경우
		
		// & 내 생각
		// - 일단 boardNo는 db에 접근해서 확인을 해봐야하기 때문에 optional 처리를 한것같다
		// - title은 추가하는 입장이기 때문에, db에 애초에 내용이 없으므로 jsp 화면에서 바로 리턴시키는게
		// 효율적이다
		// * boardNo는 애초에 int타입으로 변환해 db에 접근해야하기 때문에, null값이면 db에 접근시 문제가 발생한다
		// => boardNo는 null값을 0으로 변환하기 위해서도 백엔드 영역으로 넘어와야한다
		
		// & 최종정리 : not null 처리의 방법이 갈리는 이유
		// (1) 조회냐, 추가내 요청의 타입에 따라서 
		// (2) int타입 파라미터는 null값이 아닌 0으로 변환해서 db에 접근해야해서
		//-------------------------------------------------------------------------------------------------------&
		
		
		// # DB로 보내기 위해 board dto에 저장(title, content
			Board board = new Board();
			board.setTitle(title);
			board.setContent(content);
			
		// # DB로 Board board 보내기(삽입) ========================================================================> DAO, DB
		int result = BoardDao.getInstance().insertBoard(board);
		// * db 쿼리문의 성공은 1은 성공, -1은 실패
		
		// # 응답--------------------------------------------------------------------------------------------------------------
		// # 삽입 성공 / 실패
		
		// & 응답의 유형
		// (1) 현재 화면에서 경고창 띄우기(현재 타입)
		// (2) 새로운 화면으로 이동해서 경고창 띄우기---확인해보기?
		
		
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('게시글이 등록되었습니다.')");
			out.println("location.href='" + request.getContextPath() + "/board/list.do'");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('게시글 등록이 실패했습니다.')");
			out.println("history.back()");  // history.go(-1)
			out.println("</script>");
		}
		out.close();		
		
		
		
		return null; // * 컨트롤러로 null을 반환하면 컨트롤러는 리다이렉트&포워드 모두 수행하지 않음 (!null일 경우에만 작동하기떄문)
	}

}
