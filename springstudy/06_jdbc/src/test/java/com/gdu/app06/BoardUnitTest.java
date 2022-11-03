package com.gdu.app06;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gdu.app06.domain.BoardDTO;
import com.gdu.app06.repository.BoardDAO;

// # test용 어노테이션(junit) : pom.xml에 내장되있음
// - 기능 : Junit4를 이용한 테스트를 수행

@RunWith (SpringJUnit4ClassRunner.class)


// # 컨테이너별 경로 찾는법 3가지 -----------------------------------------------------------------------
// 1. root-context.xml에 <bean> 태그를 작성한 경우
// - @ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

// 2. com.gdu.app06.config.SpringBeanConfig.java에 @Bean을 작성한 경우
// - @ContextConfiguration(classes={SpringBeanConfig.class})

// 3. component-scan과 컴포넌트(@Component, @Service, @Repository 등)를 이용한 경우
// - @ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})

// # 3번째 방법 @component식 사용하기
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})	// * 속성이름에 s가 들어간다는건 배열이 사용된다는 뜻


public class BoardUnitTest {
	
	// # DAO단위로 unit 테스트 진행하기 ===============================================================
	// - boardDAO가 필요하다
	
	@Autowired
	private BoardDAO dao;
	
	// @Test : 테스트 실행 에너테이션
	public void 삽입테스트() {
		BoardDTO board = new BoardDTO(0, "제목", "내용", "작성자", "생성일", "수정일");
		// & assertEquals : 예상한 결과값과 동일한지 
		assertEquals(1, dao.insertBoard(board));	// - dao.insertBoard의 결과를 1만 기대한다
	}
	
	@Test
	public void 조회테스트() {
		// & assertNotNull : null이 아닌지 확인
		assertNotNull(dao.selectBoardByNo(1));	// - 1번 조회
	}

}
