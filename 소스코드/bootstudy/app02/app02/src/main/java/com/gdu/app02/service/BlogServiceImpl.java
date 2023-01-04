package com.gdu.app02.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app02.domain.BlogDTO;
import com.gdu.app02.mapper.BlogMapper;
import com.gdu.app02.util.MyFileUtil;
import com.gdu.app02.util.PageUtil;


@Service
public class BlogServiceImpl implements BlogService {

	
	private BlogMapper blogMapper;
	private PageUtil pageUtil;
	private MyFileUtil fileUtil;
	
	// # @autowired 복수처리 방법 : (1) 생성자(@AllArgsConstructor) 에너테이션 만들기, (2) set 메서드 만들기
	// * 1번 방법은 컨트롤러에 복수의 service를 빈으로 가져올때 사용된다
	@Autowired
	public void set(BlogMapper blogMapper, PageUtil pageUtil, MyFileUtil fileUtil) {
		this.blogMapper = blogMapper;
		this.pageUtil = pageUtil;
		this.fileUtil = fileUtil;
	}



	// # 목록
	// (1) 전체 블로그 개수		(2) 블로그 목록
	// selectBlogListCount 		selectBlogListByMap
	// * selectBlogListCount : totalRecord를 
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
	
	// # 블로그 삽입
	@Override
	public void saveBlog(HttpServletRequest request, HttpServletResponse response) {
		
		// 1. 파라미터 : title, content
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 2. 작성자의 ip
		// * 작성된 내용이 어딘가를 경우해서 도착하면, 원래 ip가 X-Forwarded-For라는 요청헤더에 저장된다

			// 1) 경유지가 없을 때
			// 출발					도착
			// 1.1.1.1				1.1.1.1	: request.getRemoteAddress() 
							//		null    : request.getHeader("X-Forwarded-For") optional 처리
			
			// 2) 경유지가 있을 때
			// 예시
			// 출발		경유		도착
			// 1.1.1.1	2.2.2.2		2.2.2.2	: request.getRemoteAddress() 로 호출가능
								//	1.1.1.1	: request.getHeader("X-Forwarded-For"); 로 호출가능
		
		// * 현재는 경유지가 없는 상태다
		Optional<String> opt = Optional.ofNullable(request.getHeader("X-Forwarded-For"));
		String ip = opt.orElse(request.getRemoteAddr());
		
		// 3. db로 보낼 dto
		BlogDTO blogDTO = BlogDTO.builder()
				.title(title)
				.content(content)
				.ip(ip)
				.build();
		
		// 4. db에 저장
		int result = blogMapper.insertBlog(blogDTO);
		
		// 5. 응답
		
		try {
			
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {
				out.println("alert('삽입성공');");
				out.println("location.href='" + request.getContextPath() + "/blog/list';");
			} else {
				out.println("alert('삽입실패');");
				out.println("history.go(-1);");
			}
			out.println("</script>");
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	// # 이미지 업로드
	@Override
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest) {
		
		// 1. 파라미터 : file
		MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// 2. 저장할 파일명
		String filesystem = fileUtil.getFilename(multipartFile.getOriginalFilename());	// origin을 filename으로
		
		// 3. 저장할 파일 경로 생성
		String path = "C:\\upload";
		
		// 4. 저장할 폴더 생성
		File dir = new File(path);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		// 5. 저장할 file 객체
		File file = new File(dir, filesystem);	// path, filesystem으로 해도 상관없다
		
		// 6. 하드디스크에 file 객체 저장하기
		try {
			multipartFile.transferTo(file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 7. 저장된 파일을 확인할 수 있는 맵핑 반환 : json 데이터로 반환하기 위해 map 사용
		Map<String, Object> map = new HashMap<>();
		map.put("src", multipartRequest.getContextPath() + "/load/image/" + filesystem);
		
		return map;
		
		// * 예시
		// 저장된 파일이 aaa.jpg로 가정하면,
		// src = ${contextPath}/load/image/aaa.jpg를 반환하겠다
	}
	
	// # 조회수 증가
	// * 반환타입이 void : 응답을 안함
	// * 반환타입이 int : 응답을 함
	@Override
	public int increaseBlogHit(int blogNo) {
		return blogMapper.updateHit(blogNo);
		
	}
	
	// # 상세보기
	@Override
	public BlogDTO getBlogByNo(int blogNo) {
		return blogMapper.selectBlogByNo(blogNo);		
	}
	
	// # 블로그 수정
	// (1) updateBlog 
	@Override
	public void modifyBlog(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 title, content, blogNo
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int blogNo = Integer.parseInt(request.getParameter("blogNo"));
		
		// DB로 보낼 BlogDTO
		BlogDTO blog = BlogDTO.builder()
				.title(title)
				.content(content)
				.blogNo(blogNo)
				.build();
		
		// DB 수정
		int result = blogMapper.updateBlog(blog);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {			
				out.println("alert('수정 성공');");
				out.println("location.href='" + request.getContextPath() + "/blog/detail?blogNo=" + blogNo + "';");
			} else {
				out.println("alert('수정 실패');");
				out.println("history.back();");
			}
			out.println("</script>");			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// # 블로그 삭제
	@Override
	public void removeBlog(HttpServletRequest request, HttpServletResponse response) {
		
		// 파라미터 blogNo
		int blogNo = Integer.parseInt(request.getParameter("blogNo"));
		
		// DB 삭제
		int result = blogMapper.deleteBlog(blogNo);
		
		// 응답
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			if(result > 0) {			
				out.println("alert('삭제 성공');");
				out.println("location.href='" + request.getContextPath() + "/blog/list';");
			} else {
				out.println("alert('삭제 실패');");
				out.println("history.back();");
			}
			out.println("</script>");			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
