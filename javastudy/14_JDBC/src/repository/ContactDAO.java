package repository;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.ContactDTO;

public class ContactDAO {
	
	
	
	
	
	// ------------------------------------singleton pattern---------------------------------------------------
	
	//  DAO
	// - DB에 접근하는 객체는 오직 하나 뿐이야함
	// * singleton pattern이어야함
	// * DB에 접근하는 객체는 오직 하나뿐이어야함
	// * 만들어진 객체를 가져다 쓰는것만 가능
	// * 외부에서는 새로운 객체를 생성할 수 없음
	// DAO는 하나의 객체만 생성할 수 있도록, singleton pattern으로 생성
	// (INSERT문과 SELECT문이 동시에 이루어질 수 없기 때문)
	
	// 1. ContactDAO 객체를 하나 만들어둔다
	private static ContactDAO contactDAO = new ContactDAO();			// (1.클래스 내부에 해당 클래스의 객체를 생성)
	
	// 2. 외부에서는 ContactDao 객체를 못만들도록 제한한다
	
	private ContactDAO() {												// (2.private 제한자로 외부에서 생성자 호출 x)
		
	}
	
	// 3. 만들어둔 ContactDAO 객체를 외부에 반환한다
	
	public static ContactDAO getInstance() {							// (3.클래스 내에서 만든 객체를 외부에 반환)
		return contactDAO;												// static은 static 필드값만 호출할 수 있다(static과 관련된것들만 가능)
		
	}
	

//	public static void main(String[] args) {
//		
//	// 4. ContactDAO 객체 만들기
//	ContactDAO dao1 = ContactDAO.getInstance();	
//	ContactDAO dao2 = ContactDAO.getInstance();	
//		
//	System.out.println(dao1 == dao2);			// 같음, 객체의 주소값을 달라야 하지만, 클래스기 때문에 동일
//		
//	}
	
	// ------------------------------------field---------------------------------------------------
	
	// 5. 데이터베이스에 접근할 때 사용하는 공통 요소
	private Connection con;								// db접속
	private PreparedStatement ps;						// 쿼리문 실행
	private ResultSet rs;								// select문 결과
	private String sql;									// 쿼리문
	private int result;									// insert, update, delete 결과
	
	
	
	// ------------------------------------method---------------------------------------------------
	
	// 6. 모든 데이터베이스 작업(CRUD : CREATE/READ/UPDATE/DELETE)의 공통작업은 2가지
	//(CREATE가 INSERT, READ가 SELECT를 의미)
	// 1) Connection 객체생성
	// 2) close
	
	// 1) Connection 메서드
	
	public Connection getConnection() throws Exception {	// (예외처리방법 : (1) 내부에서 try-catch 							x
															// 				   (2) throws해서 메서드 사용하는 곳에서 try-catch)	o
		//(1) OracleDriver 클래스 로드								=> (2) 예외들은 전부 CRUD 작업에서 try-catch 처리
		
		Class.forName("oracle.jdbc.OracleDriver");
		
		// (2)Connection 객체 반환
		// db.properties 파일에 접속정보 읽기
		Properties p = new Properties();
		p.load(new FileReader("db.properties"));			// (경로없는 파일은 프로젝트 디렉터리에 있다는 뜻, 14_JDBC//db.properties)
															// (url, 아이디, 비번 파일은 텍스트 파일이니 reader)
		String url = p.getProperty("url");
		String user = p.getProperty("user");
		String password = p.getProperty("password");
		return DriverManager.getConnection(url, user, password);
		
															// (개인정보가 전혀 안보이는 상태)
	}
	
	// 2) close 메서드
	public void close() {
		try {
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			ContactDAO dao = ContactDAO.getInstance();
			Connection con = dao.getConnection();
			
			System.out.println("접속성공");
			
		} catch (Exception e) {
			System.out.println("오류");
		}
		
	}
	
