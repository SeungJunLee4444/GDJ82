package com.gdu.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.ex.domain.ContactDTO;
import com.gdu.ex.repository.ContactDAO;

@Service // * @component : 컨트롤러에 저장하는 3번째 방법
public class ContactServiceImpl implements ContactService {
	
	// # dto : 싱글턴 처리된 자바빈, 컨테이너에 저장된 상태(@repository)
	@Autowired
	private ContactDAO dao; 

	// # 전체조회 ----------------------------------
	@Override
	public List<ContactDTO> fineAllContact() {
		return dao.selectAllContact();
	}
	
	// # 상세조회
	@Override
	public ContactDTO findContactByNo(int no) {
		
		return null;
	}

	// # 연락처 추가 -------------------------------
	@Override
	public int saveContact(ContactDTO contact) {
		return dao.insertContact(contact);
	}

	// # 연락처 수정
	@Override
	public int modifyContact(ContactDTO contact) {
		// TODO Auto-generated method stub
		return 0;
	}

	// # 연락처 삭제
	@Override
	public int deleteContact(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

}
