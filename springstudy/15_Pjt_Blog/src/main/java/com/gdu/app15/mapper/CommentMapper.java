package com.gdu.app15.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app15.domain.CommentDTO;

@Mapper
public interface CommentMapper {
	
	// # 블로그(게시글)의 댓글 총 개수
	public int selectCommentCount(int blogNo);
	
	// # 댓글추가
	public int insertComment(CommentDTO comment);
	
	// # 댓글리스트
	public List<CommentDTO> selectCommentList(Map<String, Object> map);
	
	// # 댓글삭제
	public int deleteComment(int commentNo);
	
	// # 답글추가
	public int insertReply(CommentDTO reply);

}
