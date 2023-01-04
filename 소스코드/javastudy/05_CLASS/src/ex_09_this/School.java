package ex_09_this;

public class School {
	
	// 1. 필드
	private Student[] students;
	private int idx; // 0, 
	// = student 배열의 인덱스이자, student 배열에 저장된 학생수와 같다
	
	//-----------------------------------------------
	
	// 2. 생성자
	public School(int cnt) {
		students = new Student[cnt]; 
	} 
	// (1) school 생성자를 만들고
	// (2) cnt 정수값이 배열의 길이가 되는 student 클래스 선언 *
	
	
	// & 생성자 메소드는 반환타입 없음 *
	
	//-----------------------------------------------
	
	// 3. 메소드
	public void addStudent(Student student) {
	
		if(idx == students.length) {
			System.out.println("Full");
			return; 
			// # 배열의 길이가 인덱스값과 같으면 새로운 배열을 만들지 말고 나가기
			// => 아래의 배열에 값 추가 코드를 실행시키지 않음
			
			//* break는 해당if문만 종료, return은 메소드 자체를 종료시킴 *
				
		}
		students[idx++] = student; 
		// # 입력된 student 클래스의 값을 students배열에 저장하고 idx 하나 증가
		// => 2명의 학생 추가, idx : 0 -> 2
		
	}
	public void printStudents() {
//		for(int i = 0; i < idx; i++) { // idx로하면 학생수가 0일떄 오류 x
//			System.out.println(students[i].getName() + ", " + students[i].getStuNo());
//		}
		// * 길이를 length 대신 idx를 한 이유
		// # 배열의 길이가 2고 값이 없는 배열인 상태
		// ??????
		
		
		// * 향상 for문
		for(Student student : students) { 
			if(student != null) {
				System.out.println(student.getName() + ", " + student.getStuNo());
			}
			// # 향상 for문, student 배열 중에서 안에 값이 없는게 아니라면 실행
		}
		
	}
}
