package com.gdu.app11.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// + @MapperScan : @Mapper로 등록된 인터페이스를 bean으로 등록할 수 있도록 @Mapper의 위치를 알려주는 에너테이션
@MapperScan(basePackages = "com.gdu.app11.mapper")


// # @EnableTransactionManagement : 트랜잭션 동작을 허용하는 에너테이션
@EnableTransactionManagement
// # @EnableAspectJAutoProxy : aspect를 자동으로 동작시키는 에너테이션
@EnableAspectJAutoProxy 	

// # @PropertySource : 프로퍼티 파일을 참조할 수 있는 에너테이션 -------------
@PropertySource(value = {"classpath:mybatis/config/mybatis.properties"})
@Configuration
public class DBConfig {
	
	// # db.properties 파일을 읽어서 변수에 저장하기
	// ${프로퍼티명}
	@Value(value = "${hikari.driver}")
	private String driver;
	
	@Value(value="${hikari.url}")
	private String url;
	
	@Value(value="${hikari.username}")
	private String username;
	
	@Value(value="${hikari.password}")
	private String password;
	
	
	// # hikariconfig : 히카리(cp) 설정 -------------------------------	<커넥션풀>
	@Bean
	public HikariConfig config() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driver);
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		return config;
	}
	
	// # hikari datasource
	@Bean(destroyMethod="close")
	public HikariDataSource dataSource() {
		return new HikariDataSource(config());
	}
	
	
	// # SqlSessionFactory :  ----------------------------------------------------
	@Bean
	public SqlSessionFactory factory() throws Exception {				// * 예외발생 : SqlSessionTemplace에 던져서 해결
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();		// - 이유: sqlsessionfactory를 어자피 template에서 호출하기때문
		// & dataSource(hikari cp) 정보 저장
		bean.setDataSource(dataSource());																					
		// & mapper의 .xml 파일 위치 저장 : 복수형으로 작성
		// - *.xml : 모든 xml
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("mybatis/mapper/*.xml"));  			
		// & mybatis config 파일의 위치 : 단수형으로 작성
		bean.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("mybatis/config/mybatis-config.xml"));	
		return bean.getObject();
	}			
	
	
	// SqlSessionTemplace(java의 sqlsession역할)
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(factory());
	}
	
	
	
	
	
	// # transactionmanager
	
	@Bean
	public TransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	}