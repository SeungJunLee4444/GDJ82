package com.gdu.app11.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app11.domain.EmpDTO;
import com.gdu.app11.mapper.EmpMapper;
import com.gdu.app11.util.PageUtil;

@Service
public class EmpServiceImpl implements EmpService {
	
	// # mapper(dao역할)
	@Autowired
	private EmpMapper empMapper;
	
	// # 페이지유틸(@component로 자바빈 생성)
	@Autowired	// @Autowired는 타입이 일치하는 자바빈을 호출한다
	private PageUtil pageUtil;

	@Override
	public void findAllEmployees(HttpServletRequest request, Model model) { // * request는 파라미터를 가져오기위해, * model : 결과명단 저장용도(포워드)
		
		// # page 파라미터 : 전달된 파라미터가 없을 경우 1page로
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// # 게시글 페이지 목록 만들기 --------------------------------------------------------
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
		
		// # pageUtil 계산하기(page와 totalRecord를 전달하면 begin, end, recordPerPage를 가져온다)
		pageUtil.setPageUtil(page, totalRecord);
		
		// # map 만들기(begin과 end를 같이 보내주기 위함)
		Map<String, Object> map = new HashMap();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// # pageUtil 객체 사용 : 전달에 map을 사용
		List<EmpDTO> employees = empMapper.selectEmployeesByPage(map); //-----------------------> db
		
		
		
		// # 뷰로 보낼 데이터들
		model.addAttribute("employees", employees);
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/emp/list"));
		// * gepaging 메서드(임의로 만든) 
		
		// & 순번생성 : 시작하는 번호
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		// * -1한 값에 * 반복되는 숫자 
		
		// 1페이지 107 - 0 = 107
		// 2페이지 107 - 10 = 97
		// 3페이지 107 - 20 = 87
		
		}
	
	
	// # 검색조회	
	@Override
		public void findEmployees(HttpServletRequest request, Model model) {
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		Map<String, Object> map = new HashMap();
		map.put("column", request.getParameter("column"));
		map.put("query", request.getParameter("query"));
		map.put("start", request.getParameter("start"));
		map.put("stop", request.getParameter("stop"));
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		map.put("page", page);
		
		int totalRecord = empMapper.selectFindEmployeesCount(map); // ---------------------------> db
		
		pageUtil.setPageUtil(page, totalRecord);
		
		List<EmpDTO> employees = empMapper.selectFindEmployees(map); // -------------------------> db
		
		model.addAttribute("employees", employees);
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/emp/search"));
	}
	
	
		
		
			 	
		
		

	

}
