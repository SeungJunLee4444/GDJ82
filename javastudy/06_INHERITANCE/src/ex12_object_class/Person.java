package ex12_object_class;

public class Person {
	
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void eat() {
		System.out.println("먹는다");
	}
	@Override
	public String toString() {
		return "이름 : " + name;
		// => tostring도 source에서 자동생성이 가능하다, 실무에서도 자동으로 사용
		// 필드값 보여주기, 오류찾기에 사용
				
		// object 클래스의 tostring 매서드를 오버라이딩함(부모클래스 메서드기떄문)
		// => System.out.println(person); 에서 사용
	}
	
	
	@Override
	public boolean equals(Object anObject) {
		Person p = (Person) anObject;	// 다운캐스팅
		return name.equals(p.name);
		// 앞에 name은 p1의 네임, 뒤에는 anObject에 들어간 p2가 p로 바뀐 이후의 네임
	}
	
}
