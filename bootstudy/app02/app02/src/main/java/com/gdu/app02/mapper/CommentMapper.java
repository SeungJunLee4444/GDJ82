package com.gdu.app02.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app02.domain.CommentDTO;

@Mapper
public interface CommentMapper {
	public int selectCommentCount(int blogNo);
	public int insertComment(CommentDTO comment);
	public List<CommentDTO> selectCommentList(Map<String, Object> map);
	public int deleteComment(int commentNo);
	public int insertReply(CommentDTO reply);
}
