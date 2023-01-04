package test;

import static org.junit.Assert.*;

import org.junit.AfterClass;

import org.junit.BeforeClass;
import org.junit.Test;

import domain.Student;
import repository.StudentDAO;

public class StudentTest {
	
	/* 
	 * [JUNIT5]
	 * - junit5 주소 		: https://junit.org/junit5/
	 * - 자바독 참고문서 	: https://junit.org/junit4/javadoc/latest/index.html
	 * 
	 * - 작업예시
	 * & db 초기화 진행후 작업
	  순서대로 전체 테스트 돌리기
	  1. 삽입
	  	: 테스터, 50, 50, 50
	  2. 목록
	  3. 상세
	  4. 수정
	  	: 테스터2, 60, 60, 60
	  5. 삭제
	  	: 테스터2
	  	
	  & 테스트 사용법
	   * 1) @TEST 하나하나 해보기
	   * 2) @BeforeClass static + @Test + @AfterClass static 쓰기
	   * => beforeclass와 afterclass
	   * 
	  & @before, @after과 @beforeclass, @afterclass의 차이
	  1) @before,after은 실행시 속한 @test의 개수만큼 작동한다
	  2) @beforeclas, @afterclass는 오직 한번만 작동한다
	  	
	 */

	@BeforeClass
	public static void 삽입테스트() {
		// @beforeclass : 클래스 실행시 한번만 먼저 실행
		// static 처리
		
		Student student = new Student();
		student.setName("테스터");
		student.setKor(50);
		student.setEng(50);
		student.setMath(50);
		student.setAve(50);
		student.setGrade("f");
	
		int result = StudentDAO.getInstance().insertStudent(student);
		assertEquals(1, result);
		// => 여기서 1은 성공시 SQL의 반환수 1
	}
	
	@Test
	public void 목록테스트() {
		// 삽입 이후의 숫자를 반영
		assertEquals(1, StudentDAO.getInstance().selectAllStudents().size());
		// => 삽입한 이후의 숫자 = 조회한 모든 학생의 수
		// => 여기서 1은 목록의 전체 인원수를 의미
	}
	
	@Test
	public void 상세테스트() {
		assertNotNull(StudentDAO.getInstance().selectStudentByNo(1));
	}
	// 여기서 1은 학번을 의미
	
	@Test
	public void 수정테스트() {
		Student s = Student.builder()
				.name("테스터2")
				.kor(60)
				.eng(60)
				.math(60)
				.ave(60)
				.grade("c")
				.stuNo(1)	// => 이것도 학번을 의미
				.build();
		assertEquals(1, StudentDAO.getInstance().updateStudent(s));
	}
	
	// @AfterClass : 클래스 실행시 한번만 먼저 실행
	// static 처리

	@AfterClass
	public static void 삭제테스트() {
		assertEquals(1, StudentDAO.getInstance().deleteStudent(1));
		// 앞에는 SQL의 성공시 반환 1, 뒤에는 학번 1
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
