package service;

import domain.ContactDTO;

public interface ContactService {
	
	public void addContact(ContactDTO contact); // 인터페이스는 본문이 없다 {}x 
	
	public void modifyContact(ContactDTO contact);
	
	// * 추가, 수정에는 여러 데이터가 들어가야하니 contactDTO사용
	// * 삭제에는 특정칼럼 하나만 필요하니 contact_no
	
	public void deleteContact(int contact_no);
	
	public void findContactByNo(int contact_no);
	
	public void findAllContacts();
	
	public void createContactFile();
		
	

}
