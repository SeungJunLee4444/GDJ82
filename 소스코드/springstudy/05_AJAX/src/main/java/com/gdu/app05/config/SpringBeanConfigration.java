package com.gdu.app05.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app05.service.BoardService;
import com.gdu.app05.service.BoardServiceImpl;
import com.gdu.app05.service.ContactService;
import com.gdu.app05.service.ContactServiceImpl;
import com.gdu.app05.service.GalleryService;
import com.gdu.app05.service.GalleryServiceImpl;
import com.gdu.app05.service.MovieService;
import com.gdu.app05.service.MovieServiceImpl;

// # 자바 컨테이너 configuration 어노테이션
@Configuration
public class SpringBeanConfigration {

	// # @autowired 처리를 위한 자바빈 컨테이너
	
	// # 자바빈으로 boardservice를 등록해서 boardserviceimpl을 반환
	@Bean
	public BoardService boardService() {
		return new BoardServiceImpl();
	}
	
	// # movie 서비스처리
	@Bean
	public MovieService movieService() {
		return new MovieServiceImpl();
	}
	
	// # contact 서비스 처리
	@Bean
	public ContactService contactService() {
		return new ContactServiceImpl();
	}
	
	// # gallery 서비스 처리
	@Bean
	public GalleryService galleryService() {
		return new GalleryServiceImpl();
	}
	
}
