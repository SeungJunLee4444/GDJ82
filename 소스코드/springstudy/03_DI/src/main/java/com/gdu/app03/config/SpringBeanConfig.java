package com.gdu.app03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app03.domain.Notice;

// # 자바 컨테이너 생성
@Configuration
public class SpringBeanConfig {
	
	// 1) 메서드로 자바빈 만들기
	@Bean
	public Notice notice1() {				// <bean id="notice1" class="Notice">
		Notice notice = new Notice();
		notice.setNoticeNo(1);				// <property name="noticeNo" value="1"/>
		notice.setTitle("일반공지");		// <property name="title" value="일반공지"/>
		return notice;
	}
	
	// 2) 생성자로 자바빈 만들기
	@Bean
	public Notice notice2() {				// <bean id="notice2" class="Notice">
		return new Notice(2, "긴급공지");	// <constructor-arg value="2"/>
											// <constructor-arg value="긴급공지">
	}

}
