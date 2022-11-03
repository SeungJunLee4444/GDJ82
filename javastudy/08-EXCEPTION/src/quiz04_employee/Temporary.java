package quiz04_employee;

public class Temporary extends Employee {

	private double hourpay;
	private int workTimes;
	
	public Temporary(int empNo, String name) {
		super(empNo, name);
		
	}
	public double getHourpay() {
		return hourpay;
	}
	public void setHourpay(double hourpay) {
		this.hourpay = hourpay;
	}
	public int getWorkTime() {
		return workTimes;
	}
	public void setWorkTime(int workTimes) {
		this.workTimes = workTimes;
	}

	@Override
	public String toString() {
		return super.toString() + "Temporary [pay=" + hourpay + ", workTime=" + workTimes + "]";
	}
	
	@Override
	public int getPay() {
		
		return (int)(hourpay * workTimes);	// # getpay 추상화후 오버라이징한 이유
											// => 시급과 월급 계산방식이 다르기 때문
	}										// => 타입이 다르니 int로 통일
	
	
	
	// # 진행목표
	// 1 new Temporary(1, "name")
	// 2 setPay() , setWorkTime();
	
	
	
	
}

	
