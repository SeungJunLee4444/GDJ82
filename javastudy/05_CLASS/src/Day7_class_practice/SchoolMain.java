package Day7_class_practice;

public class SchoolMain {

	public static void main(String[] args) {
		
		// 학교이름
		School school = new School(); 	// => 클래스 사용을 위해 객체 선언
		school.name = "경인중학교";		// => 객체를 이용해 클래스에 담긴 name 속성을 사용해 값을 입력 
										// * name의 속성을 String이 아닌 school로 했음 => 타입이 맞지 않으면 오류 *
		
		// 학생정보
		Student student1 = new Student();	//
		student1.name = "이승준";
		student1.number = "123456";
		
		Student student2 = new Student();	//
		student2.name = "장준익";
		student2.number = "148026";
		
		// * 연결작업
		school.students[0] = student1;
		school.students[1] = student2;
		
		// 학생정보 출력
		for(int i = 0; i < school.students.length; i++) { 	// * []는 애초에 length 뒤에 안쓴다
			System.out.println(school.students[i].number);	// School에 student 클래스가 선언되있음(연결)
			System.out.println(school.students[i].name);		
		}
		// => 값이 출력 안된다 (nullpointexception오류: 호출할 값이 없음)
		// * school클래스에서 선언된 배열에는 값이 없기 때문 != 현 위치에서 선언된 student클래스의 값들
		// ==> 따라서 이 둘을 연결시켜주기 위한 작업이 필요하다

	}

}
