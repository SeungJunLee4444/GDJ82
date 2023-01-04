package memberDao;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Member;

public class MemberDao {
	
	// SqlSessionFactory 빌드
	private SqlSessionFactory factory;
	
	// 1. DAO 싱글턴패턴
	// 1) 내부에 생성자 호출
	private static MemberDao dao = new MemberDao();
	
	// 2) 외부에서 호출못하게 만들기
	private MemberDao() {
		// (1) dao 호출시 factory로 생성(mybatis 사이트 : XML에서 SqlSessionFactory 빌드하기)
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 3) 인스턴스를 호출
	public static MemberDao getInstance() {
		return dao;
	}
	
	String mapper = "mybatis.mapper.member.";
	
	
	//------------------------------------------------------------------------------
	// 2. 로그인하기
	
	public Member login(Member member) {
		SqlSession ss = factory.openSession();
		Member login = ss.selectOne(mapper + "login", member);
		ss.close();
		return login;	
	}
	
	//------------------------------------------------------------------------------
	
	// 3. 등록하기 : register 서비스 요청 
	// dto member에 저장된 값을 저장
	public int insertMember(Member member) {		
		SqlSession ss = factory.openSession();
		int result = ss.insert(mapper + "insertMember", member); 
		
		// # 성공시 커밋
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	//------------------------------------------------------------------------------
	// 4. 삭제하기
	public int deleteMember(int memberNo) {
		SqlSession ss = factory.openSession();
		int result = ss.delete(mapper + "deleteMember", memberNo); 
		
		// # 성공시 커밋
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
