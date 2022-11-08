package com.gdu.app11.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;

@Service
public class EmpServiceImpl implements EmpService {
	
	// # mapper(dao역할)
	@Autowired
	private EmpMapper empMapper;

	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) { // * request는 파라미터를 가져오기위해, * model : 결과명단 저장용도(포워드)
		
		// # page 파라미터 : 전달된 파라미터가 없을 경우 1page로
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// # 페이지 구성 --------------------------------------------------------
		// - page : 페이지 번호
		// - totalRecord : 전체 게시글 수
		// - recorePerPage : 한 페이지당 할당되는 게시글 수 
		
		// 1) 글번호기준(employee_id) : 실제칼럼을 사용하는경우, 잘못된 방식 x
		// - 이유 : 중간에 글번호가 사라질 수 있음
		// page : 한 페이지 , total : 전체페이지수, 
		/*
		 	page	 	begin		end
		 	1			54			45
		  	2			44			35
		  	3			34			25
		  	4			24			15
		  	5	  		14			5
		  	6			4			1
		 */
		 // 2) 가상칼럼(rownum) : db 목록에 생성되는 가상칼럼, 올바른 방식 o
		/*
		 	page	 	begin		end		totalRecord
		 	1			1			10	<	54
		  	2			11			20	<	54
		  	3			21			30	<	54
		  	4			31			40	<	54
		  	5	  		41			50	<	54
		  	6			51			60	>	54	=> 마지막 경우만 if처리
		*/
		
		// # totalRecore : 전체게시글 수 구하는 법 --------------------------------------------> db
		int totalRecord = empMapper.selectAllEmployeesCount();
		
		// # recordPerPage : 한페이지당 몇개의 목록을 보여줄지 정하는 변수
		int recordPerPage = 10;	
		int begin = (page -1) * recordPerPage + 1; 
		int end = begin + recordPerPage -1;
		
		// * 마지막 페이지 레코드 처리 : 페이지의 마지막 게시글 번호를 totalRecord로 만든다
		if(end > totalRecord) {
			end = totalRecord;
		}
		
		// #
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(begin, end); //-----------------------> db
		
		model.addAttribute("employees", employees);
		
		
		
			 	
		
		

	}

}
