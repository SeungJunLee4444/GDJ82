package com.gdu.ex.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.gdu.ex.domain.ContactDTO;


// # dao : @compotent 처리
@Repository
public class ContactDAO {
	
	// # 오라클 드라이버 획득 ----------------------------------------------------------------
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;	// * 기능 : 결과값 표현, select문에서 사용 -- *
	private String sql;

	// * resultset은 반환하는 값이 dto일때 dto에 반환값을 저장하기 위해 사용한다, 즉 select문에서 사용된다
	
	
	// # 오라클 드라이버 + 커넥션 얻기 메서드
	// & private인 이유 : 이 메서드들은 boardDAO에서만 쓴다
	private Connection getConnection() {
		Connection con = null;
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOTT", "TIGER");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	// # 연결닫는 메서드 생성 -----------------------------------------------------------------
	private void close() {
		try {
			if(rs != null) {rs.close();}
			if(ps != null) {rs.close();}
			if(con != null) {rs.close();}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// # 전체 조회 ===========================================================================
	public List<ContactDTO> selectAllContact() {
		List<ContactDTO> contacts = new ArrayList<>();
		try {
			con = getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT ORDER BY NO DESC";
			ps = con.prepareStatement(sql);	// - 사전실행
			rs = ps.executeQuery();	
			while(rs.next()) {
				ContactDTO contact = new ContactDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				contacts.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return contacts;
	}
	
	// # 연락처 추가 ===========================================================================
	// * 추가는 반환값이 int, resultSet를 사용하지않는다
	public int insertContact(ContactDTO contact) {
		int result = 0;
		try {
			con = getConnection();
			sql = "INSERT INTO CONTACT(NO, NAME, TEL, ADDR, EMAIL, NOTE) "
					+ "VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";		
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getAddr());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getNote());
			result = ps.executeUpdate();	
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
		
		
		
		
		
		
		
		
		
	
	
	

}
