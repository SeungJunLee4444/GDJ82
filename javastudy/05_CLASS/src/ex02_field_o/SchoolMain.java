package ex02_field_o;

public class SchoolMain {

	public static void main(String[] args) {
		
		// # school 클래스 선언
		School school = new School();
		school.name = "경인중학교";
		// => name set 필드값 저장
		
		
		// # student 클래스 선언1
		Student student1 = new Student();
		student1.name = "전지현";
		student1.stuNo = "11023";
		// => name, stuNo string 타입 필드값 저장1
		
		// # student 클래스 선언2
		Student student2 = new Student();
		student2.name = "정우성";
		student2.stuNo = "14923";
		// => name, stuNo string 타입 필드값 저장2
			
		
		// # school 클래스의 students 필드 객체의 배열값으로,
		// student1, 2에 저장된 필드값을 참조
		school.students[0] =student1;
		school.students[1] =student2;
		
	
		// * 클래스 선언 유의점
		// student의 필드값은 name과 stuNo
		// school의 필드값은 name과 students배열
		// * Main에서 선언된 school과 student는 전부
		// 객체 선언으로 사용하고 있다
		
	
		// # 학생이름 출력
		for(int i = 0; i < school.students.length; i++) {
			System.out.println(school.students[i].stuNo);
			System.out.println(school.students[i].name); 
			
		// * 유의점
		// & school 클래스에서 선언된 students 객체는 student 클래스를 호출한 것이다
		// => 즉 student 클래스의 필드변수인 stuNo와 name도 가져올 수 있다
			
		// * 클래스가 상속되있다면, 메소드나, 필드값을 한번에 불러올 수 있다 *	
		// => school.students[i].name		
		}
	}

}
