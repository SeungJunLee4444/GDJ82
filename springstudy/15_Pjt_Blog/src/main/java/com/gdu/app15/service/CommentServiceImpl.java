package com.gdu.app15.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gdu.app15.domain.CommentDTO;
import com.gdu.app15.mapper.CommentMapper;
import com.gdu.app15.util.PageUtil;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

	
	private CommentMapper commentMapper;
	private PageUtil pageUtil;
	
	// # 블로그(게시글)의 댓글 총 개수
	@Override
	public Map<String, Object> getCommentCount(int blogNo) {
		Map<String, Object> result = new HashMap<>();
		result.put("commentCount", commentMapper.selectCommentCount(blogNo));	
		// * detail.jsp에서 받아올 값의 이름은 commentCount
		// * ajax의 json 데이터 = map
		return result;
	}
	
	// # 댓글추가
	@Override
	public Map<String, Object> addComment(CommentDTO commentDTO) {
		Map<String, Object> result = new HashMap<>();
		result.put("isAdd", commentMapper.insertComment(commentDTO));
		return result;
	}
	
	// # 댓글리스트
	@Override
	public Map<String, Object> getCommentList(HttpServletRequest request) {
		
		// 1. 파라미터
		int blogNo = Integer.parseInt(request.getParameter("blogNo"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		// 2. 댓글 총 개수
		int commentCount = commentMapper.selectCommentCount(blogNo);
		
		// 3. 페이징 처리계산
		pageUtil.setPageUtil(page, commentCount);
		
		// 4. db에 전달할 map
		Map<String, Object> map = new HashMap<>();
		map.put("blogNo", blogNo);
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// 5. 댓글목록
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("commentList", commentMapper.selectCommentList(map));
		// System.out.println(commentMapper.selectCommentList(map));
		result.put("pageUtil", pageUtil);
		return result;
	}
	

	
}
