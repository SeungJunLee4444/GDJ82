package com.gdu.app12.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app12.domain.BbsDTO;
import com.gdu.app12.mapper.BbsMapper;
import com.gdu.app12.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor

// # 생성자로 bean주입 
// - 필드가 하나면 @autowired, 필드가 복수면 @AllArgsConstructor를 사용해서 @bean을 주입한다

@Service
public class BbsServiceImpl implements BbsService {

	
	private BbsMapper bbsMapper;
	private PageUtil pageUtil;
	
	// [[[ 게시글 조회 서비스
	@Override
	public void findAllBbsList(HttpServletRequest request, Model model) {
		
		// # 파라미터 : 페이지 처리를 위한 page 파라미터
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt1.orElse("1"));
		
		// # 전체 게시글 수 
		int totalRecord = bbsMapper.selectAllBbsCount();	// ----> db
		
		// # 파라미터 recordPerPage, 전달되지 않으면 recordPerPage=10으로 처리
		// * 보려는 페이지당 게시글의 수를 필드로 고정시키는 것이 아닌 파라미터로 전달하는것
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("recordPerPage"));
		int recordPerPage = Integer.parseInt(opt2.orElse("10"));
		
		
		
		// # 페이징에 필요한 모든 계산 완료
		pageUtil.setPageUtil(page, recordPerPage, totalRecord);
		
		// # db로 보낼 map 생성
		Map<String, Object> map = new HashMap<>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// # db에서 목록 가져오기
		List<BbsDTO> bbsList = bbsMapper.selectAllBbsList(map);
		
		// # 뷰로 보낼 데이터(응답)
		model.addAttribute("bbsList", bbsList);
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/bbs/list"));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());		// * getRecordPerPage : 한 페이지당 게시글수 (getter)
		model.addAttribute("recordPerPage", recordPerPage);
	
	}

	
	// [[[ 원글삽입 서비스
	@Override
	public int addBbs(HttpServletRequest request) {
		
		// # 파라미터
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String ip = request.getRemoteAddr();
		
		// # dto에 저장
		BbsDTO bbs = new BbsDTO();
		bbs.setWriter(writer);
		bbs.setTitle(title);
		bbs.setIp(ip);
		
		// # db에 전송
		int result = bbsMapper.insertBbs(bbs);
		
		
		return result;
	}

	
	@Override
	public int addReply(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}

	// [[[ 삭제 서비스
	@Override
	public int removeBbs(int bbsNo) {
		
		return bbsMapper.deleteBbs(bbsNo);
	}

}
