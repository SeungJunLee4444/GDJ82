package com.gdu.app12.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app12.domain.BbsDTO;


// # Mapper 인터페이스 
// * employees.xml의 mapper 경로와 일치해야한다
@Mapper
public interface BbsMapper {
	
	// 게시판 목록 조회 : 
	public int selectAllBbsCount();
	public List<BbsDTO> selectAllBbsList(Map<String, Object> map);	// * map : begin과 end를 전달하기 위해
	
	// 추가 (1) 원글삽입
	public int insertBbs(BbsDTO bbs);

	// + 삽입시 댓글 순서 맞추기 : 답글 삽입 전 기존 답글의 GROUP_ORDER 업데이트 
	public int updatePreviousReply(BbsDTO bbs); 
	
	// 추가 (2) 댓글삽입 
	public int insertReply(BbsDTO bbs);
	
	// 삭제 : 
	public int deleteBbs(int bbsNo);
	
	
 

	
}
