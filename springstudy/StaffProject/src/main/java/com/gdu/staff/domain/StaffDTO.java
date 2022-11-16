package com.gdu.staff.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class StaffDTO {
	
	private String sno;
	private String name;
	private String dept;
	private int salary;

}
