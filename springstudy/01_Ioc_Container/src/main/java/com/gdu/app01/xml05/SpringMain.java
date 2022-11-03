package com.gdu.app01.xml05;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMain {
	
	public static void main(String[] args) throws Exception {	// => myconnection try,catch문 예외처리
		
		// [5장 : connection(스프링 jdbc연결) 빈으로 저장해서 연결 가져오기
		// * ojdbc.jar 등록 필요
		
		// # 스프링 컨테이너 연결
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("xml05/appCtx.xml");
		
		// # 커넥션 클래스 가져오기
		MyConnection myCon = ctx.getBean("conn", MyConnection.class);
		Connection con = myCon.getConnection();
		
		// - 사용후 종료
		if(con != null) {	
			con.close();
			System.out.println("커넥션 해제 완료");
		}
		
		ctx.close();
		
		
	}

}
