package ex12_object_class;

public class Main {

	public static void main(String[] args) {
		
		Object p = new Person();
		System.out.println(p.getClass());
		
		if(p instanceof Person) {			// # object로 person의 eat메서드 호출하
			((Person) p).eat();				// => person으로 다운캐스팅
		}									// * object클래스는 항상 다운캐스팅해서 사용해야함
	
		System.out.println(p);
		System.out.println(p.toString());	// # 16진수 주소값
		
		// # 새로운 person(tostring())  확인용
		Person person = new Person();
		person.setName("james");
		
		System.out.println(person);				// # james이 나오게 하는게 목표
		

		// # 새로운 Person(equals) 확인용)
		// 목표: name이 같으면 동일한 객체로 인식시키기
		Person p1 = new Person();
		Tatata p2 = new Tatata();
		p1.setName("김씨");
		p2.setName("김씨");
		System.out.println(p1.equals(p2));			// * object와 string비교에 쓰는 equals는 다르다
		// => string과 사용법이 동일
		
		// 오류: 동일한 이름이면 true야 하는데, 
		// 이유: object 클래스의 equals는 참조값을 비교한다(주소값), 이름을 비교한게아님
		// 해결: 오버라이딩으로 equals를 다운캐스팅해서 출력, String 값 필드에작성
		
		
		
	}
	
	

}
