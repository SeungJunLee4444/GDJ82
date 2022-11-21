package com.gdu.app14.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app14.domain.UploadDTO;

public interface UploadService {
	
	public List<UploadDTO> getUploadList();
	public void save(MultipartHttpServletRequest multipartRequest, HttpServletResponse response);

}
