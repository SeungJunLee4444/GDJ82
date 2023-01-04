package domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {
	
	// 1. DTO : 계층간 데이터 이동 클래스
	// SQL의 테이블 데이터와 동일하게 일치시키기

	private int stuNo;				
	private String name;
	private int kor, eng, math;
	private double ave;
	private String grade;
	
	
}