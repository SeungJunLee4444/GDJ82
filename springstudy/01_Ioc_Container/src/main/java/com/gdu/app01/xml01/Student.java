package com.gdu.app01.xml01;

public class Student {

	// # field
	private String name;
	private String school;
	private Calculator calculator;	// 학생이 들고다니는 계산기
	
	
	// # method(getter+setter);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public Calculator getCalculator() {
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
}
