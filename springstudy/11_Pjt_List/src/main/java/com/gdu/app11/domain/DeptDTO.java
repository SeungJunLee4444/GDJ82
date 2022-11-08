package com.gdu.app11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeptDTO {
	
	// # DeptDTO : DEPARTMENT 테이블 참고

	private int departmentId;
	private String departmentName;
	private int managerId;
	private int locationId;
		
}