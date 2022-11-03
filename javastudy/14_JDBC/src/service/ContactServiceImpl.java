package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import domain.ContactDTO;
import repository.ContactDAO;


/*	과정설명
 * 1. controller : 입력장치		(scanner)
 * 2. service	 : 인터페이스
 * 3. domain 	 : DTO
 * 4. ContactDAO : DAO			(connection과 close)
 *
 */

public class ContactServiceImpl implements ContactService {
	
	/********************************필드***************************************/
	
	// DAO에 데이터를 전달하고, DAO로부터 결과를 반환받기 위해 DAO를 선언(controller - service - dao - db)
	private ContactDAO dao = ContactDAO.getInstance();	// STATIC 클래스 호출


	/********************************메서드***************************************/

	
	
	

	@Override
	public void addContact(ContactDTO contact) {				// (INSERT)
		int result = dao.insertContact(contact);												// 1. controller에서 매개변수로 가져온 contact 정보를 DAO 객체의 INSERTCONTACT 메서드로 다시 던진다
		if(result > 0) {																		// 2. DAO 메서드가 실행된 값을 RESULT에 저장해서 성공, 실패 여부를 확인 (반환된 값은 INT타입)
			System.out.println("연락처가 등록되었습니다");		// 1
		} else {
			System.out.println("연락처 등록이 실패했습니다");	// 0
		} 

	}

	@Override
	public void modifyContact(ContactDTO contact) {				// (UPDATE)
		int result = dao.updateContact(contact);												// 1. controller에서 매개변수로 가져온 contact 정보를 DAO 객체의 INSERTCONTACT 메서드로 다시 던진다
		if(result > 0) {																		// 2. DAO 메서드가 실행된 값을 RESULT에 저장해서 성공, 실패 여부를 확인 (반환된 값은 INT타입)
			System.out.println("연락처가 수정되었습니다");		// 1
		} else {
			System.out.println("연락처 수정이 실패했습니다");	// 0
		} 

	

	}

	@Override
	public void deleteContact(int contact_no) {					// (DELETE)
		int result = dao.deleteContact(contact_no);											
		if(result > 0) {																		
			System.out.println("연락처가 삭제되었습니다");		// 1
		} else {
			System.out.println("연락처 삭제가 실패했습니다");	// 0
		} 

	
	}
	

	@Override
	public void findContactByNo(int contact_no) {					// (SELECT)
		ContactDTO contact = dao.selectContactByNo(contact_no);
		if(contact == null) {
			System.out.println("조회된 연락처가 없습니다");
		} else {
			System.out.println(contact);
		}

	}

	@Override
	public void findAllContacts() {								// (SELECT*)
		List<ContactDTO> contacts = dao.selectAllContacts();
		if(contacts.isEmpty()) {
			System.out.println("저장된 연락처가 없습니다");
		} else {
			for(ContactDTO contact : contacts) {
				System.out.println(contact);
			}
		}
		

	}
	
	@Override	// 깃허브는 실행되니 비교해볼것
	public void createContactFile() {
		
		
		// 1. 전체 연락처 목록을 가져온다
		List<ContactDTO> contacts = dao.selectAllContacts();	
		// dao 객체의 selallcontacts(전체조회 메서드)
		
		// 2. 파일 생성
		File file = new File("연락처.csv");
		// 경로를 작성하지 않으면 프로젝트 디렉터리에 파일이 생성된다
		
		// 3. bufferedwriter 스트림 생성
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			
			bw.write("번호,이름,전화,이메일,등록일");
			bw.newLine();
			// & newline = write("\n") 동일개념
			
			for(ContactDTO contact : contacts) {
				bw.write(contact.getContact_no()
				bw.write(contact.getName() + ",");
				bw.write(contact.getTel() + ",");
				bw.write(contact.getEmail() + ",");
		
				bw.write(new SimpleDateFormat("yy/MM/dd").format(contact.getReg_date() + "\n"));
			}
			
			System.out.println("연락처.csv 파일이 생성되었습니다");
			
		} catch (IOException e) {
			System.out.println("연락처.csv파일이 생성되지 않았습니다");
			e.printStackTrace();
		}
		
	}
	

}
