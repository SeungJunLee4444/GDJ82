package com.gdu.app11.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app11.domain.EmpDTO;


// # Mapper 인터페이스 
// * employees.xml의 mapper 경로와 일치해야한다
@Mapper
public interface EmpMapper {
	
	// # totalRecord 전체 게시글 수 
	public int selectAllEmployeesCount();
	
	// # 페이지당 게시글 조회
	public List<EmpDTO> selectEmployeesByPage(int begin, int end);


	
}
