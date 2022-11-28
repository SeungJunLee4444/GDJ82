package com.gdu.app15.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gdu.app15.domain.CommentDTO;

public interface CommentService {
	
	// # 블로그(게시글)의 댓글 총 개수
	public Map<String,Object> getCommentCount(int blogNo);
	
	// # 댓글추가
	public Map<String, Object> addComment(CommentDTO commentDTO);
	
	// # 댓글리스트
	public Map<String, Object> getCommentList(HttpServletRequest request);

}
