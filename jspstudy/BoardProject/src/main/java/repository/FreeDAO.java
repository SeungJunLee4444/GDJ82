package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Free;



public class FreeDAO {
	
	// # 싱글턴
	private SqlSessionFactory factory;
	
	private static FreeDAO dao = new FreeDAO();
	
	private FreeDAO() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static FreeDAO getInstance() {
		return dao;
	}
	
	String mapper = "mybatis.mapper.free";
	
	// # 목록 -------------------------------------------------------------
	public List<Free> selectAllBoards() {
		SqlSession ss = factory.openSession();	
		List<Free> boards = ss.selectList(mapper + ".selectAllBoards");	
		ss.close();	
		return boards;
	}

	// # 상세보기 ----------------------------------------------------------
	public Free selectBoardByNo(long freeNo) {
		SqlSession ss = factory.openSession();
		Free free = ss.selectOne(mapper + ".selectBoardByNo", freeNo);
		ss.close();
		return free;
	}
	
	// # 조회수 증가
	public int updateHit(long freeNo) {
		SqlSession ss = factory.openSession();
		int result = ss.update(mapper + ".updateHit", freeNo); 
		ss.close();
		return result;
	}
	
	
	
	// # 게시글 삽입 ------------------------------------------------------
	public int insertBoard(Free free) {
		SqlSession ss = factory.openSession(false);	
		int result = ss.insert(mapper + ".insertBoard", free);
		if(result > 0) {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	// # 게시글 삭제 -------------------------------------------------------
	
	public int deleteBoard(long freeNo) {
		SqlSession ss = factory.openSession(false); 
		int result = ss.delete(mapper +".deleteBoard", freeNo);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}

	
	// # 게시글 수정 -------------------------------------------------------
	public int updateBoard(Free free) {
		SqlSession ss = factory.openSession(false); 
		int result = ss.update(mapper + ".updateBoard", free);
		if(result > 0) {
			ss.commit();
		}
		ss.close();
		return result;
	}
	
	// # 스케줄러
	public Free selectBoardTopHit() {
		SqlSession ss = factory.openSession(); 
		Free free = ss.selectOne(mapper + ".selectBoardTopHit");
		ss.close();
		return free;
	}
	


		
		
	

}
