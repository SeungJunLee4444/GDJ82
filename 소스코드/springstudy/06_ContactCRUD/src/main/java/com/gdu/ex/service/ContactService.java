package com.gdu.ex.service;

import java.util.List;

import com.gdu.ex.domain.ContactDTO;

public interface ContactService {
	
		// # 모두 조회 : 매개변수없이 list를 반환받음
		public List<ContactDTO> fineAllContact();
		
		// # 상세 조회 : no를 전달하면 contact 하나를 가져옴
		public ContactDTO findContactByNo(int no);
		
		// # 연락처 추가 : dto 하나를 전달하면 int 반환
		public int saveContact(ContactDTO contact);
		
		// # 연락처 수정 : dto 하나를 전달하면 int 반환
		public int modifyContact(ContactDTO contact);
		
		// # 연락처 삭제 : no를 전달하면 int 반환
		public int deleteContact(int no);
}
