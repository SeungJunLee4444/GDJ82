package com.gdu.app08.config;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@EnableTransactionManagement	//  # @EnableTransactionManagement : 트랜잭션 동작을 허용하는 에너테이션 ---- +

@EnableAspectJAutoProxy 	// # @EnableAspectJAutoProxy : aspect를 자동으로 동작시키는 에너테이션 ---- +

// # 자바빈 컨테이너 

@Configuration
public class DataBaseConfig {
	
	// # SpringJdbc 처리를 위한 DriverManagerDataSource와 JdbcTemplate을 Bean으로 등록한다 ---- +
	
	// # DriverManagerDataSource
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("SCOTT");
		dataSource.setPassword("TIGER");
		return dataSource;
	}
	
	// # JdbcTemplate : dao에서 con, rs, ps 처리
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());	// * dataSource() 넣어주기
	}
	// * JdbcTemplate는 dataSource()가 필요하다
	
	
	// # 트랜잭션 처리를 위한 transactionManager를 bean으로 등록한다 ---- +
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	// * TransactionManager는 인터페이스라 DataSourceTransactionManager 구현체를 사용한다
	// * 트랜잭션에는 dataSource 정보가 필요하다
	
	
	// # 트랜잭션 인터셉터를 bean으로 등록 ---- +
	@Bean
	public TransactionInterceptor transactionInterceptor() {
		
		// & 모든 exception이 발생하면 rollback를 수행하시오
		RuleBasedTransactionAttribute attribute = new RuleBasedTransactionAttribute();
		attribute.setName("*");
		attribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		source.setTransactionAttribute(attribute);
		
		return new TransactionInterceptor(transactionManager(), source);
		
	}
	
	// # 트랜잭션 인터셉터를 advice로 등록하는 advisor를 bean으로 등록한다 ---- +
	@Bean
	public Advisor advisor() {
		// & 언제 advice를 동작시킬 것인가? : pointcut을 결정하라는 의미
		
		// * 포인트컷 표현식
		/* 
		 * - 형식
		  	execution(리턴타입, 패키지.클래스.메서드(매개변수))
		   - 의미
		   	 1) 리턴타입
		   	 	(1) *
		   	 	(2) void
		   	 	(3) !void
		   	 2) 매개변수
		   	 	(1) .. : 모든 매개변수
		   	 	(2) *  : 1개의 모든 매개변수
		 */
		AspectJExpressionPointcut pointCut = new AspectJExpressionPointcut();
		pointCut.setExpression("execution(* com.gdu.app08.service.*Impl.*Transaction(..))");
		
		return new DefaultPointcutAdvisor(pointCut, transactionInterceptor());
		
	}
	
	// & 트랜잭션 실패시 500번 발생
		
		
		
		
		
	
	
}
