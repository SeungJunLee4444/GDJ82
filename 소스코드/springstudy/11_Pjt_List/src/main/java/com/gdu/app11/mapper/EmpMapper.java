package com.gdu.app11.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.EmpDTO;


// # Mapper 인터페이스 
// * employees.xml의 mapper 경로와 일치해야한다
@Mapper
public interface EmpMapper {
	
	// # totalRecord 전체 게시글 수 
	public int selectAllEmployeesCount();
	
	// # 페이지당 게시글 
	public List<EmpDTO> selectEmployeesByPage(Map<String, Object> map);
	
	// # 검색
	public int selectFindEmployeesCount(Map<String, Object> map);

	// # 직원조회
	public List<EmpDTO> selectFindEmployees(Map<String, Object> map);
	
	// # 이메일, 성, 이름 자동완성
	public List<EmpDTO> selectAutoCompleteList(Map<String, Object> map);

	
}
