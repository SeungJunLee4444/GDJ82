package repository;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import domain.Board;

public class BoardDao {
	
	private SqlSessionFactory factory;
	
	private static BoardDao dao = new BoardDao();
	
	private BoardDao() {
		try {
			String resource = "mybatis/config/mybatis-config.xml";
			InputStream in = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardDao getInstance() {
		return dao;
	}
	
	// 1. 게시글 목록 조회
	public List<Board> selectAllList() {
		SqlSession ss = factory.openSession();	
		List<Board> boards = ss.selectList("mybatis.mapper.board.selectAllList");
		ss.close();	
		return boards;
	}
	
	// 2. 게시글 상세보기	
	public Board selectBoardByNo(long boardNo) {
		SqlSession ss = factory.openSession();
		Board board = ss.selectOne("mybatis.mapper.board.selectBoardByNo", boardNo);
		ss.close();
		return board;
	}
	
	
	// 2. 게시글 추가하기
	public int insertBoard(Board board) {
		SqlSession ss = factory.openSession(false);	
		int result = ss.insert("mybatis.mapper.board.insertBoard", board);
		if(result > 0) {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	// 3. 게시글 수정
	public int updateBoard(Board board) {
		SqlSession ss = factory.openSession(false);
		int result = ss.update("mybatis.mapper.board.updateBoard", board);	
		if(result > 0) {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	// 4. 게시글 삭제
	public int deleteBoard(int boardNo) {
		SqlSession ss = factory.openSession(false);	
		int result = ss.delete("mybatis.mapper.board.deleteBoard", boardNo);
		if(result > 0) {
			ss.commit();
			ss.close();
		}
		return result;
	}
	
	
	
	
	
	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


