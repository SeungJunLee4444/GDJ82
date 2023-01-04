package com.gdu.app05.service;

import org.springframework.http.ResponseEntity;

public interface GalleryService {
	public ResponseEntity<byte[]> imageDisplay(String path, String filename);
	
	// * 해석 : 이미지에 대한 경로와 파일명을 매개변수로 전달하면,  byty[]배열을 반환해
	// 응답하겠다

}
