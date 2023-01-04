package com.gdu.app01.xml05;

import java.sql.Connection;
import java.sql.DriverManager;


public class MyConnection {
	
	private String driverClassName;
	private String url;
	private String user;
	private String password;

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// # connection 반환 메서드
	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("커넥션 생성완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return con;
		
	}
	
}
