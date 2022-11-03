package quiz05_exam;

public class Student {
	
	private String name;
	private Exam exam;	// # 서로 생성자 매개변수가 다름
	
	public Student(String name) {
		this.name = name;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	public void info() {
//		int total = exam.getEng() + exam.getKor() + exam.getMat();
		
		System.out.println("학생이름 :" + name);
//		System.out.println("중간고사 성적");
//		System.out.println("국어 :" + exam.getKor() + "영어 :" + exam.getEng() + "수학 :" + exam.getMat());
//		System.out.println("총점 :" + total);
//		System.out.println("평균 :" + total / 3.0);
		exam.examInfo(); 
		// # exam에 만든 성적 메서드를 호출
		
		// # 성적관련 이야기니 캡슐화의 논리에 따라 exam 클래스 내에 작성해야함
		
		
	}
	
	
	

}
