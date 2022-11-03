package quiz04_employee;

public abstract class Employee {
	
	// # quiz04 
	// 부모 - 시급지원, 월급직원
	
	private int empNo;
	private String name;
	
	public Employee(int empNo, String name) {
		this.empNo = empNo;
		this.name = name;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", name=" + name + "]";
	}
	
	public abstract int getPay();		// # 추상클래스로 만듬
	
	
	
	
	
	
	// * @data = getter, setter, tostring 롬복으로 한꺼번에 호출
	
	
	
	
	

}
