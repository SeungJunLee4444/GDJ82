package com.gdu.app01.xml08;

import java.util.List;

public class Member {
	
	// 3. 멤버
	
	// 필드
	private String name;
	private List<String> course;
	private double height;				// 선택
	private double weight;				// 선택
	private BMICalculator bmiCalc;		// 2. bmi계산기
	
	// 생성자
	public Member(String name, List<String> course, double height, double weight, BMICalculator bmiCalc) {
		super();
		this.name = name;
		this.course = course;
		this.height = height;
		this.weight = weight;
		this.bmiCalc = bmiCalc;
	}
	
	// info()
	public void info() {
		System.out.println(name);
		for(String str : course) {
			System.out.println("Course" + str);		// & list로 저장되있으니 for문
		}
		System.out.println(height);
		System.out.println(weight);
		System.out.println(bmiCalc);
		bmiCalc.info();					// bmi의 bmi지수, health
	}
	
	

}
