package ex06_constructor_o;

public class StudentMain {

	public static void main(String[] args) {
		
		Student student = new Student("tom", "goodee");
		
		System.out.println(student.getName());
		System.out.println(student.getSchool());
		// # student의 메서드 뿐 아니라 부모 클래스인 school의 getname 메서드도
		// 호출할 수 있다
		// # ex06 => 개발자가 만든 생성자의 상속관계 만들기
		
		Alba alba = new Alba("jessica", "seoul univ", "library");
		System.out.println(alba.getCompany());
		System.out.println(alba.getSchool());
		System.out.println(alba.getName());

	}

}
