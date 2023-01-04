package com.gdu.app01.xml02;

public class Car {
	
	// [2장 : 엔진클래스를 자바빈으로 가진 자동차]
	
	// field
	private String model;
	private String maker;
	private Engine engine;	// 자바빈
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	

}
