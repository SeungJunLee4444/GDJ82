package quiz04_employee;

public class Regular extends Employee {
	
	private int salary;

	public Regular(int empNo, String name, int salary) {
		super(empNo, name);
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Regular [salary=" + salary + "]";
	}
	// *1 오버라이딩되서 regular의 tostring만 사용된다, 막 생성시 regular의 필드변수인 salary만 출력
	// *2 employee의 tostring도 받기 위해, super.tostring + salary로 변경
	// *3 상속관계 : object - employee - regular
	
	@Override
		public int getPay() {
			return salary;
		
	


	}
	
	

}
