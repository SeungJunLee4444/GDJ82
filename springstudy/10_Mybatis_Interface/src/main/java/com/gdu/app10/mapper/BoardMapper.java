package com.gdu.app10.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gdu.app10.domain.BoardDTO;

// # @Mapper 
// - 정의 : mybatis의 mapper와 직접 연결되는 interface 어노테이션
// - 기능 : @Mapper가 선언된 인터페이스의 메서드가 호출되면 매퍼의 쿼리문이 바로 호출된다
// - 주의 : mapper의 메서드 이름은 쿼리문의 id와 같아야한다

// @MapperScan : @Mapper을 찾는 용도, (DBConfig에 추가, @Mapper을 @bean으로 등록시켜준다)

@Mapper
public interface BoardMapper {
	public List<BoardDTO> selectAllBoards();
	public BoardDTO selectBoardByNo(int boardNo);
	public int insertBoard(BoardDTO board);
	public int updateBoard(BoardDTO board);
	public int deleteBoard(int boardNo);

}
