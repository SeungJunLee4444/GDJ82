package com.gdu.app11.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmpDTO {
	
	// # EmpDTO : HR계정 EMPLOYEES 테이블 참고
	// * 외래키 추가하는 방법 : DeptDTO 클래스를 필드값으로 처리한다
	// - 관계 : DEPARTMENT(부모) - EMPLOYEES(자식)
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private Date hireDate;
	private String jobId;
	private double commissionPct;
	private double salary;
	private int managerId;
	private DeptDTO deptDTO;	// & DEPARTMENT 부서의 외래키(DEPARTMENT_ID) 대신 작성



}