package repository;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Student;

public class StudentDAO {
	
	// DAO : DB에 직접 접근하는 클래스
	
	
	private SqlSessionFactory factory;
	
	
	private static StudentDAO dao = new StudentDAO();
	
	// 2) 외부에서 호출못하게 만들기
	private StudentDAO() {
		
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static StudentDAO getInstance() {
		return dao;
	}
	//----------------------------------------------------------------------------
	
	// # 메서드
	// & 반환하는 값이 여러개면 selectList 메서드, 하나면 selectOne 메서드
	// & 메서드의 경로는 mapper의 namespace + 각 쿼리 메서드의 id
	
	// # StudentListService 서비스 만들기
	
	String mapper = "mybatis.mapper.student.";	// => final 처리하기
	
	// 1. 학생목록
	public List<Student> selectAllStudents() {
		SqlSession ss = factory.openSession();
		// 1) sql.xml 문서에 연결한 객체 생성
		
		List<Student> students = ss.selectList(mapper + "selectAllStudents");
		// => mapper의 경로를 인수로 하는 selectList 메서드로 해당 xml파일의 쿼리문을 가져온다
		// 2) 가져온 쿼리문의 데이터를 list 타입의 students에 저장
		ss.close();
		return students;
	}
	
	// 2. 전체 학생수
	public int selectAllStudentsCount() {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne(mapper + "selectAllStudentsCount");
		ss.close();
		return count;
	}
	
	// 전체 평균
	public double selectAllStudentsAverage() {
		SqlSession ss = factory.openSession();
		double average = ss.selectOne(mapper + "selectAllStudentsAverage");
		ss.close();
		return average;
	}
	// => 매개변수가 없으면 메서드에 조건()을 사용할 필요가없다 
	
	///////////////////////////////////////////////////////////////////////////////
	// # 신규학생 등록하는 서비스 만들기
	
	
	// 신규학생 추가
	public int insertStudent(Student student) {
		SqlSession ss = factory.openSession(false);
		int result = ss.insert(mapper + "insertStudent", student);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	// # 평균범위에 따른 학생 조회 서비스 만들기
	
	// 5. 평균범위 조회
	public List<Student> selectStudentByAve(Map<String, Double> map) {
	// => 제네릭은 소문자 타입은 작성불가능
		SqlSession ss = factory.openSession();
		List<Student> students = ss.selectList(mapper + "selectStudentByAve", map);
		ss.close();
		return students;
	}
	
	// 6. 평균범위 조회 : 개수
	public int selectStudentByAveCount(Map<String, Double> map) {
		SqlSession ss = factory.openSession();
		int count = ss.selectOne("selectStudentByAveCount", map);
		ss.close();
		return count;
	}
	
	// 7. 평균범위 조회 : 평균
	public double selectStudentByAveAverage(Map<String, Double> map) {
		SqlSession ss = factory.openSession();
		double average = ss.selectOne(mapper + "selectStudentByAveAverage", map);
		ss.close();
		return average;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	// # 학생 삭제
	
	// 8. 학생삭제
	public int deleteStudent(int stuNo) {
		SqlSession ss = factory.openSession(false);
		int result = ss.delete(mapper + "deleteStudent", stuNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// # 상세보기
	
	public Student selectStudentByNo (int stuNo) {
		SqlSession ss = factory.openSession();									// db mapper에 연결
		Student student = ss.selectOne(mapper + "selectStudentByNo", stuNo);	// => stuNo : 전달해줄 파라미터값(매개변수)
		ss.close();
		return student;
	}
	
	
	// # 수정
	public int updateStudent(Student student) {
		SqlSession ss = factory.openSession();
		int result = ss.update(mapper + "updateStudent", student);
		if(result > 0 ) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	///////////////////////////////////////////////////////////////////////////////
	// # 스케줄러(TOP3) : 복수의 값을 반환하니 list
	public List<Student> selectStudentTop3() {
		SqlSession ss = factory.openSession();
		List<Student> top3 = ss.selectList(mapper + "selectStudentTop3");
		ss.close();
		return top3;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


