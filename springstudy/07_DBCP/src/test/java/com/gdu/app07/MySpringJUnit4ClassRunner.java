package com.gdu.app07;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.junit.runners.model.InitializationError;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// SpringJUnit4ClassRunner를 대신하는 클래스
// SpringJUnit4ClassRunner를 상속 받아서 만들어야 한다.

// SpringJUnit4ClassRunner를 상속받아 커스터마이징한 새로운 junit 클래스러너
public class MySpringJUnit4ClassRunner extends SpringJUnit4ClassRunner {

	public MySpringJUnit4ClassRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
		jndi();
	}

	private void jndi() {

		// # 작성의도
		// JNDI 방식으로 "java:comp/env/jdbc/oracle11g" 이름의 Resource를 호출하면,
		// 톰캣이 확인하는 context.xml을 참조하는 대신
		// 여기서 만든 BasicDataSource를 사용하시오.
		
		// # jndi는 dbcp 커넥션 정보를 자바가 아닌 was 톰캣 영역에 설정하는것을 말하며,
		// 여기서는 was 톰캣영역인 context.xml이 아닌 아래의 코드를 참조하도록 만들었다
		
		// ^ spring에 meta-inf가 없음
		
		try {
			
			// SimpleNamingContextBuilder는 현재 사용을 하지 않는 것이 좋다.
			SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
			builder.activate();
			
			BasicDataSource dataSource = new BasicDataSource();  // tomcat-dbcp 디펜던시가 필요하다.
			dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
			dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
			dataSource.setUsername("SCOTT");
			dataSource.setPassword("TIGER");
			
			JndiTemplate jndiTemplate = new JndiTemplate();
			jndiTemplate.bind("java:comp/env/jdbc/oracle11g", dataSource);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}