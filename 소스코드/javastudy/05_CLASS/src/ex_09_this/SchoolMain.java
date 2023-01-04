package ex_09_this;

public class SchoolMain {

	public static void main(String[] args) {
		
		// 1. student 클래스 선언과 필드값 입력
		
		Student student1 = new Student();
		// # student 클래스 (학번, 이름)을 지닌 생성자 1 만들기
		
		student1.setStuNo("11025");
		student1.setName("전지현");
		
		Student student2 = new Student("11925", "정우성");
		// # student 클래스 (학번, 이름)을 지닌 생성자 2 만들기

		
		// --------------------------------------
		
		// 2. school 클래스 생성
	
		School school = new School(2); 					
		// # school 클래스의 생성자는 매개변수값을 길이로 하는 student 배열을 형성
		
		school.printStudents();
		school.addStudent(student1);
		school.addStudent(student2);
		// # 길이 2의 school 클래스의 students 배열에 배열값 두개 추가
	
		
		school.printStudents();
		
	}

}
