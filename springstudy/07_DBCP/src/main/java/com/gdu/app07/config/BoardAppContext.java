package com.gdu.app07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app07.repository.BoardDAO;
import com.gdu.app07.service.BoardService;
import com.gdu.app07.service.BoardServiceImpl;

@Configuration
public class BoardAppContext {
	
	// # 자바 컨테이너 : @repository 대신 만든 컨테이너
	
	@Bean
	public BoardDAO dao() {
		return new BoardDAO();
	}
	
	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl(dao());
	}

}
