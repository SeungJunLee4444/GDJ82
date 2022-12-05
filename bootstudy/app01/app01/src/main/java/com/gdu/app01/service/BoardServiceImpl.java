package com.gdu.app01.service;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.app01.domain.BoardDTO;
import com.gdu.app01.mapper.BoardMapper;

// # 서비스 컴포넌트
@Service
public class BoardServiceImpl implements BoardService {

	
	// + dao에서 boardmapper을 사용
	@Autowired
	private BoardMapper mapper;


	
	// # 모두 조회 -----------------------------------------------------------------------------------------------------
	@Override
	public List<BoardDTO> findAllBoard() {
		return mapper.selectAllBoards();
	}

	
	// # 상세 조회 ------------------------------------------------------------------------------------------------------
	@Override
	public BoardDTO findBoardByNo(int boardNo) {
		
		return mapper.selectBoardByNo(boardNo);
	}

	
	// # 게시글 추가(* 경고창 띄우기 : 반환값인 int를 사용한다) -----------------------
	@Override
	public int saveBoard(BoardDTO board) {
		
		return mapper.insertBoard(board); // * getinstance를 쓰지 않아도 @componet로 인해 알아서 싱글턴처리
	}

	
	// # 게시글 수정(* 경고창 띄우기 : 반환값인 int를 사용한다)
	@Override
	public int modifyBoard(BoardDTO board) {
		return mapper.updateBoard(board);
	}

	
	// # 게시글 삭제(* 경고창 띄우기 : 반환값인 int를 사용한다)
	@Override
	public int deleteBoard(int boardNo) {
		
		return mapper.deleteBoard(boardNo);
	}
	
	
	// # 리스트 삭제
	@Override
	public void removeBoardList(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터
		String[] boardNoList = request.getParameterValues("boardNoList");
		
		// 삭제
		int result = mapper.deleteBoardList(Arrays.asList(boardNoList));  // String 배열을 List<String>으로 변경해서 전달
		
		try {
			
			// 자바스크립트로 응답으로 만들어서 처리하는 방식
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {
				out.println("alert('모두 삭제 성공');");
				out.println("location.href='" + request.getContextPath() + "/brd/list';");  //  /brd/list로 redirect
			} else {
				out.println("alert('모두 삭제 실패');");
				out.println("history.back();");  // 이전 화면으로 이동
			}
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
}