	// * 구조 : controller에서 서비스를 부르고, 서비스에서 dao를 부르고 dao에서 db를 부름
	// (CONTROLLER - SERVICE - DAO - DB)
	
	// 3) 연락처 추가 메서드
	//	1. 매개변수 : contactDTO
	//	2. 반환값 	: int(0또는 1)
	public int insertContact(ContactDTO contact) {	
		
		try {
			con = getConnection();														// 공통메서드 db 연결
			sql = "INSERT INTO CONTACT VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";	// (?는 ContactDTO에 담겨져있음)
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getEmail());										// (domain(contactDTO)의 객체를 매개변수로 데이터를 불러옴)
						
			result = ps.executeUpdate();	// 0(실패) , 1(성공)  
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();																	// 공통메서드 close
		}
		return result;																	// return은 try-catch문 밖에서 할것
	}
	
	
	// 4) 연락처 수정 메서드
	
	public int updateContact(ContactDTO contact) {
		
		try {
			con = getConnection();
			sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, EMAIL = ? WHERE CONTACT_NO = ?";	// WHERE 조건을 안쓰면 모든 값이 똑같아지게 수정
			ps = con.prepareStatement(sql);													// update는 해당 조건과 칼럼명만 지정해서 수정(어떤건 뺴고 어떤건 안넣고 상관x)
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getEmail());	
			ps.setInt(4, contact.getContact_no());
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
		
	} 

	// 5) 연락처 삭제 메서드
		// 매개변수 : contact_no
		// 반환값 : 0 또는 1
		public int deleteContact(int contact_no) {
			
			try {
			con = getConnection();
			sql = "DELETE FROM CONTACT WHERE CONTACT_NO = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, contact_no);
			result = ps.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return result;
		}
	
	// 6) 연락처 조회 메서드
		// 매개변수 : contact_no
		// 반환값 	: ContactDTO 또는 null반환	(contactdto 쓰는 이유 : 조회해서 데이터를 전부 가져와야하니까)
		
		
		public ContactDTO selectContactByNo(int contact_no) {
			
			ContactDTO contact = null;
			
			try {
				
				con = getConnection();
				sql = "SELECT CONTACT_NO, NAME, TEL, EMAIL, REG_DATE FROM CONTACT WHERE CONTACT_NO = ?";
				ps = con.prepareStatement(sql);
				ps.setInt(1, contact_no);
				
				rs= ps.executeQuery();																	// select는 result가 아닌 resultset에 저장
				if(rs.next()) {																			// contact_no 매개변수의 값은 하나 뿐이기 때문에 if로 행 한번 스캔
					contact = new ContactDTO();
					contact.setContact_no(rs.getInt(1));
					contact.setName(rs.getString(2));
					contact.setTel(rs.getString(3));
					contact.setEmail(rs.getString(4));
					contact.setReg_date(rs.getDate(5));
				}			
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return contact;
			
		}
		
	// 7) 연락처 목록 메서드
		// 매개변수 : x
		// 반환값   : arraylist(contactdto가 여러개 담겨있는)
		
		public List<ContactDTO> selectAllContacts() {
			
			List<ContactDTO> contacts = new ArrayList<ContactDTO>();
			
			try {
				
				con = getConnection();
				sql = "SELECT CONTACT_NO, NAME, TEL, EMAIL, REG_DATE FROM CONTACT";
				ps = con.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while(rs.next()) {
					
					ContactDTO contact = ContactDTO.builder()					// (빌더타입 : 객체가 반복 사용되는 경우에 사용)
							.contact_no(rs.getInt(1))
							.name(rs.getString(2))
							.tel(rs.getString(3))
							.email(rs.getString(4))
							.reg_date(rs.getDate(5))
							.build();
					contacts.add(contact);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close();
			}
			return contacts;													// contacts의 타입은 list인데, 반환값은 arraylist면 오류
		}
	
	
	
	
	
}




