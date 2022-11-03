package quiz_03;

public class Main {
	
	public static void main(String[] args) {
		
		
	
		Bus bus = new Bus(25);  			// # 좌석 25개의 버스 배열생성
		bus.ride(1, new Person("kim"));
		bus.ride(1, new Person("lee"));			
		bus.ride(5, new Student("choi"));
		bus.ride(10, new Alba("min"));		// # Person, student, Alba 전부 이름값만
		bus.info();
		
		// 1) person 의 name 이 공통으로 포함
		// 2) seat에 저장되는건 사람 has 개념
		// => getter setter로 사람 꺼내보고, 앉히고 하기
		// * getter은 사람을 빼내기, setter은 사람을 앉히기
		
		
	}

}
