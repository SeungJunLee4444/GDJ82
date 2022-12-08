package com.gdu.res.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.res.domain.MemberDTO;

@Mapper
public interface MemberMapper {
	
	// 추가
	public int insertMember(MemberDTO member);
	
	// 목록 + 페이징처리
	public int selectMemberCount();
	public List<MemberDTO> selectMemberListByMap(Map<String, Object> map);
	
	// 상ㅅ에
	public MemberDTO selectMemberByNo(int memberNo);
	
	// 수정
	public int updateMember(Map<String, Object> map);
	
	// 삭제
	public int deleteMemberList(List<String> memberNoList);

}
