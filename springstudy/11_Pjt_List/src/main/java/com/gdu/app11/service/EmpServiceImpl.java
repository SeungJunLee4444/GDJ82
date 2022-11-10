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
		
		// # 파라미터 recordPerPage, 전달되지 않으면 recordPerPage=10으로 처리 ----
		// * 보려는 페이지당 게시글의 수를 필드로 고정시키는 것이 아닌 파라미터로 전달하는것
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("recordPerPage"));
		int recordPerPage = Integer.parseInt(opt2.orElse("10"));
		
		
		
		// # pageUtil 계산하기(page와 totalRecord를 전달하면 begin, end, recordPerPage를 가져온다)
		pageUtil.setPageUtil(page, recordPerPage ,totalRecord);
		
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
		model.addAttribute("recordPerPage", recordPerPage);
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
		
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String start = request.getParameter("begin");
		String stop = request.getParameter("query");
		
		Map<String, Object> map = new HashMap();
		map.put("column", column);
		map.put("query", query);	// * query는 employeeid ~ phone_number 조회시 사용
		map.put("start", start);	// * start, stop은 hire_date와 salary 조회시 사용
		map.put("stop", stop);
		map.put("page", page);
		
		// # 파라미터 recordPerPage, 전달되지 않으면 recordPerPage=10으로 처리 ----
		// * 보려는 페이지당 게시글의 수를 필드로 고정시키는 것이 아닌 파라미터로 전달하는것
		Optional<String> opt2 = Optional.ofNullable(request.getParameter("recordPerPage"));
		int recordPerPage = Integer.parseInt(opt2.orElse("10"));
		
		int totalRecord = empMapper.selectFindEmployeesCount(map); // ---------------------------> db
		
		pageUtil.setPageUtil(page, recordPerPage, totalRecord);
		
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		List<EmpDTO> employees = empMapper.selectFindEmployees(map); // -------------------------> db
		
		model.addAttribute("employees", employees);
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());
		
		// # 검색후 페이지 이동시 검색한 내용을 가지고 이동할 수 있도록 처리
		// - 
		String path = null;
		switch(column) {
		case "EMPLOYEE_ID" :
		case "E.DEPARTMENT_ID" :
		case "LAST_NAME" :
		case "FIRST_NAME" :
		case "PHONE_NUMBER" :
			path = request.getContextPath() + "/emp/search?column=" + column + "&query=" + query;
			break;
		case "HIRE_DATE" :
		case "SALARY" :
			path = request.getContextPath() + "/emp/search?column=" + column + "&start=" + start + "&stop=" + stop;
			break;
		}
		
		model.addAttribute("paging", pageUtil.getPaging(path));
	}
	
	
	// # 이메일, 성, 이름 검색 자동완성 : ajax로 처리
		@Override
		public Map<String, Object> findAutoCompleteList(HttpServletRequest request) {
			
			String target = request.getParameter("target");
			String param = request.getParameter("param");
			
			// map : param, target 저장
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("target", target);
			map.put("param", param);
			
			// db 접근
			List<EmpDTO> list = empMapper.selectAutoCompleteList(map);
			
			
			Map<String, Object> result = new HashMap<String, Object>();
			if(list.size() == 0) {	// * list 배열의 길이가 0 = 값이 없으면
				result.put("status", 400);
				result.put("list", null);
				//System.out.println("400");	
			} else {
				result.put("status", 200);
				result.put("list", list);
				//System.out.println("200");
				
			}
			
			switch(target) {
			case "FIRST_NAME": result.put("target", "firstName"); break;
			case "LAST_NAME": result.put("target", "lastName"); break;
			case "EMAIL": result.put("target", "email"); break;
			}
			
			
			return result; //  * ajax의 resData에 json 형식으로 저장된다
			
			
			/* 
			 result = {
			 	"status" : 200,
			 	"list" : [
			 	
			 	]
			 	"target" : switch문에서 나온 3가지 칼럼 중 하나
			 } 
			  
			  
			 */
			
			
			
			/* 
			 * jackson으로 인해 json으로 자동변환된 조회 결과 + json에서 속성값 꺼내는 방법
			  Map<> result가 jackson에 의해서 아래 json으로 자동 변경된다
			  result = {
			  	"status" : 200,						=> 꺼내는 방법 : result.status	/ result["status"]
			  	"list" : [
			  			{
			  				"Email" : email@~,		// 쿼리문에서 이메일만 가지고 왔기 때문에 email만 저장되있다
			  				"EmployeeId" : null,
			  				"FirstName" : null,
			  				"LastName" : null,      => 꺼내는 방법 : result.list[0].email
			  			}
			  	]
			  }
			  
			  
			 */
		}
	
	
		
		
			 	
		
		

	

}
