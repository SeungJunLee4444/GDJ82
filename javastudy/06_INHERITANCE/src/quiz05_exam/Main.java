package quiz05_exam;

public class Main {

	public static void main(String[] args) {

		Exam exam = new Exam("중간고사");
		exam.setScore(); // 국, 영, 수학 점수 0 ~ 100 사이 랜덤 생성
		
		Student student = new Student("emily");
		student.setExam(exam);
		
		student.info();
		 // 학생이름 : emily
		// 중간고사 성적
		// 국어 : 50, 영어 : 50, 수학 : 50, 총점 : 150, 평균 : 50.0점
		
		// & 정리
		// # 클래스 명과 다른 메서드가 있다는 것은, 직접 새로 만들어야할 메서드라는뜻
		// # 클래스 두개가 입력할 매개변수에 전할 값의 양식이 다른것
		// * 캡슐화 : info에 이름, 성적이라는 다른 타입의 입력값을 각 클래스에 맞게 별도로 작성
		
		// # void는 반환값이 없는 타입
		
		// ? 잘 안되는것
		// 캡슐화란?
		// => 관련이 있는 변수와 메서드들을 해당 클래스별로 묶어서 작성하는 것을 말한다
		// * 여기에 접근제어자를 사용해 함부로 객체를 이용해 필드값 정보를 열람하는 것을 방지
		// ex) 이 문제에서는 info의 성적부분을 exam에, 이후 student에서 학생이름과 exam.info 호출한 경우가 해당

		
		// 객체배열
		// => 클래스 객체를 배열타입으로 선언 후, 각 배열의 자리에 다시 클래스 객체를 저장하는 것을 말한다
		
	
	
	
	
	
	
	
	
	}

}
