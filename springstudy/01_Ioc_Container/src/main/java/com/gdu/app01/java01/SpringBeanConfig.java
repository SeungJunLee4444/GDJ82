package com.gdu.app01.java01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

	// [@어노테이션 컨테이너]
	// 1. 규칙순서
	// (1) 자바클래스에 @configuration 사용
	// (2) 자바빈에 @bean 사용


	// 2. 이름짓는 방식
	// (1) 메서드명			   : 가장 일반적인 방식
	// (2) @Bean(name="song2") : 메서드명은 아무렇게 지어도 상관없다			
	
	// * 자바빈의 메서드명은 자바빈의 id와 동일하다 => main에서 호출시 유의 ***
	

	// 3. 자바빈 인젝션
	// (1) setter 인젝션 : setter 메서드 사용
	// (2) 생성자 인젝션 : 생성자의 매개변수 순서에 맞게 return(매개변수1, 매개변수2...)를 반환한다


	// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&---------------------------보완필요
	// & 인젝션 개념
	// - 개념 : 인젝션(주입)은 외부에서 클래스 객체를 생성하여 해당 객체를 클래스 내부에 저장하는것
	// - 개인생각 : 클래스 안에 클래스를 담은 상태
	// => 문제 : 만약 포함하고 있는 클래스가 다른 클래스로 대체되어야할 경우에는?

	//----위와 아래는 다른개념인것 같다?

	// # dependancy injection(DI) : 의존성주입
	// - 개념 : 클래스간의 의존성을 가지는 것을 말한다
	// - 방법 : 의존성을 지닌 클래스를 인터페이스화하여 포함하고 있는 클래스가 변해도 알아서 참고하도록 의존관계를 생성?

	//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
	
	
	



	// #1 자바를 이용해서 xml을 처리하는 역할을 수행할 수 있다
	// * xml에서 자바로 변하는 추세이다
	
	/*
	 	#2 
	 	@Configuration
	 	- Bean을 만드는 java 파일
	 	- 특징 : Spring bean configuration file하고 하는일이 같다(xml)
	 */
	
	@Configuration
	public class SpringBeanConfig {
		
		// 규칙 : 메서드 하나당 bean 하나를 생성한다	
	
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
