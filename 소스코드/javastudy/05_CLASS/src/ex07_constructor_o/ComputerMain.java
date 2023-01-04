package ex07_constructor_o;

public class ComputerMain {

	public static void main(String[] args) {
		
		// # 디폴트 생성자 선언
		//Computer myCom2 = new Computer();
		// => 2. 디폴트 생성자의 형태
		
		// # 직접 만든 생성자 메서드 선언
		Computer myCom = new Computer("gram", 150);
		// => 인수를 입력해 초기값 저장
		// * 이를 통해 선언시 필드값 초기화 용도로 사용
		
		myCom.printComputerStatus();
		// => printComputerStatus 메소드에 실행문을 만듬 *
		
		Computer yourCome = new Computer();
		// 오류발생
		// 생성자가 만들어져 있기 때문에 매개변수가 없는 별도의 메소드가 필요
		// => 메소드 오버로드를 이용해 해결 *
		yourCome.printComputerStatus();
		
	}

}
