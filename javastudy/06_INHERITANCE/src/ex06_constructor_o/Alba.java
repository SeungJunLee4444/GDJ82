package ex06_constructor_o;

public class Alba extends Student {
	
	private String company;

	public Alba(String name, String school, String company) {
		super(name, school);
		this.company = company;
	}
	// # 자동으로 만들기 가능

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	


}
