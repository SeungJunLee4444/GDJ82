package com.gdu.app15.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app15.domain.BlogDTO;

public interface BlogService {
	
	// # 목록 
	public void getBlogList(Model model);
	
	// # 삽입
	public void saveBlog(HttpServletRequest request, HttpServletResponse response);

	// # 이미지 업로드
	public Map<String, Object> saveSummernoteImage(MultipartHttpServletRequest multipartRequest);
	
	// # 조회수 : void를 사용하면 응답 페이지를 별도로 안만드는것
	public int increaseBlogHit(int blogNo);
	
	// # 상세
	public BlogDTO getBlogByNo(int blogNo);
	
	// # 수정
	public void modifyBlog(HttpServletRequest request, HttpServletResponse response);
	// # 삭제
	public void removeBlog(HttpServletRequest request, HttpServletResponse response);
	
}
