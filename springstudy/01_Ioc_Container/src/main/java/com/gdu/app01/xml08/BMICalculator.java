package com.gdu.app01.xml08;

public class BMICalculator {
	
	// - 계산기를 필드값으로 가진 bmi계산기
	
	// 필드
	private Calculator calc;
	private double height;  // 키(cm 단위)
	private double weight;  // 몸무게(kg 단위)
	private double bmi;     // BMI : 몸무게(kg) / 키(m) * 키(m)
	private String health;  // bmi < 20 : 저체중, bmi < 25 : 정상, bmi < 30 : 과체중, 나머지 : 비만
	
	// 생성자
	// & 계산에 관련된 문제는 매개변수로 받지 않고 생성자의 this값에서 처리해야한다
	public BMICalculator(Calculator calc, double height, double weight) {	// double bmi, String health => 지우기		// 
		super();
		this.calc = calc;
		this.height = height;
		this.weight = weight;
		bmi = calc.div(weight, calc.div(calc.mul(height, height), 10000));
		health = (bmi < 20) ? "저체중" : (bmi < 25) ? "정상" : (bmi < 30) ? "과체중" : "비만";
	}
	
	// info() 메서드
	// height, weight : bmi, 또는 member에서 보여주면됨
	// bmi, health는 여기서 보여줘야함
	public void info() {
		System.out.println("BMI: " + bmi);
		System.out.println("Health: " + health);
	}
	
	
	

}
