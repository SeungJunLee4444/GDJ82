package ex10_downcasting_o;

public class Main {

	public static void main(String[] args) {
		
		// #1 업캐스팅
		
		// & 정리(ex10, ex11)
		// #1 업캐스팅
		Person p = new Student();	
		p.eat();
		// # p는 인스턴스
		// # 업캐스팅은 서브클래스를 슈퍼클래스로 캐스팅하는 것을 말한다
		// # 업캐스팅되면 기본적으로 person 클래스의 멤버들만 호출할 수 있다
		// => 다만 오버라이징, 다운캐스팅을 통해 자식클래스의 멤버들도 호출할 수 있다
		
		// * 업캐스팅된 객체의 자식 클래스의 멤버를 호출하는 법
		// (1) 오버라이징
		// (2) 다운캐스팅
		
		
		// #2 다운캐스팅 : instanceof 연산자
		// # 특정 인스턴스가 어떤 클래스 타입인지 정돈하는 연산자
		// => 해당 클래스면 true, 아니면 false를 반환
		
		System.out.println(p instanceof Person);
		System.out.println(p instanceof Student);
		System.out.println(p instanceof Alba);
		// # p는 person 클래스와, person을 상속하는 student 클래스의 객체이다
		// => 객체다는, '특정 클래스를 참조하는가?' 라는 뜻이다
		// # alba는 선언되있지 않다
		
		// p. // # study가 없음
		
		// * p가 Student 타입의 인스턴스이면 study() 메서드를 호출
		if(p instanceof Student) {
			((Student) p).study();
		}	
		// => p가 student 클래스를 참조하면,
		// => student 다운캐스팅, study 메서드 호출
			
		if(p instanceof Alba) {
			((Alba) p).work();
			// => p를 alba로 바꾸고 alba를 호출
		}
		// => p가 alba 클래스를 참조하면, 
		// => alba로 다운캐스팅, work 메서드 호출
	}

}
