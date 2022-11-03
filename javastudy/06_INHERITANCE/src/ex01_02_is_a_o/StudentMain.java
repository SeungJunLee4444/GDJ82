package ex01_02_is_a_o;

public class StudentMain {

	public static void main(String[] args) {
		
		Student student = new Student();
		
		student.eat();
		student.walk();
		student.sleep();
		student.study();
		// # student는 부모클래스 person의 모든 메소드를 상속한다 *

	}

}
