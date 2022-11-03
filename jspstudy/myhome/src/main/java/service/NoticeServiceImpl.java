package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import common.ActionForward;
import domain.Notice;
import memberDao.NoticeDao;

public class NoticeServiceImpl implements NoticeService {

	
	
	@Override
	public ActionForward findAllNotices(HttpServletRequest request) {
		
		// # 파라미터 page 확인 ( * 처음들어갈때 페이지는 목록 1페이지로 띄우게 만들기) -----------------
		
		// 1) 파라미터가 없으면 page=1로 처리 : optional로 처리
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		// - 해석 : page 파라미터의 값이 null일 경우, 값을 1로 변환
		
		
		// # 전체목록 개수 구하기 : dao로 db 접근 	---------------------------------------------------------------------------------------------------------------> dao, db로
		NoticeDao dao = NoticeDao.getInstance();
		int totalRecordCnt = dao.selectAllNoticesCnt();
		
		
		// # 한 페이지에 표시할 목록의 개수 ( * 게시글 하나하나를 record로 가정)--------------------------
		int recordPerPage = 10;
		
		
		// # 특정 page에 표시할 목록의 시작번호와 끝번호 -------------------------------------------------
		/*
		   & 가정
		   			begin	/ 	end
		   page 1 :    1		10		=> 10 레코드
		   page 2 :   11 		20		=> 10 레코드
		   page 3 :   21        30		=> 10 레코드
		   page 4 :   31        35		=> 5 레코드(남은레코드)
		   // & 여기서 begin과 end는 ROWNUM(DB함수)를 의미한다
		 */
		// * 시작페이지 계산식 : begin이 일정 값이 되면 앞자리가 늘어나는 개념( 해석 : 1에 십의단위를 곱한 값을 더한다)
		int begin = (page - 1) * recordPerPage + 1;
		int end = begin + recordPerPage -1; // (해석 : 첫재숫자를 1을 빼서 0으로 만듬)
		if(end > totalRecordCnt) {
			end = totalRecordCnt;	// (- 해석 : end가 40이고, 전체게시글이 35개면 end를 전체게시글 수로 고정시킨다)
		}
		
		
		// # begin, end를 map으로 만들어서 dao에게 전달 --------------------------------------------------------------
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", begin);
		map.put("end", end);
		
		// # begin에서 end 사이에 목록 가져오기	---------------------------------------------------------------------------------------------------------------------> dao, db로
		List<Notice> notices = dao.selectAllNotices(map);	
		
		// # 목록을 forward 하기 위해서 request에 저장
		request.setAttribute("notices", notices);
		
		return new ActionForward("/notice/list.jsp", false);
	}

}
