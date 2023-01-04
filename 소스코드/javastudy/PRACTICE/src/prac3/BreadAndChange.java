package prac3;

public class BreadAndChange {

	// 필드
	private int bread;   // 빵
	private int change;  // 잔돈
	
	// 생성자
	public BreadAndChange() {
		
	}
	public BreadAndChange(int bread, int change) {
		super();
		this.bread = bread;
		this.change = change;
	}
	
	// 메소드
	public int getBread() {
		return bread;
	}
	public void setBread(int bread) {
		this.bread = bread;
	}
	public int getChange() {
		return change;
	}
	public void setChange(int change) {
		this.change = change;
	}
	
}
