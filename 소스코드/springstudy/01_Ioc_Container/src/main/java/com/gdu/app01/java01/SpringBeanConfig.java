package com.gdu.app01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

	// [@어노테이션 컨테이너]
	// 1. 구조
	// (1) @configuration : 스프링 컨테이너 
	// (2) @bean		  : 자바빈

	// 2. 이름짓는 방식
	// (1) 메서드명			: 가장 일반적인 방식, 자바빈의 id와 동일하다
	// (2) name 속성		:  @Bean(name="song2"), 이 경우 메서드명은 어떻게 지어도 상관없다

	// 3. 인젝션
	// - 개념 : 외부의 클래스(bean이 될) 객체를 생성해 현재 클래스(컨테이너) 내부에 저장하는것
	// - 방법
	// (1) setter 인젝션 : setter 메서드 사용
	// (2) 생성자 인젝션 : 생성자의 매개변수 사용, 생성자의 매개변수 순서대로 bean 생성()을 작성해줘야한다 (맨 밑 연습문제 참고)
		


@Configuration
public class SpringBeanConfig {
		
		
	
	/* 
	 	#3
	  	@Bean
	  	- bean을 만드는 메서드
	  	- 메서드 이름이 Bean의 이름(id)
	  	- 반환타입이 bean 타입(class)
	  	
	  	<bean id="song" class="Song"> 
	  		<property name="title" value="제목"/>
	  		<property name="genre" value="장르"/>
	  	</bean>
	 */
	@Bean
	public Song song1() {
		Song song = new Song();
		song.setTitle("제목1");
		song.setGenre("장르1");
		return song;
	}
	
	/* 
	  	@Bean
	  	- bean을 만드는 메서드
	  	- 메서드 이름이 Bean의 이름(id)
	  	- 반환타입이 bean 타입(class)
	  	
	  	<bean id="song2" class="Song"> 
	  		<property name="title" value="제목2"/>
	  		<property name="genre" value="장르2"/>
	  	</bean>
	 */
	@Bean(name="song2")											// #4 @bean에 이름을 주면, 메서드 이름은 아무거나 적어도 된다
	public Song sadkasldas() {
		Song song = new Song();
		song.setTitle("제목2");
		song.setGenre("장르2");
		return song;
	}
	
	/* 
	  	@Bean
	  	- bean을 만드는 메서드
	  	- 메서드 이름이 Bean의 이름(id)
	  	- 반환타입이 bean 타입(class)
	  	
	  	<bean id="song3" class="Song"> 
	  		<constructor-arg value="제목3"/>
	  		<constructor-arg value="장르3"/>
	  	</bean>
	 */
	@Bean											// #4 @bean에 이름을 주면, 메서드 이름은 아무거나 적어도 된다
	public Song song3() {
		return new Song("제목3", "장르3");
	}
	//=================================================================================================================
	
	// 연습 : song1을 가지는 singer1을 만들어보자
	// setter injection 사용
	
	@Bean
	public Singer singer1() {
		Singer singer = new Singer();
		singer.setName("가나다");
		singer.setSong(song1());
		return singer;
	}
	
	// 해결 : setSong()안에 song1()메서드를 호출하면된다
	// * setter방식이라 set메서드 사용
	
	//------------------------------------------------------------------------------------------------
	
	
	// 연습 : song2을 가지는 singer2을 name값으로
	// setter injection 사용
	
	@Bean(name="singer2")
	public Singer skdkasdlsak() {
		Singer singer = new Singer();
		singer.setName("가나다");
		singer.setSong(sadkasldas());
		return singer;
	}
	
	// * setter방식이라 set메서드 사용
	// * name값 사용
	
	//------------------------------------------------------------------------------------------------
	
	// 연습 : song3을 가지는 singer3을 name값으로
	// 생성자 injection 사용
	
	@Bean
	public Singer singer3() {
		return new Singer("가수3", song3());
	}
	// => 매개변수 생성자를 사용한 방법이기 때문에 new Singer("가수3", song3()); 의 형태를 지님
			
		
	
		
	

}
