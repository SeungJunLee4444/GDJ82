package com.gdu.app14.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.UploadDTO;

public interface UploadService {
	

	// # 목록조회
	public List<UploadDTO> getUploadList();
	
	// # 게시글 추가
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	
	// # 상세화면 이동 
	public void getUploadByNo(int uploadNo, Model model);
	
	// # 개별 다운로드
	public ResponseEntity<Resource> download(String userAgent, int attachNo);
	
	// # 전체 다운로드
	public ResponseEntity<Resource> downloadAll(String userAgent, int uploadNo);
	
	// # 게시글 수정 : 상세화면 요청이 내부에 있을껄?
	public void modifyUpload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);
	
	// # 첨부파일 삭제 : 첨부파일 삭제후 게시글 상세화면으로 돌아간다
	public void removeAttachByAttachNo(int attachNo);
	
	// # 게시글 삭제
	public void removeUpload(HttpServletRequest multipartRequest, HttpServletResponse response);


}
