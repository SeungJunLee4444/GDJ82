package controller;

import java.util.Scanner;

import domain.ContactDTO;
import service.ContactService;
import service.ContactServiceImpl;


public class ContactController {
	
	/* 	작동 순서 : CONTROLLER -> SERVICE -> DAO -> SERVICE -> CONTROLLER
	 *  	- controller에서 값을 입력하면, 해당값을 dto(contact)에 저장, dto(contact)값을 매개변수로 service의 addcontact 메서드로 연결
	 * 		- service addcontact 메서드 안의 dao(insertcontact) 메서드가 실행된다 
	 *		- dao에서 쿼리문을 실행시키고 int값을 반환, service에서 성공여부를 출력
	 *		- service에서 성공여부를 확인하면 완료
	 *
	 */		
	
	
	
	
	
	
	
	/********************************필드***************************************/
	
	// (1) 입력을 위한 scanner 클래스
	// (2) contactservice 호출을 담당
	
	private Scanner sc;
	private ContactService contactService;
	
	
	/********************************생성자***************************************/
	
	public ContactController() {
		sc = new Scanner(System.in);
		contactService = new ContactServiceImpl();	// * 인터페이스는 new 생성이 불가능하기 때문에, 인터페이스를 구현한 contactserviceimpl을 생성
	}

		
	/********************************메서드***************************************/
	
		public void menu() {
			
			System.out.println("1. 연락처 등록");
			System.out.println("2. 연락처 수정");
			System.out.println("3. 연락처 삭제");
			System.out.println("4. 연락처 조회");
			System.out.println("5. 전체 연락처");
			System.out.println("0. 종료");
			
		} 
		
		public void play() {
			
			int contact_no;													// (같은 이름을 여러번 사용하기 위해 밖으로 변수를 뻄)
			String name;
			String tel;
			String email;
			ContactDTO contact;
			
			
			while(true) {
				menu();
				System.out.println("선택 (1~5,0)");
			System.out.println("선택 >>>");
			int choice = sc.nextInt();										// (숫자는 초이스에 저장, 이후에 nextline처리)
			sc.nextLine();
			switch(choice) {
			case 1 : System.out.println("신규연락처 이름 >>>");
					 name = sc.next();
					 System.out.println("신규연락처 전화번호 >>>");
					 tel = sc.next();
					 System.out.println("신규연락처 이메일 >>>");
					 email = sc.next();
					 contact = new ContactDTO();					// 입력받은 세 개의 데이터를 contact(dto)에 저장
					 contact.setName(name);
					 contact.setTel(tel);
					 contact.setEmail(email);
					 contactService.addContact(contact);					// controller에서 값을 입력하면, 해당값을 dto(도메인)에 저장, contact값을 가지고 service의 add 메서드로 연결
					 break;	
					 
			case 2 : System.out.println("수정할 연락처 번호");
					 contact_no = sc.nextInt();
					 System.out.println("수정할 연락처 이름 >>>");
					 name = sc.next();
					 System.out.println("수정할 연락처 전화번호 >>>");
					 tel = sc.next();
					 System.out.println("수정할 연락처 이메일 >>>");
					 email = sc.next();
					 contact = new ContactDTO();	
					 
					 contact.setContact_no(contact_no);
					 contact.setName(name);
					 contact.setTel(tel);
					 contact.setEmail(email);
					 contactService.modifyContact(contact);
					 break;
					 
			case 3 : System.out.println("삭제할 연락처 번호");
					 contact_no = sc.nextInt();
					 contactService.deleteContact(contact_no);
					 break;
	
			case 4 : System.out.println("조회할 연락처 번호");
			 		 contact_no = sc.nextInt();
			 		 contactService.findContactByNo(contact_no);
			 		 break;

			case 5 : contactService.findAllContacts();
					 break;
			case 6 : contactService.createContactFile();
					 break;

			case 0 : System.out.println("연락처 프로그램을 종료합니다"); 
					 return; 
					 
			default: System.out.println("다시 선택하세요!");
			
			}
			}
			
		}

}


