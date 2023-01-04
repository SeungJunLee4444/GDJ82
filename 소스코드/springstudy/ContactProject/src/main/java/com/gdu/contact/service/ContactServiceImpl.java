package com.gdu.contact.service;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.contact.domain.ContactDTO;
import com.gdu.contact.repository.ContactDAO;


// @Service 없습니다.
// com.gdu.contact.config.AppContext 클래스에서 ContactServiceImpl을 @Bean으로 등록하였습니다.

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDAO dao;
	
	@Override
	public void findAllContacts(Model model) {
		// 목록을 DB에서 가져온 뒤 model에 저장하는 코드
		model.addAttribute("contacts", dao.selectAllContacts());
	}

	@Override
	public void findContactByNo(Model model) {
		// model에 저장된 request를 꺼내는 코드
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		// request에서 상세보기할 번호(no) 꺼내는 코드
		Optional<String> opt = Optional.ofNullable(request.getParameter("no"));
		int no = Integer.parseInt(opt.orElse("1"));
		// 상세보기할 데이터를 DB에서 가져온 뒤 model에 저장하는 코드
		model.addAttribute("contact", dao.selectContactByNo(no));
	}

	@Override
	public void register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청 파라미터를 이용해서 DB로 보낼 ContactDTO 만드는 코드
		ContactDTO contact = new ContactDTO();
		contact.setName(request.getParameter("name"));
		contact.setTel(request.getParameter("tel"));
		contact.setAddr(request.getParameter("addr"));
		contact.setEmail(request.getParameter("email"));
		contact.setNote(request.getParameter("note"));
		// DB에 삽입하는 코드
		int result = dao.insertContact(contact);
		// 삽입 결과에 따른 응답 코드
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('연락처가 등록되었습니다.');");
			out.println("location.href='" + request.getContextPath() + "/card/list';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('연락처 등록이 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
	}

	@Override
	public void modify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청 파라미터를 이용해서 DB로 보낼 ContactDTO 만드는 코드
		ContactDTO contact = new ContactDTO();
		contact.setNo(Integer.parseInt(request.getParameter("no")));
		contact.setName(request.getParameter("name"));
		contact.setTel(request.getParameter("tel"));
		contact.setAddr(request.getParameter("addr"));
		contact.setEmail(request.getParameter("email"));
		contact.setNote(request.getParameter("note"));
		// DB를 수정하는 코드
		int result = dao.updateContact(contact);
		// 수정 결과에 따른 응답 코드
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('연락처가 수정되었습니다.');");
			out.println("location.href='" + request.getContextPath() + "/card/detail?no=" + request.getParameter("no") + "';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('연락처 수정이 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
	}

	@Override
	public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청 파라미터(삭제할 연락처의 번호)를 처리하는 코드
		Optional<String> opt = Optional.ofNullable(request.getParameter("no"));
		int no = Integer.parseInt(opt.orElse("0"));
		// DB에서 삭제하는 코드
		int result = dao.deleteContact(no);
		// 삭제 결과에 따른 응답 코드
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(result > 0) {
			out.println("<script>");
			out.println("alert('연락처가 삭제되었습니다.');");
			out.println("location.href='" + request.getContextPath() + "/card/list';");
			out.println("</script>");
		} else {
			out.println("<script>");
			out.println("alert('연락처 삭제가 실패했습니다.');");
			out.println("history.back();");
			out.println("</script>");
		}
		out.close();
	}

}
