package com.gdu.app12.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	// [[[ 답글삽입 서비스
	@Transactional
	/* 트랜잭션
	  @Transactional : 트랜잭션을 처리하는 에너테이션
	  - 용도 : INSERT, UPDATE, DELETE 2개 이상이 호출되는 서비스에 추가된다 
	 */
	@Override
	public int addReply(HttpServletRequest request) {
		
		// # 파라미터 : 작성자, 제목, ip (삽입시 필요한건 실제로 이 3가지)
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String ip = request.getRemoteAddr();
		
		// # 원글의 정보 : DEPTH, GROUP_NO, GROUP_ORDER 3가지 필요
		// - 방법 : (1) 원글의 번호만 받아와서 DB로 조회 / (2) 3가지를 list.jsp에서 파라미터로 가져오기
		
		// (2)번 사용
		int depth = Integer.parseInt(request.getParameter("depth"));
		int groupNo = Integer.parseInt(request.getParameter("groupNo"));
		int groupOrder = Integer.parseInt(request.getParameter("groupOrder"));
		
		// # 원글 DTO : updatePreviousReply에 전달할 용도
		BbsDTO bbs = new BbsDTO();
		bbs.setDepth(depth);
		bbs.setGroupNo(groupNo);
		bbs.setGroupOrder(groupOrder);
		
		// # db로 원글 dto 전송 : 원글의 3가지 정보 update
		bbsMapper.updatePreviousReply(bbs);
		
		
		// # 답글 dto 생성 : writer, title, ip, depth, groupno, grouporder 6가지 필요
		
		BbsDTO reply = new BbsDTO();
		reply.setWriter(writer);
		reply.setTitle(title);
		reply.setIp(ip);
		reply.setDepth(depth + 1);				// depth   : 답글은 원글의 depth + 1
		reply.setGroupNo(groupNo);				// groupNo : 답글과 원글 일치
		reply.setGroupOrder(groupOrder + 1);	// groupOrder : 답글은 원글 groupOrder + 1
		
		// # db로 답글 dto 전송 : 답글 insert
		return bbsMapper.insertReply(reply);
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	// [[[ 삭제 서비스
	@Override
	public int removeBbs(int bbsNo) {
		
		return bbsMapper.deleteBbs(bbsNo);
	}

}
