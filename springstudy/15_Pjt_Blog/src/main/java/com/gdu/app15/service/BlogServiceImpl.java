package com.gdu.app15.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gdu.app15.mapper.BlogMapper;
import com.gdu.app15.util.PageUtil;


@Service
public class BlogServiceImpl implements BlogService {

	
	private BlogMapper blogMapper;
	private PageUtil pageUtil;
	
	// # @autowired 복수처리 방법 : (1) 생성자(@AllArgsConstructor) 에너테이션 만들기, (2) set 메서드 만들기
	// * 1번 방법은 컨트롤러에 복수의 service를 빈으로 가져올때 사용된다
	@Autowired
	public void set(BlogMapper blogMapper, PageUtil pageUtil) {
		this.blogMapper = blogMapper;
		this.pageUtil = pageUtil;
	}



	// # 목록
	@Override
	public void getBlogList(Model model) {	// * request에 page가 전달되야한다
		
		// * Model에 저장된 request 꺼내기 : 컨트롤러에서 model에 request를 담아서 전달했음
		
		Map<String, Object> modelMap = model.asMap();	// model을 map으로 변신
		HttpServletRequest request = (HttpServletRequest)modelMap.get("request");
		
		
		// 1. 파라미터
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		// 2. 전체 블로그 개수 -- db
		int totalRecord = blogMapper.selectBlogListCount();
		
		// 3. 페이징 처리에 필요한 변수 계산
		pageUtil.setPageUtil(page, totalRecord);
		
		
		// 4. 조회 조건으로 사용할 map 만들기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());
		
		// * 뷰로 전달할 데이터를 model에 저장
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("blogList", blogMapper.selectBlogListByMap(map));
		model.addAttribute("beginNo", totalRecord - (page - 1) * pageUtil.getRecordPerPage());	// * 한 페이지 시작번호 계산
		model.addAttribute("paging", pageUtil.getPaging(request.getContextPath() + "/blog/list"));
		
		
	}
	
	
	
}
