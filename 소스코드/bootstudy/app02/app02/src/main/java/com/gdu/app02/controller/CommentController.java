package com.gdu.app02.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app02.domain.CommentDTO;
import com.gdu.app02.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	// # ajax service : 블로그(게시글)의 댓글 총 개수
	@ResponseBody
	@GetMapping(value="/comment/getCount", produces="application/json")
	public Map<String, Object> getCount(@RequestParam("blogNo") int blogNo) {
		return commentService.getCommentCount(blogNo);
	}
	
	// # 댓글추가
	@ResponseBody
	@PostMapping(value="/comment/add", produces="application/json")
	public Map<String, Object> add(CommentDTO commentDTO) {
		return commentService.addComment(commentDTO);
	}
	
	// # 댓글목록
	@ResponseBody
	@GetMapping(value="/comment/list", produces="application/json")
	public Map<String, Object> list(HttpServletRequest request) {
		return commentService.getCommentList(request);
	}
	
	// # 댓글삭제
	@ResponseBody
	@PostMapping(value="/comment/remove", produces="application/json")
	public Map<String, Object> remove(@RequestParam("commentNo") int commentNo){
		return commentService.removeComment(commentNo);
	}
	
	// # 답글추가
	@ResponseBody
	@PostMapping(value="/comment/reply/add", produces="application/json") 
	public Map<String, Object> replyAdd(CommentDTO reply) {
		return commentService.addReply(reply);
	}
	
	
	
		
	
}
