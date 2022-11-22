package com.gdu.app14.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.UploadDTO;

public interface UploadService {
	
	// [[ 조회
	public List<UploadDTO> getUploadList();
	
	// [[ 게시글 추가
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);

	// [[ 상세보기
	public void getUploadByNo(int uploadNo, Model model);	// * model : 반환시 uploadNo와 attachlist 두가지를 실어야한다
	
	// [[ 첨부파일 다운로드
	public ResponseEntity<Resource> download(String userAgent, int attachNo);
	
	// [[ 삭제
	public void removeAttachByAttachNo(int attachNo);
}
