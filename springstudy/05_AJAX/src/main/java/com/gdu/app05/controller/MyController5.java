package com.gdu.app05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gdu.app05.service.GalleryService;

@Controller
public class MyController5 {
	
	@GetMapping("gallery")		// * 반환이 void일 경우 mapping을 jsp로 인식한다
	public void gallery() {
	}
	
	@Autowired
	private GalleryService GalleryService;
	
	// # 이미지 응답 : byte[]배열로 응답
	@ResponseBody						// * 응답에는 @responseBody가 반드시 필요하다
	@GetMapping("image/display")
	public ResponseEntity<byte[]> display(String path, String filename) {
		return GalleryService.imageDisplay(path, filename);
	}
	

}



