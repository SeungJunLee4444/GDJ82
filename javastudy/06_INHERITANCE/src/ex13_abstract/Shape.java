package ex13_abstract;


// 추상메서드
// 1. 본문이 없는 메서드
// 2. 호출용으로 사용되는 메서드들
// 3. 중괄호{} 자체를 없애고, 세미콜론;을 추가함
// 4. public abstract(추천)  또는 abstract public 둘다 작동

// 추상클래스
// 1. 추상 메서드가 있으면 추상 클래스
// 2. public abstract class 처리
// 3. 본문이 없는 메서드를포함하기 때문에 객체생성이 불가
// 4. 추상 클래스를 상속받는 클래스는 "반드시" "모든" 추상 메서드를 오버라이징 해야함
 

public abstract class Shape {
	
	private String type;

	public Shape(String type) {	// # type값을 상속할수 있는 생성자 만들기
		super();				// (개발자 생성자)
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public abstract double getArea(); 	// # 넓이 구하는 메서드
	//	return 0;				// shape를 상속받는 객체들이 호출할 때 사용하는
								// 메서드들은 사용되지않는다 -> 추상메서드
								// 추상메서드 : 본문이 없는 메서드
//								// # 추상메서드 : 본문없애기, ; , abstract
	

}
